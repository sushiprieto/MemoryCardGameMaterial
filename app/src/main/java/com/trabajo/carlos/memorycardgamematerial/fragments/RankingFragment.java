package com.trabajo.carlos.memorycardgamematerial.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.trabajo.carlos.memorycardgamematerial.R;
import com.trabajo.carlos.memorycardgamematerial.bbdd.DBAdapter;
import com.trabajo.carlos.memorycardgamematerial.datos.Persona;
import com.trabajo.carlos.memorycardgamematerial.listas.CustomAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {

    private ListView lsvLista;
    private ArrayList<Persona> personas = new ArrayList<>();
    private CustomAdapter adapter;

    private Button btnLimpiar;

    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ranking, container, false);

        lsvLista = (ListView)v.findViewById(R.id.lsvLista);

        btnLimpiar = (Button)v.findViewById(R.id.btnLimpiar);

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                limpiarRegistros();

            }
        });

        //Inicializamos el adapter de la lista
        adapter = new CustomAdapter(getActivity(), personas);

        this.getPersonas();

        return v;
    }

    /**
     * Metodo donde obtenemos todos los nombres y tiempos de la BBDD
     */
    private void getPersonas()
    {

        personas.clear();
        DBAdapter db = new DBAdapter(getActivity());
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

        DBAdapter db = new DBAdapter(getActivity());
        db.openDB();

        boolean deleted = db.limpiarRegistros();
        db.closeDB();

        //Si se ha borrado bien
        if(deleted)
        {

            getPersonas();

        }else {

            Toast.makeText(getActivity(), "No se puede borrar", Toast.LENGTH_SHORT).show();

        }
    }

}
