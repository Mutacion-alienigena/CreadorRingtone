package com.aor.creadorigtones;

import android.util.Log;

import java.util.concurrent.TimeUnit;

public class Dato {
    private String nombre;
    private String size;
    private String album;
    private String titulo;
    private String duracion;
    private String imagen;


    public String getNombre() {
        return nombre;
    }


    public String getSize() {
        return size;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public void SetAlbum(String albumName) {
        this.album = albumName;
    }

    public String getAlbum() {
        return album;
    }

    public void SetTitulo(String extractMetadata) {
        this.titulo = extractMetadata;
    }

    public String getTitulo() {
        return titulo;
    }

    public void SetDuracion(String extractMetadata) {

        String parte_minutos = "";
        int minutes = Integer.parseInt(String.valueOf(TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(extractMetadata))));
        if(minutes < 10){
            parte_minutos = "0" + minutes;
        }else if(minutes >= 10){
            parte_minutos = String.valueOf(minutes);
        }
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(extractMetadata)) - (60 * minutes);
        String parte_segundos = "";
        if(seconds < 10){
          parte_segundos =  "0" +  seconds;
        } else if(seconds >= 10) {
            parte_segundos = String.valueOf(seconds);
        }


        this.duracion = "00:" + parte_minutos + ":" + parte_segundos;
    }

    public String getDuracion() {
        return duracion;
    }

    public void SetImagen(String extractMetadata) {
        this.imagen = extractMetadata;
    }

    public String getImagen() {
        return imagen;
    }
}
