package umn.ac.id.musique;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {
    private static final int REQUEST_WRITE_PERMISSION = 111;
    private ListView listView;
    private String songNames[];
    int open = 0;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        requestPermission();

        Toolbar toolbarmenu = findViewById(R.id.toolbarmenu);
        setSupportActionBar(toolbarmenu);
        open = open+1;
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        if(open < 2) {
            // Set the message show for the Alert time
            builder.setMessage("Vanessa Ardelia 00000019949");

            // Set Alert Title
            builder.setTitle("Selamat Datang");

            // Set Cancelable false
            // for when the user clicks on the outside
            // the Dialog Box then it will remain show
            builder.setCancelable(false);

            // Set the positive button with yes name
            // OnClickListener method is use of
            // DialogInterface interface.

            builder.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            // Vibrate for 500 milliseconds
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                //deprecated in API 26
                                v.vibrate(500);
                            }
                            // When the user click yes button
                            // then app will close
                            dialog.cancel();
                        }
                    });
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
        }else{
            AlertDialog alertDialog = builder.create();
            alertDialog.hide();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myprofile:
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.asc:
                listView = findViewById(R.id.listview);
                final ArrayList<File> songs = readSongs(Environment.getExternalStorageDirectory());

                Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v2.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v2.vibrate(500);
                }

                Collections.sort(songs);

                songNames = new String[songs.size()];
                for(int i = 0; i <songs.size();i++){
                    songNames[i] = songs.get(i).getName().toString().replace("mp3","");
                }

                ArrayAdapter<String> title_adapter2 = new ArrayAdapter<String>(getApplicationContext(),R.layout.song, R.id.textView, songNames);
                //textview = judul lagu
                title_adapter2.notifyDataSetChanged();
                listView.setAdapter(title_adapter2);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //i = posisi lagu yang di klik oleh user
                        startActivity(new Intent(HomeActivity.this, PlayActivity.class).putExtra("position", i).putExtra("list", songs).putExtra("title", songNames));
                    }
                });
                Toast.makeText(getApplicationContext(),"Sort Asc", Toast.LENGTH_LONG).show();
                return true;
            case R.id.dsc:
                    listView = findViewById(R.id.listview);
                    final ArrayList<File> songs2 = readSongs(Environment.getExternalStorageDirectory());

                    Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v3.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v3.vibrate(500);
                    }

                    Collections.reverse(songs2);
                    songNames = new String[songs2.size()];
                    for(int i = 0; i <songs2.size();i++){
                        songNames[i] = songs2.get(i).getName().toString().replace("mp3","");
                    }

                    ArrayAdapter<String> title_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.song, R.id.textView, songNames);
                    //textview = judul lagu
                    title_adapter.notifyDataSetChanged();
                    listView.setAdapter(title_adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //i = posisi lagu yang di klik oleh user
                            startActivity(new Intent(HomeActivity.this, PlayActivity.class).putExtra("position", i).putExtra("list", songs2).putExtra("songName",songNames[i]));
                        }
                    });
                    Toast.makeText(getApplicationContext(),"Sort Desc", Toast.LENGTH_LONG).show();
                return true;
            case R.id.logout:

                Vibrator v4 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v4.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v4.vibrate(500);
                }
                Intent intentlogout = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intentlogout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            listView = findViewById(R.id.listview);
            final ArrayList<File> songs = readSongs(Environment.getExternalStorageDirectory());

            songNames = new String[songs.size()];

            for(int i = 0; i <songs.size();i++){
                songNames[i] = songs.get(i).getName().toString().replace("mp3","");
            }

            final ArrayAdapter<String> title_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.song,R.id.textView, songNames);
//            textview = judul lagu
            title_adapter.notifyDataSetChanged();
            listView.setAdapter(title_adapter);

            searchView = (SearchView)findViewById(R.id.item_search);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String text) {
                    title_adapter.getFilter().filter(text);
//            textview = judul lagu
                    title_adapter.notifyDataSetChanged();
                    listView.setAdapter(title_adapter);
                    Log.i("BARU", "posisi lagu" + listView);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    title_adapter.getFilter().filter(newText);
//            textview = judul lagu
                    title_adapter.notifyDataSetChanged();
                    listView.setAdapter(title_adapter);
                    Log.i("BARU", "posisi lagu" + listView);
                    return true;
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    i = posisi lagu yang di klik oleh user
//                    MediaPlayer mp1;
//                    mp1 = MediaPlayer.create(HomeActivity.this, raw.my_sound_name);
                    startActivity(new Intent(HomeActivity.this,PlayActivity.class).putExtra("position", i).putExtra("list", songs).putExtra("songName", songNames[i]));
                }
            });
        }
    }

    private void requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            listView = findViewById(R.id.listview);
            final ArrayList<File> songs = readSongs(Environment.getExternalStorageDirectory());
            songNames = new String[songs.size()];

            for(int i = 0; i <songs.size();i++){
                songNames[i] = songs.get(i).getName().toString();
            }
            ArrayAdapter<String> title_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.song,R.id.textView, songNames);
//            textview = judul lagu
            title_adapter.notifyDataSetChanged();
            listView.setAdapter(title_adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    i = posisi lagu yang di klik oleh user
                    startActivity(new Intent(HomeActivity.this, PlayActivity.class).putExtra("position", i).putExtra("list", songs).putExtra("songName",songNames[i]));
                }
            });
        }
    }

    //    read file mp3
    private ArrayList<File> readSongs(File root){
        ArrayList<File> arrayList = new ArrayList<File>();
        File files[] = root.listFiles();

        for(File file : files){
            if(file.isDirectory()){
                arrayList.addAll(readSongs(file));
            }else{
                if(file.getName().endsWith(".mp3") || file.getName().endsWith(".MP3") ){
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }
}
