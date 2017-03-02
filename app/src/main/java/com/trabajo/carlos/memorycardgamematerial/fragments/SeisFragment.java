package com.trabajo.carlos.memorycardgamematerial.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trabajo.carlos.memorycardgamematerial.bbdd.LoginSQLHelper;
import com.trabajo.carlos.memorycardgamematerial.utilidades.MemoryButton6x6;
import com.trabajo.carlos.memorycardgamematerial.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeisFragment extends Fragment {

    private static final String TAG = CuatroFragment.class.getSimpleName();

    private int ganar = 0;

    private Chronometer cronometrito;

    TextView txvCronometro;

    private int numberOfElements;

    private MemoryButton6x6[] buttons;

    private int[] buttonGraphicLocation;
    private int[] buttonGraphics;

    private MemoryButton6x6 selectedButton1;
    private MemoryButton6x6 selectedButton2;

    private boolean isBussy = false;

    GridLayout gridJuego;

    public SeisFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_seis, container, false);

        gridJuego = (GridLayout)v.findViewById(R.id.contenedorGrid6x6);

        cronometrito = (Chronometer)v.findViewById(R.id.chrCronometro6x6);
        txvCronometro = (TextView)v.findViewById(R.id.txvCronometro6x6);

        int numColumns = gridJuego.getColumnCount();
        int numRows = gridJuego.getRowCount();

        numberOfElements = numColumns * numRows;

        buttons = new MemoryButton6x6[numberOfElements];

        //Cargamos las imagenes
        buttonGraphics = new int[numberOfElements / 2];
        buttonGraphics[0] = R.drawable.card1;
        buttonGraphics[1] = R.drawable.card2;
        buttonGraphics[2] = R.drawable.card3;
        buttonGraphics[3] = R.drawable.card4;
        buttonGraphics[4] = R.drawable.card5;
        buttonGraphics[5] = R.drawable.card6;
        buttonGraphics[6] = R.drawable.card7;
        buttonGraphics[7] = R.drawable.card8;
        buttonGraphics[8] = R.drawable.card9;
        buttonGraphics[9] = R.drawable.card10;
        buttonGraphics[10] = R.drawable.card11;
        buttonGraphics[11] = R.drawable.card12;
        buttonGraphics[12] = R.drawable.card13;
        buttonGraphics[13] = R.drawable.card14;
        buttonGraphics[14] = R.drawable.card15;
        buttonGraphics[15] = R.drawable.card16;
        buttonGraphics[16] = R.drawable.card17;
        buttonGraphics[17] = R.drawable.card18;

        buttonGraphicLocation = new int[numberOfElements];

        shuffleButtonGraphics();

        for (int r = 0; r < numRows; r++){

            for (int c = 0; c < numColumns; c++){

                MemoryButton6x6 tempButton = new MemoryButton6x6(getActivity(), r, c, buttonGraphics[buttonGraphicLocation[r * numColumns + c]]);
                tempButton.setId(View.generateViewId());

                tempButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Ponemos en marcha el cronometro
                        cronometrito.start();

                        if (isBussy)
                            return;

                        MemoryButton6x6 button = (MemoryButton6x6) v;

                        if (button.isMatched)
                            return;

                        if (selectedButton1 == null) {

                            selectedButton1 = button;
                            selectedButton1.flip();
                            return;

                        }

                        if (selectedButton1.getId() == button.getId()) {

                            return;

                        }

                        if (selectedButton1.getFrontDrawableId() == button.getFrontDrawableId()) {

                            //Compruebo si ha ganao el colega
                            ganar++;
                            Log.d(TAG, String.valueOf(ganar));

                            button.flip();

                            button.setMatched(true);
                            selectedButton1.setMatched(true);

                            selectedButton1.setEnabled(false);
                            button.setEnabled(false);

                            selectedButton1 = null;

                            //Le mando un mensajito pa que se entere que ha ganado
                            if(ganar == 18){

                                //Paramos el cronometro y lo metemos en una variable para mostrar el tiempo que ha tardado
                                cronometrito.stop();
                                long tiempo = SystemClock.elapsedRealtime() - cronometrito.getBase();
                                long segundos = 0;

                                if (tiempo >= 1000) {
                                    segundos = tiempo / 1000;
                                }

                                txvCronometro.setText("Has tardado: " + segundos + " segundos");

                                //Casteamos a String los segundos
                                String resultado = String.valueOf(segundos);

                                //Creamos un Bundle para recoger el nombre que ingresamos en el MainActivity
                                Bundle recogerNombreLogin = getActivity().getIntent().getExtras();
                                String nombreLogin = recogerNombreLogin.getString("nombre");

                                //Lo insertamos en la BBDD
                                LoginSQLHelper DDBB = new LoginSQLHelper(getActivity().getApplicationContext(), null, null, 1);
                                String mensaje = DDBB.insertar(nombreLogin, resultado);

                                //Mostramos mensaje de confirmacion
                                Toast.makeText(getActivity().getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();

                            }

                            return;

                        } else {

                            selectedButton2 = button;
                            selectedButton2.flip();
                            isBussy = true;

                            final Handler handler = new Handler();

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    selectedButton2.flip();
                                    selectedButton1.flip();
                                    selectedButton1 = null;
                                    selectedButton2 = null;
                                    isBussy = false;
                                }
                            }, 500);

                        }

                    }
                });
                buttons[r * numColumns + c] = tempButton;
                gridJuego.addView(tempButton);

            }

        }

        return v;
    }

    protected void shuffleButtonGraphics(){

        Random rand = new Random();

        for (int i = 0; i < numberOfElements; i++){

            buttonGraphicLocation[i] = i % (numberOfElements / 2);

        }

        for (int i = 0; i < numberOfElements; i++){

            int temp = buttonGraphicLocation[i];

            int swapIndex = rand.nextInt(16);

            buttonGraphicLocation[i] = buttonGraphicLocation[swapIndex];

            buttonGraphicLocation[swapIndex] = temp;

        }

    }

}