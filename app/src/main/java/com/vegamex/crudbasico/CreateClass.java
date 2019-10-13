package com.vegamex.crudbasico;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CreateClass extends AppCompatActivity implements View.OnClickListener {

    private Button btnFechaCreate, btnCancelarCreate, btnAgregarCreate;
    private TextView txtFechaCreate;
    private EditText txtUsuarioCreate, txtEmailCreate, txtTelefonoCreate;
    private int dia, mes, año;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_main);

        btnFechaCreate = (Button)findViewById(R.id.btnFechaCreate);
        btnAgregarCreate = (Button)findViewById(R.id.btnAgregarCreate);
        btnCancelarCreate = (Button)findViewById(R.id.btnCancelarCreate);
        txtFechaCreate = (TextView)findViewById(R.id.txtFechaCreate);
        txtUsuarioCreate = (EditText)findViewById(R.id.txtUsuarioCreate);
        txtEmailCreate = (EditText)findViewById(R.id.txtEmailCreate);
        txtTelefonoCreate = (EditText)findViewById(R.id.txtTelefonoCreate);

        btnFechaCreate.setOnClickListener(this);
        btnAgregarCreate.setOnClickListener(this);
        btnCancelarCreate.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btnFechaCreate){
            final Calendar calendar = Calendar.getInstance();
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            mes = calendar.get(Calendar.MONTH);
            año = calendar.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txtFechaCreate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, año, mes, dia);
            datePickerDialog.show();
        }

        if(view == btnAgregarCreate){
            Intent intent = new Intent(CreateClass.this, MainActivity.class);
            DAOContacto daoContacto = new DAOContacto(this);
            Conversores conversor = new Conversores();
            long fecha = conversor.CalendarALong(año, mes, dia);

            try {

                daoContacto.insert(new Contacto(0, txtUsuarioCreate.getText().toString(),
                        txtEmailCreate.getText().toString(),
                        txtEmailCreate.getText().toString(),fecha));

                setResult(RESULT_OK, null);
                finish();

            }catch (Exception ex){

                setResult(RESULT_CANCELED, null);
                finish();

            }
        }
    }
}
