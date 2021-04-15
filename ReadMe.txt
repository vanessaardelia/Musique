Panduan cara kerja:
Pada halaman utama, akan ada menu untuk login.
Login yang digunakan: 
1. musique sebagai username
2. musique123 sebagai password

Halaman berikutnya adalah halaman home. Pada halaman home ini, akan ada list lagu yang diambil dari semua mp3 dari internal HP.
1. Jika salah satu dari listview di klik, maka akan di direct ke halaman now playing.
2. Pada option pojok kanan atas, ada 4 menu:
	a. Profile: untuk ke halaman profile
	b. Sort asc: untuk sort lagu secara asc
	c. Sort desc: untuk sort lagu secara desc
	d. Logout: untuk logout (kembali ke halaman login)
3. Fitur search untuk mencari lagu berdasarkan judul lagu

Pada halaman now playing ini, 
1. ada seekbar yang disesuaikan dengan durasi lagu
2. 5 tombol:
	a. play / pause :untuk memulai dan mematikan lagu
	b. tombol fastforward: untuk mempercepat lagu
	c. tombol backforward: untuk kembali ke menit sebelumnya
	d. tombol prev: untuk kembali ke lagu sebelumnya
	e. tombol next: untuk ke lagu berikutnya
3. Akan ditampilkan juga judul lagu dan foto
4. Tombol back untuk kembali ke halaman home

Library yang digunakan dan tidak ada di kelas:
Untuk sort:
- import java.util.Collections;
Untuk data file:
- import java.io.File;
Untuk vibrate:
- import android.os.VibrationEffect;
- import android.os.Vibrator;
Untuk tes log:
- import android.util.Log;
Untuk search:
- import android.widget.SearchView;
Untuk akses storage:
- import android.os.Environment;
Untuk toolbar:
- import android.view.Menu;
- import android.view.MenuItem;
- import android.view.MenuInflater;
- import androidx.appcompat.widget.Toolbar;
Untuk music activity:
- import android.media.MediaPlayer;
Untuk seekbar:
- import android.widget.SeekBar;
Untuk dialogalert:
- import androidx.appcompat.app.AlertDialog;
- import android.content.DialogInterface;

- import android.content.Context;
- import android.content.pm.PackageManager;

lainnya:
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
