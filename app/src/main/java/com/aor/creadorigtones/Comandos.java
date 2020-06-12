package com.aor.creadorigtones;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import nl.bravobit.ffmpeg.ExecuteBinaryResponseHandler;
import nl.bravobit.ffmpeg.FFmpeg;


public class Comandos {
private Context context;
private TextView text;

    public Comandos(Context context, TextView text) {
        this.context = context;
        this.text = text;
    }

    public String getSplitCommand(String inputFileUrl, String outputFileUrl,
                                  String start, String end) {
        if ((TextUtils.isEmpty(inputFileUrl) && (TextUtils.isEmpty(outputFileUrl)))) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-i ")
                .append(inputFileUrl).append(" ")
                .append("-ss ")
                .append(start).append(" ")
                .append("-codec ")
                .append("copy ")
                .append("-t ")
                .append(end).append(" ")
                .append(outputFileUrl);
        return stringBuilder.toString();
    }

    public void Ejecutar(String[] comando, final String nombre_cancion){
        if (FFmpeg.getInstance(context).isSupported()) {

            FFmpeg ffmpeg = FFmpeg.getInstance(context);
            ffmpeg.execute(comando, new ExecuteBinaryResponseHandler() {


                @Override
                public void onStart() {}

                @Override
                public void onProgress(String message) {


                }

                @Override
                public void onFailure(String message) {
      System.out.println(message);
                    Toast.makeText(context, "Ocurrio un error cortando la cancion",
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess(String message) {
                    Toast.makeText(context, "Se guardo la cancion con el nombre: " + nombre_cancion,
                            Toast.LENGTH_LONG).show();
                }


                @Override
                public void onFinish() {

                }
            });


        } else {
            text.setText("No es soportado");
        }



    }
}
