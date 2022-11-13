package com.example.practicaevaluable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.practicaevaluable.model.GymDex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private GymDex gymDex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gymDex = new GymDex("horario", this);

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        Button button=(Button)findViewById(R.id.button2);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> days = gymDex.getDays();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,AddActivity.class);
                intent.putExtra("dia",String.valueOf(spinner.getSelectedItem()));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(i).toString();

        List<String> lista = Arrays.asList("hola", "2");//this.gymDex.search(item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view, lista);
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}