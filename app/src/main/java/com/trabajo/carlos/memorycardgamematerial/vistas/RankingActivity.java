package com.trabajo.carlos.memorycardgamematerial.vistas;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.trabajo.carlos.memorycardgamematerial.R;
import com.trabajo.carlos.memorycardgamematerial.bbdd.DBAdapter;
import com.trabajo.carlos.memorycardgamematerial.datos.Persona;
import com.trabajo.carlos.memorycardgamematerial.listas.CustomAdapter;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    private ListView lsvLista;
    private ArrayList<Persona> personas = new ArrayList<>();
    private CustomAdapter adapter;

    private Button btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_ranking);

        lsvLista = (ListView)findViewById(R.id.lsvLista);

        btnLimpiar = (Button)findViewById(R.id.btnLimpiar);

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                limpiarRegistros();

            }
        });

        //Inicializamos el adapter de la lista
        adapter = new CustomAdapter(this, personas);

        this.getPersonas();

    }

    /**
     * Metodo donde obtenemos todos los nombres y tiempos de la BBDD
     */
    private void getPersonas()
    {

        personas.clear();
        DBAdapter db = new DBAdapter(this);
        db.openDB();

        Cursor c = db.retrieve();
        Persona persona = null;

        //Va recorriendo el cursor en busca de datos
        while (c.moveToNext())
        {

            int id = c.getInt(0);
            String name = c.getString(1);
            String time = c.getString(2);
            String dificultad = c.getString(3);

            persona = new Persona();
            persona.setId(id);
            persona.setNombre(name);
            persona.setTiempo(time);
            persona.setDificultad(dificultad);

            personas.add(persona);

        }

        db.closeDB();

        //Añadimos a la lista
        lsvLista.setAdapter(adapter);

    }

    /**
     * Metodo que borra un nombre de la BBDD
     */
    private void limpiarRegistros()
    {

        DBAdapter db = new DBAdapter(this);
        db.openDB();

        boolean deleted = db.limpiarRegistros();
        db.closeDB();

        //Si se ha borrado bien
        if(deleted)
        {

            getPersonas();

        }else {

            Toast.makeText(this, "No se puede borrar", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * Metodo para recargar la misma actividad
     */
    public void recargar(){

        this.recreate();

    }

}
