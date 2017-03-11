package com.trabajo.carlos.memorycardgamematerial.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.trabajo.carlos.memorycardgamematerial.R;
import com.trabajo.carlos.memorycardgamematerial.vistas.GameActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private ImageButton btnPlay;

    private String nombreLogin;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        btnPlay = (ImageButton)v.findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mostrarDialogoPersonalizado();

            }
        });

        return v;
    }

    /**
     * Método que crea un cuadro de diálogo personalizado
     */
    public void mostrarDialogoPersonalizado() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialogo_login, null);
        dialogBuilder.setView(dialogView);

        final EditText edtLogin = (EditText) dialogView.findViewById(R.id.edtLogin);

        dialogBuilder.setTitle("Login");
        dialogBuilder.setMessage("Introduzca su nombre");
        dialogBuilder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                //Comprobamos que no se inserte el campo vacio
                if (edtLogin.getText().toString().equals("")){

                    Toast.makeText(getActivity(), "Debe insertar un nombre", Toast.LENGTH_SHORT).show();

                }else{

                    //Recogemos el nombre introducido para enviarlo a la otra actividad
                    nombreLogin = edtLogin.getText().toString();

                    Intent intent = new Intent(getActivity(), GameActivity.class);

                    intent.putExtra("nombre", nombreLogin);

                    startActivity(intent);

                }

            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                dialog.cancel();

            }
        });

        AlertDialog dialogo = dialogBuilder.create();
        dialogo.show();

    }

}
