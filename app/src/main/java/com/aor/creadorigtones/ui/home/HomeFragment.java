package com.aor.creadorigtones.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aor.creadorigtones.Comandos;
import com.aor.creadorigtones.R;

import java.io.File;


public class HomeFragment extends Fragment {
    public static TextView textView;
    private HomeViewModel model;
    public View root;
   private Context context;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        model = new ViewModelProvider.NewInstanceFactory().create(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
          textView = root.findViewById(R.id.text_home);

        return root;
    }


    public void Cortar_MP3(File file, String path,TextView text,TextView inicio,TextView fin,TextView nombre_cancion){
        nombre_cancion.setText(nombre_cancion.getText().toString().replace(' ','_'));
        File nuevo = new File(file.getParent() +  "/" + nombre_cancion.getText() + ".mp3");
        text.setText(file.getName());
        Comandos comandos = new Comandos(context,text);
        String[] command = comandos.getSplitCommand(path,nuevo.getAbsolutePath(),inicio.getText().
                toString(),fin.getText().toString()).split(" ");
        comandos.Ejecutar(command,nombre_cancion.getText().toString());
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public HomeViewModel getModel(){
        return this.model;
    }



}