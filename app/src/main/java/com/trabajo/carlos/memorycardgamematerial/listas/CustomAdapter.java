package com.trabajo.carlos.memorycardgamematerial.listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.trabajo.carlos.memorycardgamematerial.R;
import com.trabajo.carlos.memorycardgamematerial.datos.Persona;

import java.util.ArrayList;

/**
 * Created by Carlos Prieto on 02/03/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Persona> personas;
    private LayoutInflater inflater;
    private Persona persona;

    public CustomAdapter(Context context, ArrayList<Persona> personas) {

        this.context = context;
        this.personas = personas;

    }

    @Override
    public int getCount() {
        return personas.size();
    }

    @Override
    public Object getItem(int position) {
        return personas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(inflater == null)
        {
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null)
        {
            //Inflamos la lista
            convertView = inflater.inflate(R.layout.fila_lista, parent, false);
        }

        //Bindeamos los datos
        CustomViewHolder holder = new CustomViewHolder(convertView);
        holder.txvNombre.setText(personas.get(position).getNombre());
        holder.txvTiempo.setText(personas.get(position).getTiempo() + "s");
        holder.txvDificultad.setText(personas.get(position).getDificultad());

        return convertView;
    }

    public int getSelectedItemID()
    {
        return persona.getId();
    }

    public String getSelectedItemName()
    {
        return persona.getNombre();
    }

    public String getSelectedItemTime()
    {
        return persona.getTiempo();
    }

    public String getSelectedItemDificultad()
    {
        return persona.getDificultad();
    }

}
