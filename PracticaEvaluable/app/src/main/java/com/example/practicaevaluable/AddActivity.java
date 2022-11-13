package com.example.practicaevaluable;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicaevaluable.model.AdminSQLite;

public class AddActivity extends AppCompatActivity {
    private EditText et_ejercicio, et_parteCuerpo;
    //private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_ejercicio = (EditText) findViewById(R.id.textoEjercicio);
        et_parteCuerpo = (EditText) findViewById(R.id.textoParteCuerpo);
    }

    public void saveExercises(View view){
        AdminSQLite admin = new AdminSQLite(this, "horario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //TODO: Change the day, take from the MainActivity
        Bundle b = getIntent().getExtras();
        String dia = null;
        if(b != null)
            dia = b.getString("dia");

        String ejercicio = et_ejercicio.getText().toString();
        String parteCuerpo = et_parteCuerpo.getText().toString();

        if(!ejercicio.isEmpty() && !parteCuerpo.isEmpty()){
            ContentValues entry = new ContentValues();
            entry.put("dia", dia);
            entry.put("nombre", ejercicio);
            entry.put("parteCuerpo", parteCuerpo);

            db.insert("Ejercicios", null, entry);
            db.close();

            //TODO: Devolver a MainActivity, mostrando ejercicios en el día seleccionado


            et_ejercicio.setText("");
            et_parteCuerpo.setText("");

            Toast.makeText(this, "Success adding the exercise to the selected day.", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "You must fill in all the fields.", Toast.LENGTH_SHORT).show();
        }

    }

}
