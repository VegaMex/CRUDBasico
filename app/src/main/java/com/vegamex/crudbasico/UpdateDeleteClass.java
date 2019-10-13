package com.vegamex.crudbasico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateDeleteClass extends AppCompatActivity implements View.OnClickListener {

    private Button btnEliminarUD, btnEditarUD, btnFechaUD;
    private TextView txtFechaUD;
    private EditText txtUsuarioUD, txtEmailUD, txtTelefonoUD;
    private long fecha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_delete_main);

        btnFechaUD = (Button)findViewById(R.id.btnFechaUD);
        btnEditarUD = (Button)findViewById(R.id.btnEditarUD);
        btnEliminarUD = (Button)findViewById(R.id.btnEliminarUD);
        txtFechaUD = (TextView)findViewById(R.id.txtFechaUD);
        txtUsuarioUD = (EditText)findViewById(R.id.txtUsuarioUD);
        txtEmailUD = (EditText)findViewById(R.id.txtEmailUD);
        txtTelefonoUD = (EditText)findViewById(R.id.txtTelefonoUD);

        btnFechaUD.setOnClickListener(this);
        btnEliminarUD.setOnClickListener(this);
        btnEditarUD.setOnClickListener(this);

        Bundle b = this.getIntent().getExtras();
        if (b != null){
            Contacto contacto = (Contacto)b.getSerializable("contacto");
            Conversores conversor = new Conversores();
            txtUsuarioUD.setText(contacto.getUsuario());
            txtEmailUD.setText(contacto.getEmail());
            txtTelefonoUD.setText(contacto.getTelefono());
            txtFechaUD.setText(conversor.longAString(contacto.getFechaNacimiento()));
            Toast.makeText(this, contacto.getFechaNacimiento()+"", Toast.LENGTH_LONG).show();
        }else{
            finish();
        }


    }

    @Override
    public void onClick(View view) {
        if(view == btnFechaUD){

        }
        if(view == btnEliminarUD){

        }
        if(view == btnEditarUD){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK){
//            Contacto contacto = (Contacto)data.getSerializableExtra("contacto");
//            Toast.makeText(this, contacto.getUsuario()+"", Toast.LENGTH_SHORT).show();
//        }
    }
}
