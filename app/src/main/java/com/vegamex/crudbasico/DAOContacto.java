package com.vegamex.crudbasico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOContacto {

    SQLiteDatabase sqLiteDatabase;
    Context contexto;

    public DAOContacto(Context contexto) {
        this.contexto = contexto;
        sqLiteDatabase = new DataBase(contexto).getWritableDatabase();
    }

    public long insert(Contacto contacto) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBase.COLUMNS_NAME_CONTACTO[1], contacto.getUsuario());
        contentValues.put(DataBase.COLUMNS_NAME_CONTACTO[2], contacto.getEmail());
        contentValues.put(DataBase.COLUMNS_NAME_CONTACTO[3], contacto.getTelefono());
        contentValues.put(DataBase.COLUMNS_NAME_CONTACTO[4], contacto.getFechaNacimiento());

        return sqLiteDatabase.insert(DataBase.TABLE_NAME_CONTACTOS, null, contentValues);
    }

    public List<Contacto> getAll() {
        List<Contacto> lista = null;

        Cursor cursor = sqLiteDatabase.query(DataBase.TABLE_NAME_CONTACTOS,
                DataBase.COLUMNS_NAME_CONTACTO,
                null,
                null,
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            lista = new ArrayList<Contacto>();
            do {
                Contacto contacto =
                        new Contacto(cursor.getInt(0), cursor.getString(1),
                                cursor.getString(2), cursor.getString(3), cursor.getLong(4));
                lista.add(contacto);

            } while (cursor.moveToNext());
        }
        return lista;

    }

    public Cursor getAllCursor() {


        Cursor cursor = sqLiteDatabase.query(DataBase.TABLE_NAME_CONTACTOS,
                DataBase.COLUMNS_NAME_CONTACTO,
                null,
                null,
                null,
                null,
                null,
                null);


        return cursor;

    }

    public Cursor getAllByUsuario(String criterio) {

        Cursor cursor = sqLiteDatabase.query(
                DataBase.TABLE_NAME_CONTACTOS,
                DataBase.COLUMNS_NAME_CONTACTO,
                "usuario like %?%",
                new String[]{criterio},
                null,
                null, null

        );

        return cursor;
    }
}
