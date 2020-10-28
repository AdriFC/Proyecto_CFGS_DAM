package com.example.weathercompare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Clase para la conexión con la bd
public class BaseDeDatos extends SQLiteOpenHelper {

    //Constructor obligatorio que coincide con el de su clase superior
    public BaseDeDatos(@Nullable Context context,
                       @Nullable String name,
                       @Nullable SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
    }

    //Método para crear la bbdd, al extender de una clase abstracta es de obligada implementación por el usuario.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE  usuario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "email TEXT, " +
                "contraseña TEXT);";

        db.execSQL(createTable);

    }

    //Método para actualizar, al extender de una clase abstracta es de obligada implementación por el usuario.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Función para insertar datos en la BBDD
    public void insertData (String nombre, String email, String contraseña){
        SQLiteDatabase escritura = getWritableDatabase();
        String insert = "INSERT INTO usuario (nombre, email, contraseña) " +
                "VALUES (\""+ nombre +"\" , \""+ email + "\" , \""+ contraseña + "\" );";
        escritura.execSQL(insert);
        escritura.close();
    }

    /*
    Función para recuperar todos los datos de la BBDD, guardarlos en un arraylist e imprimirlos (Visto en clase, no aplica a este proyecto)
    La uso para comprobar el funcionamiento de la bbdd
     */

    public void getData (){
        SQLiteDatabase lectura = getReadableDatabase();
        String query = "SELECT * FROM usuario";
        Cursor cursor = lectura.rawQuery (query, null);
        cursor.moveToFirst();
        List<Usuario> usuario = new ArrayList<>();

        do{
            //System.out.println("Nombre de usuario: " + cursor.getString(1));
            usuario.add(new Usuario (
                                    cursor.getInt(0),
                                    cursor.getString(1),
                                    cursor.getString(2),
                                    cursor.getString(3),
                                    ""));
        } while (cursor.moveToNext());
        Iterator iterator = usuario.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        lectura.close();
    }
}
