package com.trabajo.carlos.memorycardgamematerial.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Carlos Prieto on 02/03/2017.
 */

public class DBAdapter {

    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper = new DBHelper(c);
    }

    /**
     * Metodo que abre una conexion
     */
    public void openDB()
    {
        try
        {

            db = helper.getWritableDatabase();

        }catch (SQLException e)
        {

        }
    }

    /**
     * Metodo que cierra la BBDD
     */
    public void closeDB()
    {
        try
        {

            helper.close();

        }catch (SQLException e)
        {

        }
    }

    /**
     * Metodo para insertar un nombre y un tiempo
     * @param name
     * @return
     */
    public boolean add(String name, String time, String dificultad)
    {
        try
        {

            ContentValues cv = new ContentValues();
            cv.put(Constantes.COLUMN_NOMBRE, name);
            cv.put(Constantes.COLUMN_TIEMPO, time);
            cv.put(Constantes.COLUMN_DIFICULTAD, dificultad);

            long result = db.insert(Constantes.TABLE_NAME, Constantes.COLUMN_ID, cv);

            if(result>0)
            {
                return true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Metodo que hace un select a la BBDD
     * @return
     */
    public Cursor retrieve()
    {
        String[] columns = {Constantes.COLUMN_ID, Constantes.COLUMN_NOMBRE, Constantes.COLUMN_TIEMPO, Constantes.COLUMN_DIFICULTAD};

        Cursor c = db.query(Constantes.TABLE_NAME, columns, null, null, null, null, null);
        return c;
    }

    /**
     * Metodo que elimina un campo de la BBDD
     * @return
     */
    public boolean limpiarRegistros()
    {
        try
        {

            int result = db.delete(Constantes.TABLE_NAME, null, null);

            if(result > 0)
            {
                return true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

}
