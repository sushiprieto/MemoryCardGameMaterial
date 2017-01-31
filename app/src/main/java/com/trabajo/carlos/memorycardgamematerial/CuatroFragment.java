package com.trabajo.carlos.memorycardgamematerial;

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

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class CuatroFragment extends Fragment {

    private static final String TAG = CuatroFragment.class.getSimpleName();

    private int ganar = 0;

    private int numberOfElements;

    private Chronometer cronometrito;

    TextView txvCronometro;

    private MemoryButton4x4[] buttons;

    private int[] buttonGraphicLocation;
    private int[] buttonGraphics;

    private MemoryButton4x4 selectedButton1;
    private MemoryButton4x4 selectedButton2;

    private boolean isBussy = false;

    GridLayout gridJuego;

    public CuatroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cuatro, container, false);

        gridJuego = (GridLayout) v.findViewById(R.id.contenedorGrid4x4);

        cronometrito = (Chronometer) v.findViewById(R.id.chrCronometro4x4);
        txvCronometro = (TextView) v.findViewById(R.id.txvCronometro4x4);

        int numColumns = gridJuego.getColumnCount();
        int numRows = gridJuego.getRowCount();

        numberOfElements = numColumns * numRows;

        buttons = new MemoryButton4x4[numberOfElements];

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

        buttonGraphicLocation = new int[numberOfElements];

        shuffleButtonGraphics();

        for (int r = 0; r < numRows; r++) {

            for (int c = 0; c < numColumns; c++) {

                MemoryButton4x4 tempButton = new MemoryButton4x4(getActivity(), r, c, buttonGraphics[buttonGraphicLocation[r * numColumns + c]]);
                tempButton.setId(View.generateViewId());

                tempButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Ponemos en marcha el cronometro
                        cronometrito.start();

                        if (isBussy)
                            return;

                        MemoryButton4x4 button = (MemoryButton4x4) v;

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

                        //Este if cada vez que encuentra una pareja deja las imagenes visibles
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
                            if (ganar == 8) {

                                //Toast.makeText(getActivity(), "HAS GANAO CABRON", Toast.LENGTH_SHORT).show();

                                //Paramos el cronometro y lo metemos en una variable para mostrar el tiempo que ha tardado
                                cronometrito.stop();
                                long tiempo = SystemClock.elapsedRealtime() - cronometrito.getBase();

                                //int hours = (int) (tiempo / 3600000);
                                //int minutes = (int) (tiempo - hours * 3600000) / 60000;
                                //int seconds = (int) (tiempo - hours * 3600000 - minutes * 60000) / 1000;

                                long segundos = 0;

                                if (tiempo >= 1000) {
                                    segundos = tiempo / 1000;
                                }

                                //int seconds = (int)(saveTime/1000 % 60);
                                txvCronometro.setText("Has tardado: " + segundos + " segundos");

                            }

                            return;

                            //Este else es por si no encuentra la pareja se le añade un timer para darle la vuelta
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

    protected void shuffleButtonGraphics() {

        Random rand = new Random();

        for (int i = 0; i < numberOfElements; i++) {

            buttonGraphicLocation[i] = i % (numberOfElements / 2);

        }

        for (int i = 0; i < numberOfElements; i++) {

            int temp = buttonGraphicLocation[i];

            int swapIndex = rand.nextInt(16);

            buttonGraphicLocation[i] = buttonGraphicLocation[swapIndex];

            buttonGraphicLocation[swapIndex] = temp;

        }

    }

}

