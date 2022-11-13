package com.example.practicaevaluable.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<String> search(String dia){
        List<String> mapa = new ArrayList<>();
        SQLiteDatabase readableDataBase = dataBase.getReadableDatabase();
        Cursor cursor = readableDataBase.rawQuery("select nombre, parteCuerpo from horario where dia = "+dia, null);

        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);

                mapa.add(name);
            }while (cursor.moveToNext());
        }
        cursor.close();
        readableDataBase.close();
        return mapa;
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
