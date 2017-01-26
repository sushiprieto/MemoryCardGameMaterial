package com.trabajo.carlos.memorycardgamematerial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cprieto on 26/01/17.
 */

public class LoginSQLHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Nombres
    String sqlCreate = "CREATE TABLE Nombres (nombre TEXT, tiempo INTEGER)";

    public LoginSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creamos la BBDD
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Nombres");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);

    }
}
