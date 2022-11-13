package com.example.practicaevaluable.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminSQLite extends SQLiteOpenHelper{

    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table Ejercicios(nombre text primary key, parteCuerpo text, dia text)");
        database.execSQL("create table Dias(nombre text primary key)");

        List<String> dias = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo");

        ContentValues values = new ContentValues();

        for (String d: dias){
            values.put("nombre", d);
            database.insert("Dias", null, values);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
