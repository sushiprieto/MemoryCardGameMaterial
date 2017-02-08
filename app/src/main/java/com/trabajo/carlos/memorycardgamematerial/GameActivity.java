package com.trabajo.carlos.memorycardgamematerial;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    Button btn4x4, btn6x6;

    FragmentManager manager;
    FragmentTransaction transaction;

    CuatroFragment frag4x4 = new CuatroFragment();
    SeisFragment frag6x6 = new SeisFragment();

    private final String tagfrag4x4 = "frag4x4";
    private final String tagfrag6x6 = "frag6x6";

    AlertDialog alerta;
    AlertDialog.Builder yesnoquestion;

    String nombreLogin;

    TextView txvNombreLogin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_game);

        btn4x4 = (Button)findViewById(R.id.btn4x4);
        btn6x6 = (Button)findViewById(R.id.btn6x6);

        txvNombreLogin = (TextView)findViewById(R.id.txtNombreLogin);

        //Creamos un Bundle donde guardar los datos recibidos
        Bundle recogerNombreLogin = getIntent().getExtras();

        //Obtenemos la cadena
        nombreLogin = recogerNombreLogin.getString("nombre");

        //Mostramos los datos
        txvNombreLogin.setText("Bienvenid@, " + nombreLogin);

        btn4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pregunta yes/no para confirmar que quiere iniciar una nueva partida
                yesnoquestion = new AlertDialog.Builder(GameActivity.this);
                yesnoquestion.setMessage("¿Quieres empezar una nueva partida?").setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            /**
                             * Primero cargamos el fragmen cuando pulse YES y luego recargamos la Activity para actualizar el cronómetro
                             * @param dialogInterface
                             * @param i
                             */
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //Cambio de un fragment a otro
                                manager = getSupportFragmentManager();
                                transaction = manager.beginTransaction();
                                transaction.replace(R.id.contenedor_fragment, frag4x4, tagfrag4x4);
                                transaction.commit();

                                recargar();

                            }
                        })
                        /**
                         * Si pulsa NO simplemente no hacemos nada
                         */
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.cancel();

                            }
                        });

                alerta = yesnoquestion.create();
                alerta.setTitle("NUEVA PARTIDA");
                alerta.show();

            }
        });

        btn6x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pregunta yes/no para confirmar que quiere iniciar una nueva partida
                yesnoquestion = new AlertDialog.Builder(GameActivity.this);
                yesnoquestion.setMessage("¿Quieres empezar una nueva partida?").setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            /**
                             * Primero cargamos el fragmen cuando pulse YES y luego recargamos la Activity para actualizar el cronómetro
                             * @param dialogInterface
                             * @param i
                             */
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //Cambio de un fragment a otro
                                manager = getSupportFragmentManager();
                                transaction = manager.beginTransaction();
                                transaction.replace(R.id.contenedor_fragment, frag6x6, tagfrag6x6);
                                transaction.commit();

                                recargar();

                            }
                        })
                        /**
                         * Si pulsa NO simplemente no hacemos nada
                         */
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.cancel();

                            }
                        });

                alerta = yesnoquestion.create();
                alerta.setTitle("NUEVA PARTIDA");
                alerta.show();

            }
        });

    }

    /**
     * Metodo para recargar la misma actividad
     */
    public void recargar(){

        this.recreate();

    }

}