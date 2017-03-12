package com.trabajo.carlos.memorycardgamematerial.bbdd;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Carlos Prieto on 02/03/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {

        super(context, Constantes.DB_NAME, null, Constantes.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(Constantes.CREATE_TB);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(Constantes.DROP_TB);
        onCreate(db);

    }
}
