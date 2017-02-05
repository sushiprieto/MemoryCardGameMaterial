package com.trabajo.carlos.memorycardgamematerial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    ListView lsv_lista;
    ArrayList<String> lista;
    ArrayAdapter adaptador;

    Button btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_ranking);

        lsv_lista = (ListView)findViewById(R.id.lista);

        final LoginSQLHelper DDBB = new LoginSQLHelper(getApplicationContext(), null, null, 1);

        lista = DDBB.llenarLista();

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lsv_lista.setAdapter(adaptador);

        btnLimpiar = (Button)findViewById(R.id.btnLimpiar);
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DDBB.limpiarRegistros();

            }
        });

    }

}
