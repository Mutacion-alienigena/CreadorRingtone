package com.aor.creadorigtones.ui.home;

import android.content.Context;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;

public class HomeViewModel extends ViewModel {

    private TextView  text;
    private MutableLiveData<String> mText;
    public Context context;
    private TextView nombre_cancion;
    private File file;
    private TextView fin;
    private String path;
    private TextView inicio;

    public void SetFile(File file){
        this.file = file;
    }
    public void SetPath(String path){
        this.path = path;
    }

    public void SetInicio(TextView inicio){
        this.inicio = inicio;
    }

    public void setFin(TextView fin){
        this.fin = fin;
    }

    public HomeViewModel() {
        mText = new MutableLiveData<>();
    }

    public MutableLiveData<String> getText() {

        return mText;
    }


    public TextView getNombre_cancion() {
        return nombre_cancion;
    }

    public void setNombre_cancion(TextView nombre_cancion) {
        this.nombre_cancion = nombre_cancion;
    }

    public void setText(TextView text) {
        this.text = text;
    }
    public TextView getText_element(){
        return this.text;
    }

    public File getfile() {
        return this.file;
    }

    public TextView Getfin() {
        return this.fin;

    }

    public String Getpath() {
        return this.path;
    }

    public TextView Getinicio() {
        return this.inicio;
    }
}