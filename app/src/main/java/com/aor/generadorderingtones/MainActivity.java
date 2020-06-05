package com.aor.generadorderingtones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
  Archivos archivos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        archivos = new Archivos(getApplicationContext());
        startActivityForResult(Intent.createChooser(archivos.Buscar_archivo(Archivos.musica),"Cancion a recortar"), Archivos.READ_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode == Archivos.READ_REQUEST_CODE){

               Uri uri = data.getData();
               Dato dato = archivos.Get_MetaData(uri);
               TextView text = findViewById(R.id.titulo);
               text.setText(dato.getNombre());

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}