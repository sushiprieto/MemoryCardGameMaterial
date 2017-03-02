package com.trabajo.carlos.memorycardgamematerial.bbdd;

/**
 * Created by Carlos Prieto on 02/03/2017.
 */

public class Constantes {

    static final String COLUMN_ID = "id";
    static final String COLUMN_NOMBRE = "nombre";
    static final String COLUMN_TIEMPO = "tiempo";
    static final String COLUMN_DIFICULTAD = "dificultad";

    static final String DB_NAME = "ranking.db";

    static final String TABLE_NAME = "personas";

    static final int DATABASE_VERSION = 2;

    //Crear tabla
    static final String CREATE_TB = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOMBRE + " TEXT NOT NULL, " + COLUMN_TIEMPO + " TEXT, " + COLUMN_DIFICULTAD + " TEXT);";

    //Borrar tabla
    static final String DROP_TB = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
