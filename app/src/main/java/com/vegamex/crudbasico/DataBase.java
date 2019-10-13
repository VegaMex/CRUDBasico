package com.vegamex.crudbasico;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private final String SCRIPT_DB = "CREATE TABLE IF NOT EXISTS contactos (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "_usuario TEXT NOT NULL," +
            "_email TEXT NOT NULL," +
            "_telefono TEXT NOT NULL," +
            "_fechaNacimiento DATE NOT NULL" +
            ");";

    public static final String[] COLUMNS_NAME_CONTACTO =
            {"_id", "_usuario", "_email", "_telefono", "_fechaNacimiento"};

    public static final String TABLE_NAME_CONTACTOS = "contactos";

    public DataBase(@Nullable Context context) {
        super(context, "MyDB", null, 1);
    }

    public ContentValues valores(Contacto contacto){
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMNS_NAME_CONTACTO[1], contacto.getUsuario());
        contentValues.put(COLUMNS_NAME_CONTACTO[2], contacto.getEmail());
        contentValues.put(COLUMNS_NAME_CONTACTO[3], contacto.getTelefono());
        contentValues.put(COLUMNS_NAME_CONTACTO[4], contacto.getFechaNacimiento());

        return contentValues;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_DB);
        sqLiteDatabase.insert(TABLE_NAME_CONTACTOS, null,
                valores(new Contacto(0,"Juan","juan@mail.com",
                "4451234567","1999/12/16")));
        sqLiteDatabase.insert(TABLE_NAME_CONTACTOS, null,
                valores(new Contacto(0,"Aarón","aaron@mail.com",
                        "4451234567","1998/02/22")));
        sqLiteDatabase.insert(TABLE_NAME_CONTACTOS, null,
                valores(new Contacto(0,"Óscar","oscar@mail.com",
                        "4451234567","1998/07/17")));
        sqLiteDatabase.insert(TABLE_NAME_CONTACTOS, null,
                valores(new Contacto(0,"Bere","bere@mail.com",
                        "4451234567","1998/05/16")));
        sqLiteDatabase.insert(TABLE_NAME_CONTACTOS, null,
                valores(new Contacto(0,"Monse","monse@mail.com",
                        "4451234567","1998/02/15")));
        sqLiteDatabase.insert(TABLE_NAME_CONTACTOS, null,
                valores(new Contacto(0,"Pablo","pablo@mail.com",
                        "4451234567","1998/08/17")));
        sqLiteDatabase.insert(TABLE_NAME_CONTACTOS, null,
                valores(new Contacto(0,"Iván","ivan@mail.com",
                        "4451234567","1995/10/14")));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CONTACTOS);
        onCreate(sqLiteDatabase);
    }
}
