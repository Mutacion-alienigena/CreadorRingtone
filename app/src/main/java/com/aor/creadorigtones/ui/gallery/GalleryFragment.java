package com.aor.creadorigtones.ui.gallery;

import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aor.creadorigtones.MainActivity;
import com.aor.creadorigtones.R;
import com.yausername.youtubedl_android.YoutubeDL;
import com.yausername.youtubedl_android.YoutubeDLException;
import com.yausername.youtubedl_android.YoutubeDLRequest;

import java.io.File;


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



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Buscar_Youtube:
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                try {

                    YoutubeDL.getInstance().init(MainActivity.get_aplication);
                    File youtubeDLDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "youtubedl-android");
                    YoutubeDLRequest request = new YoutubeDLRequest(url_texto_buscar.getText().toString());
                    request.addOption("-o", youtubeDLDir.getAbsolutePath() + "/%(title)s.%(ext)s");
                    YoutubeDL.getInstance().execute(request, (progress, etaInSeconds) -> {
                        System.out.println(String.valueOf(progress) + "% (ETA " + String.valueOf(etaInSeconds) + " seconds)");
                    });
                } catch (YoutubeDLException | InterruptedException e) {
                    Log.e("Fallo", "failed to initialize youtubedl-android", e);
                }
                break;

    }
}


}
