package com.trabajo.carlos.memorycardgamematerial.listas;

import android.view.View;
import android.widget.TextView;

import com.trabajo.carlos.memorycardgamematerial.R;

/**
 * Created by Carlos Prieto on 02/03/2017.
 */

public class CustomViewHolder {

    TextView txvNombre, txvTiempo, txvDificultad;

    public CustomViewHolder(View v) {

        txvNombre = (TextView)v.findViewById(R.id.txvNombre);
        txvTiempo = (TextView)v.findViewById(R.id.txvTiempo);
        txvDificultad = (TextView)v.findViewById(R.id.txvDificultad);

    }

}
