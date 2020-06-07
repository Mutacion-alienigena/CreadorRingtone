package com.aor.generadorringtones;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

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
        stringBuilder.append("-y ")
                .append("-i ")
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

    public void Ejecutar( String comando){
        if (FFmpeg.getInstance(context).isSupported()) {

            FFmpeg ffmpeg = FFmpeg.getInstance(context);
            String[] cmd = new String[1];
            cmd[0] = comando;
            ffmpeg.execute(cmd, new ExecuteBinaryResponseHandler() {

                @Override
                public void onStart() {}

                @Override
                public void onProgress(String message) {
                    text.setText(message);
                }

                @Override
                public void onFailure(String message) {
                    text.setText(message);
                }

                @Override
                public void onSuccess(String message) {
                    text.setText(message);
                }


                @Override
                public void onFinish() {

                }
            });


        } else {
            text.setText("No es soportado, por pete, comprate un mejor celular rata");
        }



    }
}
