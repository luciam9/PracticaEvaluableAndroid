package com.example.practicaevaluable.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class GymDex {

    private AdminSQLite dataBase;

    public GymDex(String dataBaseName, Context context){
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

    public List<String> getDays(){
        SQLiteDatabase db = this.dataBase.getWritableDatabase();
        Cursor cursor = db.rawQuery("select nombre from Dias", null);

        List<String> list = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);

                list.add(name);
            }while (cursor.moveToNext());
            }
        cursor.close();
        db.close();
        return list;
    }
}
