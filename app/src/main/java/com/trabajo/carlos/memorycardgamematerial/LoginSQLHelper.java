package com.trabajo.carlos.memorycardgamematerial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by cprieto on 26/01/17.
 */

public class LoginSQLHelper extends SQLiteOpenHelper {

    static final String COLUMN_ID = "id";
    static final String COLUMN_NOMBRE = "nombre";
    static final String COLUMN_TIEMPO = "tiempo";
    static final String DB_NAME = "ranking.db";
    static final String TABLE_NAME = "personas";

    //Sentencia SQL para crear la tabla de Nombres
    String sqlCreate = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_NOMBRE + " TEXT, " + COLUMN_TIEMPO + " TEXT";

    //String sqlCreate = "CREATE TABLE personas(nombre TEXT, tiempo TEXT)";

    public LoginSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "personas", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creamos la BBDD
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS personas");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);

    }

    public String insertar(String nombre, String tiempo){

        String mensaje = "";

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", nombre);
        contenedor.put("tiempo", tiempo);

        try {

            database.insertOrThrow("personas", null, contenedor);
            mensaje="Ingresado Correctamente";

        }catch (SQLException e){

            mensaje="No Ingresado";

        }

        database.close();

        return mensaje;

    }

    public ArrayList llenarLista(){

        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();

        String select = "SELECT * FROM personas";
        Cursor registros = database.rawQuery(select, null);

        if(registros.moveToFirst()){

            do{

                lista.add("Nombre: " + registros.getString(0) + " Tiempo: " + registros.getString(1));

            }while(registros.moveToNext());

        }

        return lista;

    }



}
