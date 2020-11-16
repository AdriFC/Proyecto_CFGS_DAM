package com.example.weathercompare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
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

        createTable = "CREATE TABLE  comparacion (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "fecha DATE, " +
                "busqueda1_id INTEGER," +
                "busqueda2_id INTEGER" +
                ");";

        db.execSQL(createTable);

        createTable = "CREATE TABLE  busqueda (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tmin FLOAT," +
                "tmax FLOAT, " +
                "tmed FLOAT," +
                "probOfprec FLOAT, " +
                "vmed FLOAT," +
                "vracha FLOAT, " +
                "fecha DATE, " +
                "estadoCielo TEXT" +
                ");";

        db.execSQL(createTable);
    }

    //Método para actualizar, al extender de una clase abstracta es de obligada implementación por el usuario.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Función para insertar datos en la BBDD
    public boolean insertDataUsuario(String nombre, String email, String contraseña){
        SQLiteDatabase db = getWritableDatabase();

        //Comprobar que el email no ha sido registrado todavía en la base de datos
        String checkEmail = "SELECT * FROM usuario WHERE email = \"" + email + "\"";
        System.out.println(checkEmail);
        Cursor cursor = db.rawQuery (checkEmail, null);
        System.out.println("Número de emails: " + cursor.getCount());

        if (cursor.getCount() != 0) // There is a user register
        {
            return false;
        }

        //Insertar datos de usuario
        String insert = "INSERT INTO usuario (nombre, email, contraseña) " +
                "VALUES (\""+ nombre +"\" , \""+ email + "\" , \""+ contraseña + "\" );";
        db.execSQL(insert);
        db.close();

        return true;
    }

    public boolean insertDataBusqueda(int id, float tempMind, float tempMax, float tempMed, float vientoMed,
                                      float vientoRacha, float precipitaciones, String estadoCielo, Date fecha){
        SQLiteDatabase db = getWritableDatabase();
        //Insertar datos de la búsqueda
        String insert = "INSERT INTO busqueda (tmin, tmax, tmed, probOfprec, vmed, vracha, fecha, estadoCielo) " +
                "VALUES (\""+ tempMind +"\" , \""+ tempMax + "\" , \""+ tempMed + "\" , \""+precipitaciones + "\"" + vientoMed +
                        "\""+ vientoRacha + "\"" + fecha + "\"" + estadoCielo + "\");";
        db.execSQL(insert);

        return true;
    }

    public boolean insertDataComparacion(int user_id, Date fecha, Busqueda busqueda1_id, Busqueda busqueda2_id){
        SQLiteDatabase db = getWritableDatabase();
        //Insertar datos de la comparación
        String insert = "INSERT INTO comparacion (user_id, fecha, busqueda1_id, busqueda2_id) " +
                "VALUES (\""+ user_id +"\" , \""+ fecha + "\" , \""+ busqueda1_id + "\" , \""+busqueda2_id + "\");";
        db.execSQL(insert);

        return true;
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
                                    cursor.getString(3)));
        } while (cursor.moveToNext());
        Iterator iterator = usuario.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        lectura.close();
    }

    public Usuario checkLogin(String email, String contraseña){
        SQLiteDatabase lectura = getReadableDatabase();
        String query = "SELECT * FROM usuario WHERE email = "
                + "\"" + email + "\""
                + " AND contraseña = "
                + "\"" + contraseña + "\"" ;

        System.out.println(query);

        Cursor cursor = lectura.rawQuery (query, null);

        Usuario usuario;
        if (cursor.getCount() == 1) // There is a user register
        {
            cursor.moveToFirst();
            usuario = new Usuario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
        }
        else
        {
            usuario = new Usuario();
        }

        lectura.close();

        return usuario;
    }
}
