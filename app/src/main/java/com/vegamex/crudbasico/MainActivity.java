package com.vegamex.crudbasico;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.btnNuevoMain);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //DataBase dataBase = new DataBase(getBaseContext());
        //SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        Calendar c = Calendar.getInstance();
        long y = c.getTimeInMillis();

        DAOContacto daoContacto = new DAOContacto(this);
        //daoContacto.insert(new Contacto(0, "Oscar", "oscar@mail.com", "445", y));
        //daoContacto.insert(new Contacto(0, "Pablo", "pablo@mail.com", "445", y));

        for (Contacto contacto : daoContacto.getAll()){
            Toast.makeText(this,
                    contacto.usuario,
                    Toast.LENGTH_SHORT).show();
        }

        lista = findViewById(R.id.lista);

        SimpleCursorAdapter adp =
                new SimpleCursorAdapter(
                        this,
                        android.R.layout.simple_list_item_2,
                        daoContacto.getAllCursor(),
                        new String[]{"_usuario","_email"},
                        new int[]{android.R.id.text1, android.R.id.text2
                        },
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE

                );
        lista.setAdapter(adp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
