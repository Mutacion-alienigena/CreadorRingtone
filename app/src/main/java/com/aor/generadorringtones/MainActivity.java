package com.aor.generadorringtones;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    Archivos archivos;
    TextView text;
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.titulo);
        archivos = new Archivos(getApplicationContext());
        boton = findViewById(R.id.button2);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivityForResult(Intent.createChooser(archivos.Buscar_archivo(Archivos.musica),
                        "Cancion a recortar"), Archivos.READ_REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode == Archivos.READ_REQUEST_CODE){

               Uri uri = data.getData();
               Dato dato = archivos.Get_MetaData(uri);
               String path = archivos.getPathFromURI(uri);
               File file = new File(path);
               File nuevo = new File(file.getParent() +  "/convertido.mp3");
               text.setText(file.getName());
               Comandos comandos = new Comandos(getApplicationContext(),text);
                String comando = comandos.getSplitCommand(path,nuevo.getAbsolutePath(),
                        "00:00:02","00:00:06");
               comandos.Ejecutar(comando);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }








}