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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ListView lista;
    Button btnBuscarMain;
    EditText txtBuscarMain;

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

        btnBuscarMain = (Button)findViewById(R.id.btnBuscarMain);
        txtBuscarMain = (EditText)findViewById(R.id.txtBuscarMain);

        btnBuscarMain.setOnClickListener(this);

        DAOContacto daoContacto = new DAOContacto(this);

        //Datos de prueba
        daoContacto.insert(new Contacto(0,"Juan","juan@mail.com",
                "4451234567","1999/12/16"));
        daoContacto.insert(new Contacto(0,"Aarón","aaron@mail.com",
                "4451234567","1998/02/22"));
        daoContacto.insert(new Contacto(0,"Óscar","oscar@mail.com",
                "4451234567","1998/07/17"));
        daoContacto.insert(new Contacto(0,"Bere","bere@mail.com",
                "4451234567","1998/05/16"));
        daoContacto.insert(new Contacto(0,"Monse","monse@mail.com",
                "4451234567","1998/02/15"));
        daoContacto.insert(new Contacto(0,"Pablo","pablo@mail.com",
                "4451234567","1998/08/17"));
        daoContacto.insert(new Contacto(0,"Iván","ivan@mail.com",
                "4451234567","1995/10/14"));

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
            Toast.makeText(this, "Operación realizada con éxito.", Toast.LENGTH_SHORT).show();
            refresh();
        }else{
            Toast.makeText(this, "No se hizo nada.", Toast.LENGTH_SHORT).show();
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
            cursor.close();

            Intent intent = new Intent();
            Bundle bundle = new Bundle();

            bundle.putSerializable("contacto", contacto);
            intent.putExtras(bundle);
            intent.setClass(this, UpdateDeleteClass.class);
            startActivityForResult(intent, 10);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnBuscarMain:
                DAOContacto daoContacto = new DAOContacto(this);
                Contacto contacto = daoContacto.contactoPorUsuario(txtBuscarMain.getText().toString());

                if(contacto != null){
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("contacto", contacto);
                    intent.putExtras(bundle);
                    intent.setClass(this, ReadClass.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "No existe el usuario.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
