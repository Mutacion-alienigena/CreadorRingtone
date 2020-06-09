package com.aor.creadorigtones;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        inicio = findViewById(R.id.inicio);
        fin = findViewById(R.id.fin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
            case R.id.abrir_cancion:
                startActivityForResult(Intent.createChooser(archivos.Buscar_archivo
                                (Archivos.musica), "Cancion a abrir"),
                        Archivos.READ_REQUEST_CODE);
                return true;
            case R.id.reproducir:
                try{
                    Intent intent = new Intent();
                    intent.setAction(android.content.Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(path), "audio/*");
                    startActivity(intent);

                }catch (ActivityNotFoundException exception){
                    Toast.makeText(getApplicationContext(), "No hay una aplicacion vinculada "
                            + "para abrir la cancion", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.action_settings:

                return true;
            case R.id.cortar:
                Cortar_MP3();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode == Archivos.READ_REQUEST_CODE){

            Uri uri = data.getData();
            dato = archivos.Get_MetaData(uri);
            try {
                path = archivos.getPath(uri);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            text = findViewById(R.id.titulo);
            text.setText("path" + path);
            //file = new File(path);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Cortar_MP3(){
        File nuevo = new File(file.getParent() +  "/" + dato.getNombre()
                .split(".")[0] + "_cut.mp3");
        text.setText(file.getName());
        Comandos comandos = new Comandos(getApplicationContext(),text);
        String[] command = comandos.getSplitCommand(path,nuevo.getAbsolutePath(),inicio.getText().
                toString(),fin.getText().toString()).split(" ");
        comandos.Ejecutar(command);
    }
}