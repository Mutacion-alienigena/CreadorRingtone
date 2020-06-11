package com.aor.creadorigtones;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aor.creadorigtones.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import  android.widget.Button;
import java.io.File;
import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity {
    Archivos archivos;
    File file;
    String path;
    private AppBarConfiguration mAppBarConfiguration;
    private Dato dato;
    private TextView text;
    private TextView fin;
    private  DrawerLayout drawer;
    private TextView inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer= findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        archivos = new Archivos(getApplicationContext());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        Button abrir_cancion = findViewById(R.id.abrir);
        abrir_cancion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivityForResult(Intent.createChooser(archivos.Buscar_archivo
                                (Archivos.musica), "Cancion a abrir"),
                        Archivos.READ_REQUEST_CODE);
            }
        });

        Button cortar = findViewById(R.id.Cortar_cancion);
        cortar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                HomeFragment fragment = new HomeFragment();
                TextView nombre_cancion = findViewById(R.id.nombre_cancion);
                fragment.setContext(getApplicationContext());
                fragment.Cortar_MP3(file,path,text,inicio,fin,nombre_cancion);
            }
        });

        Button Generar_rigntone = findViewById(R.id.Generar_rigntone);
        Generar_rigntone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Seleccionar Tono");
                    startActivityForResult(intent,5);
                }catch (ActivityNotFoundException exception){
                    Toast.makeText(getApplicationContext(), "No hay una aplicacion vinculada "
                            + "para asginarle el tono", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button Reproducir_cancion = findViewById(R.id.Reproducir_cancion);
        Reproducir_cancion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try{
                    Intent intent_abrir_cancion = new Intent();
                    intent_abrir_cancion.setAction(android.content.Intent.ACTION_VIEW);
                    intent_abrir_cancion.setDataAndType(Uri.parse(path), "audio/*");
                    startActivity(intent_abrir_cancion);

                }catch (ActivityNotFoundException exception){
                    Toast.makeText(getApplicationContext(), "No hay una aplicacion vinculada "
                            + "para abrir la cancion", Toast.LENGTH_LONG).show();
                }
            }
        });


        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode == Archivos.READ_REQUEST_CODE){
            Definir_inputs_Cortar();
            Uri uri = data.getData();
            try {
                dato = archivos.Get_MetaData(uri);
                fin.setText(dato.getDuracion());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            try {
                path = archivos.getPath(uri);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            text = findViewById(R.id.titulo);
            text.setText(dato.getAlbum());
            TextView titulo = findViewById(R.id.text_home);
            titulo.setText(dato.getTitulo());
            file = new File(path);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    public void Definir_inputs_Cortar(){
        inicio = findViewById(R.id.inicio);
        fin = findViewById(R.id.fin);
    }



}