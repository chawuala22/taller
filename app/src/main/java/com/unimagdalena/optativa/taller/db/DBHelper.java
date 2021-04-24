package com.unimagdalena.optativa.taller.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.unimagdalena.optativa.taller.model.InfoContador;

import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    private static final Integer DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_contadores";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE registros_contadores(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "barrio TEXT," +
                "direccion TEXT," +
                "valor INTEGER," +
                "idSpTipo INTEGER," +
                "nameSpTipo TEXT," +
                "fecha_creacion INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS registros_contadores");
        onCreate(db);

    }


    public long addInfoContador(InfoContador infoContador) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("fecha_creacion", java.lang.System.currentTimeMillis());
        cv.put("barrio", infoContador.getBarrio());
        cv.put("direccion", infoContador.getDireccion());
        cv.put("nameSpTipo", infoContador.getNameSpTipo());
        cv.put("idSpTipo", infoContador.getIdSpTipo());
        cv.put("valor", infoContador.getValor());



        return db.insert("registros_contadores", null, cv);
    }

    public int updateInfoContador(InfoContador infoContador) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("barrio", infoContador.getBarrio());
        cv.put("direccion", infoContador.getDireccion());
        cv.put("valor", infoContador.getValor());
        cv.put("idSpTipo", infoContador.getIdSpTipo());
        cv.put("nameSpTipo", infoContador.getNameSpTipo());
        return db.update("registros_contadores", cv, "id=?", new String[]{String.valueOf(infoContador.getId())});

    }

    public ArrayList<InfoContador> getInfoContadores() {
        ArrayList<InfoContador> infoContadores = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from registros_contadores", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String barrio = cursor.getString(1);
                String direccion = cursor.getString(2);
                int valor = cursor.getInt(3);
                long idSpTipo = (long) cursor.getInt(4);
                String nameStTipo = cursor.getString(5);


                InfoContador infoContador = new InfoContador(id, barrio, direccion, valor, nameStTipo, idSpTipo);
                infoContador.setFecha_creacion(cursor.getLong(6));
                infoContadores.add(infoContador);
            } while (cursor.moveToNext());
        }

        return infoContadores;
    }

    public int deleteInfoContador(int id) {

        SQLiteDatabase sqLiteDatabase =getWritableDatabase();

       return sqLiteDatabase.delete("registros_contadores","id=?", new String[]{String.valueOf(id)});

    }

    public Cursor searchBarrio(String text){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+ DATABASE_NAME+"WHERE barrio Like '%"+text+"%'";
        Cursor  cursor = db.rawQuery(query,null);
        return cursor;
    }
}
