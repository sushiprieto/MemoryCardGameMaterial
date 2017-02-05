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

    static final String COLUMN_NOMBRE = "nombre";
    static final String COLUMN_TIEMPO = "tiempo";
    static final String DB_NAME = "ranking.db";
    static final String TABLE_NAME = "personas";
    private static final int DATABASE_VERSION = 2;

    //Sentencia SQL para crear la tabla de Nombres
    String sqlCreate = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_NOMBRE + " TEXT, " + COLUMN_TIEMPO + " TEXT";

    //String sqlCreate = "CREATE TABLE personas(nombre TEXT, tiempo TEXT)";

    public LoginSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, TABLE_NAME, factory, DATABASE_VERSION);
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

    /**
     * Método para insertar campos en la BBDD
     * @param nombre
     * @param tiempo
     * @return
     */
    public String insertar(String nombre, String tiempo){

        String mensaje = "";

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put(COLUMN_NOMBRE, nombre);
        contenedor.put(COLUMN_TIEMPO, tiempo);

        try {

            database.insertOrThrow(TABLE_NAME, null, contenedor);
            mensaje="Ingresado Correctamente";

        }catch (SQLException e){

            mensaje="No Ingresado";

        }

        database.close();

        return mensaje;

    }

    /**
     * Método para rellenar el ListView con los valores introducidos
     * @return
     */
    public ArrayList llenarLista(){

        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();

        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor registros = database.rawQuery(select, null);

        if(registros.moveToFirst()){

            do{

                lista.add("Nombre: " + registros.getString(0) + " Tiempo: " + registros.getString(1) + " segundos");

            }while(registros.moveToNext());

        }

        return lista;

    }

    /**
     * Método que limpia los datos de la tabla
     * @return
     */
    public String limpiarRegistros(){

        String mensaje ="";

        SQLiteDatabase database = this.getWritableDatabase();

        int cantidad =database.delete(TABLE_NAME, null, null);

        if (cantidad !=0){

            mensaje="Eliminado Correctamente";

        }
        else{

            mensaje = "No existe";
        }

        database.close();
        return mensaje;

    }

}
