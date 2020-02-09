package com.vegamex.crudbasico;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CP extends ContentProvider {

    SQLiteDatabase sqliteDB;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("com.vegamex.crudbasico.provider", "contactos", 1);
        uriMatcher.addURI("com.vegamex.crudbasico.provider", "contactos/#", 2);
        uriMatcher.addURI("com.vegamex.crudbasico.provider", "contactos/*", 3);
    }

    @Override
    public boolean onCreate() {
        sqliteDB = new DataBase(this.getContext()).getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;

        switch (uriMatcher.match(uri)) {
            case 1:
                cursor = sqliteDB.query(DataBase.TABLE_NAME_CONTACTOS,
                        projection,
                        null,
                        null,
                        null,
                        null, null);
                break;
            case 2:
                cursor = sqliteDB.query(DataBase.TABLE_NAME_CONTACTOS,
                        projection,
                        DataBase.COLUMNS_NAME_CONTACTO[0] + " = ?",
                        new String[]{uri.getLastPathSegment()},
                        null, null, null
                );
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        String resul = "";
        switch (uriMatcher.match(uri)) {
            case 1:
                resul = "vnd.android.cursor.dir/vnd." +
                        "com.vegamex.crudbasico.provider.contactos";
                break;
            case 2:
                resul = "vnd.android.cursor.item/vnd. " +
                        "com.vegamex.crudbasico.provider.contactos";
                break;
        }
        return resul;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case 1:
                sqliteDB.insert(DataBase.TABLE_NAME_CONTACTOS,
                        null, values);
                break;
        }
        return Uri.parse(uri.toString());
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted = 0;
        switch (uriMatcher.match(uri)) {
            case 1:
                deleted = sqliteDB.delete(DataBase.TABLE_NAME_CONTACTOS, selection, selectionArgs);
                break;
        }
        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updated = 0;
        switch (uriMatcher.match(uri)) {
            case 1:
                updated = sqliteDB.update(DataBase.TABLE_NAME_CONTACTOS, values, selection, selectionArgs);
                break;
        }
        return updated;
    }
}
