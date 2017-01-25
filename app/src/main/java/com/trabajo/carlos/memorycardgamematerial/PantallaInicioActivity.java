package com.trabajo.carlos.memorycardgamematerial;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PantallaInicioActivity extends AppCompatActivity {

    //ProgressBar carga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pantalla_inicio);

        //carga = (ProgressBar)findViewById(R.id.proCarga);
        //carga.setVisibility(carga.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(PantallaInicioActivity.this, MainActivity.class);
                startActivity(intent);

            }
        }, 3000);

    }
}
