package com.trabajo.carlos.memorycardgamematerial;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * Created by Carlos Prieto on 25/01/2017.
 */

public class MemoryButton4x4 extends Button {

    protected int fila;
    protected int columna;
    protected int frontDrawableId;

    protected boolean isFlipped;
    protected boolean isMatched;

    protected Drawable front;
    protected Drawable back;

    public MemoryButton4x4(Context context, int fila, int columna, int frontImageDrawableId) {
        super(context);

        fila = fila;
        columna = columna;
        frontDrawableId = frontImageDrawableId;

        front = AppCompatDrawableManager.get().getDrawable(context, frontImageDrawableId);
        back = AppCompatDrawableManager.get().getDrawable(context, R.drawable.img_dorso);

        setBackground(back);

        GridLayout.LayoutParams grid = new GridLayout.LayoutParams(GridLayout.spec(fila), GridLayout.spec(columna));

        //Aqui cambiamos las propiedades del GridLayout
        grid.width = (int)getResources().getDisplayMetrics().density * 50;
        grid.height = (int)getResources().getDisplayMetrics().density * 50;
        grid.bottomMargin = (int)getResources().getDisplayMetrics().density * 10;
        grid.rightMargin = (int)getResources().getDisplayMetrics().density * 10;

        setLayoutParams(grid);

    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public int getFrontDrawableId() {
        return frontDrawableId;
    }

    /**
     * Metodo para voltear la carta
     */
    public void flip(){

        if (isMatched){
            return;
        }

        if (isFlipped){
            setBackground(back);
            isFlipped = false;
        }else{
            setBackground(front);
            isFlipped = true;
        }

    }


}

