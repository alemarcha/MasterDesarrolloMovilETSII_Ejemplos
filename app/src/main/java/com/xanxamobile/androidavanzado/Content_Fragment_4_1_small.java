package com.xanxamobile.androidavanzado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Content_Fragment_4_1_small extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content__fragment_4_1_small);
        Intent tarea =getIntent();
        String nombre=tarea.getStringExtra("texto");
        TextView texto = (TextView)findViewById(R.id.EditTextDescription);
        texto.setText(nombre);
    }




}
