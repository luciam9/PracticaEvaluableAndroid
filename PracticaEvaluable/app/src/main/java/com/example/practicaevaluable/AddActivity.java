package com.example.practicaevaluable;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicaevaluable.model.AdminSQLite;

public class AddActivity extends AppCompatActivity {
    private EditText et_ejercicio, et_parteCuerpo;
    private Button btn_guardar;
    private TextView tv_Dia;
    private String dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tv_Dia = (TextView) findViewById(R.id.textoDia);
        et_ejercicio = (EditText) findViewById(R.id.textoEjercicio);
        et_parteCuerpo = (EditText) findViewById(R.id.textoParteCuerpo);
        btn_guardar = (Button) findViewById(R.id.botonGuardar);

        //Get the day selected to add an exercise
        Bundle b = getIntent().getExtras();
        if(b != null)
            dia = b.getString("dia");

        tv_Dia.setText(dia);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AddActivity.this,MainActivity.class);
                saveExercises(v);
                //intent.putExtra("dia",dia);
                startActivity(intent);
            }
        });
    }

    public void saveExercises(View view){
        AdminSQLite admin = new AdminSQLite(this, "horario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String ejercicio = et_ejercicio.getText().toString();
        String parteCuerpo = et_parteCuerpo.getText().toString();

        //Add the exercise to the day selected
        if(!ejercicio.isEmpty() && !parteCuerpo.isEmpty()){
            ContentValues entry = new ContentValues();
            entry.put("dia", dia);
            entry.put("nombre", ejercicio);
            entry.put("parteCuerpo", parteCuerpo);

            db.insert("Ejercicios", null, entry);
            db.close();

            et_ejercicio.setText("");
            et_parteCuerpo.setText("");

            Toast.makeText(this, "Success adding the exercise to the selected day.", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "You must fill in all the fields.", Toast.LENGTH_SHORT).show();
        }

    }

}
