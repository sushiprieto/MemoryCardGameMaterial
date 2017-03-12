package com.trabajo.carlos.memorycardgamematerial.utilidades;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;

import com.trabajo.carlos.memorycardgamematerial.R;

/**
 * Created by Carlos Prieto on 25/01/2017.
 */

public class MemoryButton4x4 extends android.support.v7.widget.AppCompatButton {

    public int frontDrawableId;

    public boolean isFlipped;
    public boolean isMatched;

    public Drawable front;
    public Drawable back;

    public MemoryButton4x4(Context context, int fila, int columna, int frontImageDrawableId) {
        super(context);

        frontDrawableId = frontImageDrawableId;

        front = AppCompatDrawableManager.get().getDrawable(context, frontImageDrawableId);
        back = AppCompatDrawableManager.get().getDrawable(context, R.drawable.poker);

        setBackground(back);

        GridLayout.LayoutParams grid = new GridLayout.LayoutParams(GridLayout.spec(fila), GridLayout.spec(columna));

        //Aqui cambiamos las propiedades del GridLayout
        grid.width = (int) getResources().getDisplayMetrics().density * 50;
        grid.height = (int) getResources().getDisplayMetrics().density * 50;
        grid.bottomMargin = (int) getResources().getDisplayMetrics().density * 10;
        grid.rightMargin = (int) getResources().getDisplayMetrics().density * 10;

        setLayoutParams(grid);

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
    public void flip() {

        if (isMatched) {
            return;
        }

        if (isFlipped) {
            setBackground(back);
            isFlipped = false;
        } else {
            setBackground(front);
            isFlipped = true;
        }

    }


}

