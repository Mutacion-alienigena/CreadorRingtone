package com.aor.generadorringtones;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.nio.file.Path;

import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Archivos archivos;
    TextView text;
    EditText inicio;
    EditText Fin;
    Button boton;
    File file;
    String path;
    Button Cortar;
    Dato dato;
    Button abrir_cancion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.titulo);
        inicio = findViewById(R.id.inicio);
        inicio.setVisibility(View.GONE);
        Fin = findViewById(R.id.Fin);
        abrir_cancion = findViewById(R.id.Abrir_cancion);
        abrir_cancion.setVisibility(View.GONE);
        Fin.setVisibility(View.GONE);
        archivos = new Archivos(getApplicationContext());
        Cortar = findViewById(R.id.Cortar);
        boton = findViewById(R.id.button2);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivityForResult(Intent.createChooser(archivos.Buscar_archivo
                                (Archivos.musica), "Cancion a recortar"),
                        Archivos.READ_REQUEST_CODE);
            }
        });
        abrir_cancion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(path), "audio/*");
                startActivity(intent);
            }
        });
        Cortar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cortar_MP3();
            }
        });
        Cortar.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode == Archivos.READ_REQUEST_CODE){

               Uri uri = data.getData();
               inicio.setVisibility(View.VISIBLE);
               Fin.setVisibility(View.VISIBLE);
               dato = archivos.Get_MetaData(uri);
               path = archivos.getPathFromURI(uri);
               text.setText(path);
               abrir_cancion.setVisibility(View.VISIBLE);
               file = new File(path);
               Cortar.setVisibility(View.VISIBLE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Cortar_MP3(){
        File nuevo = new File(file.getParent() +  "/" + dato.getNombre().split(".")[0] + "_cut.mp3");
        text.setText(file.getName());
        Comandos comandos = new Comandos(getApplicationContext(),text);
        String[] command = comandos.getSplitCommand(path,nuevo.getAbsolutePath(),inicio.getText().
                toString(),Fin.getText().toString()).split(" ");
        comandos.Ejecutar(command);
    }








}