package com.example.practicaevaluable.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Agenda {

    private AdminSQLite dataBase;

    public Agenda(String dataBaseName, Context context){
        this.dataBase = new AdminSQLite(context, dataBaseName, null, 1);
    }



    public AdminSQLite getDataBase() {
        return dataBase;
    }

    public void insert(String table, ContentValues data){
        SQLiteDatabase writableDataBase = dataBase.getWritableDatabase();
        writableDataBase.insert(table, null, data);
    }

    public void search(String nombre){
        SQLiteDatabase readableDataBase = dataBase.getReadableDatabase();
        readableDataBase.rawQuery("select nombre, numero from contactos where nombre = "+nombre, null);
    }
}
