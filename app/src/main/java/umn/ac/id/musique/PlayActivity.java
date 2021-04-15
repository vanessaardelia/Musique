package umn.ac.id.musique;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.util.ArrayList;


public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPlay, btnBack, btnFor, btnBackf, btnNext;
    private SeekBar seekBar;
    static private MediaPlayer mediaPlayer;
    private Runnable runnable;
    private Handler handler;
    private TextView songNameText;
    String sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean next = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        Toolbar toolbarmenu = findViewById(R.id.toolbarmenu);
        setSupportActionBar(toolbarmenu);
        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        14:51
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Bundle bundle = getIntent().getExtras();
        ArrayList<File> songs = (ArrayList) bundle.getParcelableArrayList("list");
        int position = bundle.getInt("position", 0);

        Uri uri = Uri.parse(songs.get(position).toString());

        btnPlay = findViewById(R.id.btnPlay);
        btnBack = findViewById(R.id.btnBack);
        btnFor = findViewById(R.id.btnFor);
        btnBackf = findViewById(R.id.btnBackf);
        btnNext = findViewById(R.id.btnNext);
        handler = new Handler();
        seekBar = findViewById(R.id.seekbar);
        mediaPlayer = MediaPlayer.create(this, uri);

        songNameText = findViewById(R.id.title);
        sname = songs.get(position).getName().toString();
        Toast.makeText(getApplicationContext(),"Song played "+songs.get(position).getName(), Toast.LENGTH_LONG).show();
        songNameText.setText(sname);
        songNameText.setSelected(true);

        btnFor.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnBackf.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
                changeSeekbar();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void changeSeekbar(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if(mediaPlayer.isPlaying()){
            runnable = new Runnable(){
                @Override
                public void run() {
                    changeSeekbar();
                }
            };
            handler.postDelayed(runnable, 3000);
        }
    }

    int clickcount=0;
    int clickcountback=0;
    //    btnFor
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnPlay:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlay.setText(">");
                }else{
                    mediaPlayer.start();
                    btnPlay.setText("||");
                    changeSeekbar();
                }
                break;
            case R.id.btnFor:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
                break;
            case R.id.btnBack:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
                break;
            case R.id.btnBackf:
                clickcountback = clickcountback+1;
                if(clickcountback==1)
                {
                    Toast.makeText(getApplicationContext(),"Song played", Toast.LENGTH_LONG).show();
                    Bundle bundle = getIntent().getExtras();
                    ArrayList<File> songs = (ArrayList) bundle.getParcelableArrayList("list");
                    int position = bundle.getInt("position");
                    int size = bundle.getInt("size");
                    sname = songs.get(position).getName().toString();
                    songNameText.setText(sname);
                    songNameText.setSelected(true);
                    initSong(position, songs);
                    changeSeekbar();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Song played", Toast.LENGTH_LONG).show();
                    Bundle bundle = getIntent().getExtras();
                    ArrayList<File> songs = (ArrayList) bundle.getParcelableArrayList("list");
                    int position = bundle.getInt("position");
                    Toast.makeText(getApplicationContext(),"Song prev played", Toast.LENGTH_LONG).show();
                    int size = bundle.getInt("size");
                    if(position-(clickcountback-1)>0) {
                        changeSeekbar();
                        int backsong = position-(clickcountback-1);
                        initSong(backsong, songs);
                        sname = songs.get(backsong).getName().toString();
                        songNameText.setText(sname);
                        songNameText.setSelected(true);
                        Toast.makeText(getApplicationContext(), "Prev song track played", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Track list number 0 played", Toast.LENGTH_LONG).show();
                        int backsong = 0;
                        initSong(backsong, songs);
                        sname = songs.get(backsong).getName().toString();
                        songNameText.setText(sname);
                        songNameText.setSelected(true);
                    }
                }
                break;
            case R.id.btnNext:
                clickcount = clickcount+1;
                if(clickcount==1)
                {
                    Toast.makeText(getApplicationContext(),"Song played", Toast.LENGTH_LONG).show();
                    Bundle bundle = getIntent().getExtras();
                    ArrayList<File> songs = (ArrayList) bundle.getParcelableArrayList("list");
                    int position = bundle.getInt("position");
                    int size = bundle.getInt("size");
                    initSong(position, songs);
                    sname = songs.get(position).getName().toString();
                    songNameText.setText(sname);
                    songNameText.setSelected(true);
                    changeSeekbar();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Song played", Toast.LENGTH_LONG).show();
                    Bundle bundle = getIntent().getExtras();
                    ArrayList<File> songs = (ArrayList) bundle.getParcelableArrayList("list");
                    int position = bundle.getInt("position");
                    int size = bundle.getInt("size");
                    int nextsong = position + (clickcount-1);
                    changeSeekbar();
                    initSong(nextsong, songs);
                    sname = songs.get(nextsong).getName().toString();
                    songNameText.setText(sname);
                    songNameText.setSelected(true);
                    Toast.makeText(getApplicationContext(),"Next song played", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void initSong(int position, ArrayList<File> songs){
        mediaPlayer.pause();
        btnPlay.setText(">");
        Uri current = Uri.parse(songs.get(position).toString());
        mediaPlayer = MediaPlayer.create(this, current);
        mediaPlayer.start();
        btnPlay.setText("||");
        changeSeekbar();
    }
}
