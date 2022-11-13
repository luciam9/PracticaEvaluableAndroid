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
    private EditText et_exercise, et_bodyPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_exercise = (EditText) findViewById(R.id.txtExercise);
        et_bodyPart = (EditText) findViewById(R.id.txtBodyPart);
    }

    public void saveExercises(View view){
        AdminSQLite admin = new AdminSQLite(this, "GymDex", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //TODO: Change the day, take from the MainActivity
        Bundle b = getIntent().getExtras();
        String dia = null;
        if(b != null)
            dia = b.getString("dia");

        String ejercicio = et_exercise.getText().toString();
        String parteCuerpo = et_bodyPart.getText().toString();

        if(!ejercicio.isEmpty() && !parteCuerpo.isEmpty()){
            ContentValues entry = new ContentValues();
            entry.put("Día", dia);
            entry.put("Ejercicio", ejercicio);
            entry.put("ParteCuerpo", parteCuerpo);

            db.insert("Timetable", null, entry);
            db.close();

            //TODO: Devolver a MainActivity, mostrando ejercicios en el día seleccionado


            et_exercise.setText("");
            et_bodyPart.setText("");

            Toast.makeText(this, "Success adding the exercise to the selected day.", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "You must fill in all the fields.", Toast.LENGTH_SHORT).show();
        }

    }

}
