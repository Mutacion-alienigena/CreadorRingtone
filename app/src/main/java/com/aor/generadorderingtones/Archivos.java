package com.aor.generadorderingtones;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;



public class Archivos {
    public static final int READ_REQUEST_CODE = 42;

    public static final String musica = "audio/*";
    private Context context;

    public Intent Buscar_archivo(String formato) {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(formato);
        return intent;

    }

    Archivos(Context context){
        this.context = context;
    }

    public Dato Get_MetaData(Uri uri) {

        Dato dato = new Dato();
        Cursor cursor = context.getContentResolver()
                .query(uri, null, null, null, null, null);

        try {

            if (cursor != null && cursor.moveToFirst()) {

                String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                dato.setNombre(displayName);

                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);

                String size = null;
                if (!cursor.isNull(sizeIndex)) {

                    size = cursor.getString(sizeIndex);
                    dato.setSize(size);
                } else {
                    size = "Unknown";
                }


            }
        } finally {
            cursor.close();
        }
        return  dato;
    }



}
