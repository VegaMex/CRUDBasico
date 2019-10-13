package com.vegamex.crudbasico;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lista;
    //DAOContacto daoContacto = new DAOContacto(this);

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
                Intent intent = new Intent(MainActivity.this, CreateClass.class);
                startActivityForResult(intent, 1);
            }
        });


        DAOContacto daoContacto = new DAOContacto(this);


//        for (Contacto contacto : daoContacto.getAll()){
//            Toast.makeText(this,
//                    contacto.usuario,
//                    Toast.LENGTH_SHORT).show();
//        }

        lista = findViewById(R.id.lista);

        lista.setClickable(true);
        lista.setOnItemClickListener(this);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == MainActivity.RESULT_OK){
            Toast.makeText(this, "Se insertó un registro.", Toast.LENGTH_SHORT).show();
            refresh();
        }else{
            Toast.makeText(this, "Error al insertar el registro.", Toast.LENGTH_SHORT).show();
        }
    }

    public void refresh(){
        DAOContacto daoContacto = new DAOContacto(this);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView == lista){
            DAOContacto daoContacto = new DAOContacto(this);
            Cursor cursor = (Cursor)lista.getItemAtPosition(i);
            String id = cursor.getString( cursor.getColumnIndex("_id") );

            Contacto contacto = daoContacto.contactoPorID(Integer.parseInt(id));

            Intent intent = new Intent();
            Bundle bundle = new Bundle();

            bundle.putSerializable("contacto", contacto);
            intent.putExtras(bundle);
            intent.setClass(this, UpdateDeleteClass.class);
            startActivity(intent);
        }
    }
}
