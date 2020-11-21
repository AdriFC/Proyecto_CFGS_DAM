package com.example.weathercompare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

        createTable = "CREATE TABLE comparacion (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "fecha DATE, " +
                "busqueda1_id INTEGER," +
                "busqueda2_id INTEGER" +
                ");";

        db.execSQL(createTable);

        createTable = "CREATE TABLE busqueda (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tmin FLOAT," +
                "tmax FLOAT, " +
                "tmed FLOAT," +
                "probOfprec FLOAT, " +
                "vmed FLOAT," +
                "vracha FLOAT, " +
                "fecha DATE, " +
                "estadoCielo TEXT, " +
                "municipio TEXT, " +
                "provincia TEXT" +
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

    public int insertDataBusqueda(float tempMin, float tempMax, float tempMed, float vientoMed,
                                      float vientoRacha, float precipitaciones, String estadoCielo,
                                      Date fecha, String municipio, String provincia){
        SQLiteDatabase db = getWritableDatabase();
        //Insertar datos de la búsqueda
        String insert = "INSERT INTO busqueda (tmin, tmax, tmed, probOfprec, vmed, vracha, fecha, estadoCielo, municipio, provincia) " +
                "VALUES (\""+ tempMin +"\" , \""+ tempMax + "\" , \""+ tempMed + "\" , \""+precipitaciones + "\", \"" +
                        vientoMed + "\", \"" + vientoRacha + "\" , \"" + fecha + "\" , \"" + estadoCielo +
                        "\", \"" + municipio + "\", \"" + provincia +"\")";
        db.execSQL(insert);

        // Obtenemos el ID que se le acaba de asignar a la busqueda
        Cursor cursor = db.rawQuery ("SELECT last_insert_rowid()", null);
        cursor.moveToFirst();
        //System.out.println("insertando busqueda: ID = " + cursor.getInt(0));

        int id = cursor.getInt(0);

        //cerramos la conexion
        //db.close();

        //devolvemos el id
        return id;
    }

    public boolean insertDataComparacion(int user_id, Date fecha, Busqueda busqueda1, Busqueda busqueda2){
        SQLiteDatabase db = getWritableDatabase();

        //Insertar datos de cada una de las busquedas en la tabla busqueda
        int id1 = insertDataBusqueda(
                busqueda1.getTempMin(),
                busqueda1.getTempMax(),
                busqueda1.getTempMedia(),
                busqueda1.getVientoMedia(),
                busqueda1.getVientoRacha(),
                busqueda1.getPrecipitaciones(),
                busqueda1.getEstadoCielo(),
                busqueda1.getFecha(),
                busqueda1.getUbicacion(),
                busqueda1.getProvincia()
        );

        int id2 = insertDataBusqueda(
                busqueda2.getTempMin(),
                busqueda2.getTempMax(),
                busqueda2.getTempMedia(),
                busqueda2.getVientoMedia(),
                busqueda2.getVientoRacha(),
                busqueda2.getPrecipitaciones(),
                busqueda2.getEstadoCielo(),
                busqueda2.getFecha(),
                busqueda2.getUbicacion(),
                busqueda2.getProvincia()
        );

        //Insertar datos de la comparación en la tabla comparacion
        String insert = "INSERT INTO comparacion (user_id, fecha, busqueda1_id, busqueda2_id) " +
                "VALUES (\""+ user_id +"\" , \""+ fecha + "\" , \""+ id1 + "\" , \""+ id2 + "\");";
        db.execSQL(insert);

        db.close();
        return true;
    }

    /*
    Función para recuperar todos los datos de la BBDD, guardarlos en un arraylist e imprimirlos (Visto en clase, no aplica a este proyecto)
    La uso para comprobar el funcionamiento de la bbdd
     */

    public void getDataUsuario (){
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

    public Busqueda getDataBusqueda(int id) {
        SQLiteDatabase lectura = getReadableDatabase();
        String query = "SELECT * FROM busqueda WHERE id = " + id;
        Cursor cursor = lectura.rawQuery (query, null);
        cursor.moveToFirst();

        if (cursor.getCount() != 1) // No existe dicha busqueda
        {
            lectura.close();
            return new Busqueda();
        }

        Busqueda busqueda = new Busqueda(
                cursor.getInt(0),               // id
                cursor.getString(9),           // Ubicacion
                cursor.getString(10),           // Provincia
                new Date(cursor.getLong(7)),    // fecha
                cursor.getFloat(1),             // tmin
                cursor.getFloat(2),             // tmax
                cursor.getFloat(3),             // tmed
                cursor.getFloat(5),             // vmed
                cursor.getFloat(6),             // vracha
                cursor.getFloat(4),             //precipitaciones
                cursor.getString(8)             // EstadoCielo
        );

        lectura.close();

        return busqueda;
    }

    public List<Comparacion> getDataComparacion(int user_id) {
        SQLiteDatabase lectura = getReadableDatabase();
        String query = "SELECT * FROM comparacion WHERE user_id = " + user_id + " ORDER BY fecha DESC LIMIT 6";
        Cursor cursor = lectura.rawQuery (query, null);
        cursor.moveToFirst();
        List<Comparacion> comparacionList = new ArrayList<>();
        do{
            System.out.println("Comparacion ID " +
                    cursor.getInt(0) +
                    " entre busqueda:" +
                    cursor.getInt(3) +
                    " y" +
                    cursor.getInt(4) +
                    "\n" );

            //Buscamos la info de cada una de las busquedas, dado su id
            Busqueda busqueda1 = getDataBusqueda(cursor.getInt(3));
            Busqueda busqueda2 = getDataBusqueda(cursor.getInt(4));

            // Comprobamos si ha habido algun problema con las busquedas
            if(busqueda1.getId() != 0 && busqueda2.getId() != 0) {
                System.out.println("Datos busqueda 1: \n " + busqueda1.toString() + "\nDatos busqueda 2: \n " + busqueda2.toString());

                comparacionList.add(new Comparacion(new Date(cursor.getLong(3)), busqueda1, busqueda2));
            } else {
                System.out.println("Ha habido algun problema con alguna de las busquedas \n");
            }

        } while (cursor.moveToNext()) ;

        lectura.close();

        return comparacionList;
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
