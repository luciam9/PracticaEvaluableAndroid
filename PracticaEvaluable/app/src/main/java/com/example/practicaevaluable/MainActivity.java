package com.example.practicaevaluable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.practicaevaluable.model.Agenda;

public class MainActivity extends AppCompatActivity {

    private Agenda agenda;
    private EditText nombre, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.editTextPersonName);
        telefono = (EditText) findViewById(R.id.editTextNumber);
    }

    public void buscarContacto(View view){

    }
}