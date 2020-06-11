package com.aor.creadorigtones.ui.gallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aor.creadorigtones.Archivos;
import com.aor.creadorigtones.Comandos;
import com.aor.creadorigtones.R;


public class GalleryFragment extends Fragment implements View.OnClickListener{

    private GalleryViewModel model;
    private TextView textView;
    private TextView url_texto_buscar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        model = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        url_texto_buscar = root.findViewById(R.id.Url_youtube);
        Button Buuscar_Youtube_Boton = (Button) root.findViewById(R.id.Buscar_Youtube);
        Buuscar_Youtube_Boton.setOnClickListener(this);
        return root;
    }

    public void Buscar_URL(TextView url_textview) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Buscar_Youtube:
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                   this.Buscar_URL(url_texto_buscar);
                break;

    }
}


}
