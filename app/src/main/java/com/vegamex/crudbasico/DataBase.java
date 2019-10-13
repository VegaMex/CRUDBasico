package com.vegamex.crudbasico;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private final String SCRIPT_DB = "CREATE TABLE IF NOT EXISTS contactos (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "_usuario TEXT NOT NULL," +
            "_email TEXT NOT NULL," +
            "_telefono TEXT NOT NULL," +
            "_fechaNacimiento INTEGER NOT NULL" +
            ");";

    public static final String[] COLUMNS_NAME_CONTACTO =
            {"_id", "_usuario", "_email", "_telefono", "_fechaNacimiento"};

    public  static final String TABLE_NAME_CONTACTOS =
            "contactos";

    public DataBase(@Nullable Context context) {
        super(context, "MyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_DB);
        Log.i(this.getClass().toString(), "ACABO DE EJECUTAR EL SCRIPT");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
