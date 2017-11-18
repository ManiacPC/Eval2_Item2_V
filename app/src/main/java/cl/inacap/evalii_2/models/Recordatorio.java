package cl.inacap.evalii_2.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

import cl.inacap.evalii_2.helpers.DatabaseHelper;

public class Recordatorio implements Serializable {

    // Atributos
    private int codRecordatorio;
    private String titulo;
    private String contenido;
    private DatabaseHelper helper;

    // Constructores
    public Recordatorio() { }

    public Recordatorio(Context context) {
        this.helper = new DatabaseHelper(context);
    }

    public Recordatorio(int codRecordatorio, String titulo, String contenido) {
        this.codRecordatorio = codRecordatorio;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public Recordatorio(Context context, int codRecordatorio, String titulo, String contenido) {
        this.helper = new DatabaseHelper(context);
        this.codRecordatorio = codRecordatorio;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    // Métodos
    public boolean insertar(Recordatorio recordatorio) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("TITULO", recordatorio.getTitulo());
        c.put("CONTENIDO", recordatorio.getContenido());
        try {
            db.insert("recordatorio",null,c);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }

    public ArrayList<Recordatorio> obtenerRecordatorios() {
        ArrayList<Recordatorio> recordatorios = new ArrayList<Recordatorio>();

        SQLiteDatabase db = this.helper.getReadableDatabase();
        try {
            Cursor consulta = db.rawQuery("SELECT * FROM recordatorio", null);
            if(consulta.moveToFirst()) {
                do {
                    int codRecordatorio = consulta.getInt(0);
                    String titulo = consulta.getString(1);
                    String contenido = consulta.getString(2);
                    recordatorios.add(new Recordatorio(codRecordatorio,titulo,contenido));
                } while (consulta.moveToNext());
                return recordatorios;
            } else return null;
        } catch (Exception e) {
            return null;
        } finally {
            db.close();
        }
    }

    public boolean actualizar() {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("TITULO", this.titulo);
        c.put("CONTENIDO", this.contenido);
        int filasActualizadas;

        try {
            filasActualizadas = db.update("recordatorio",c,"codRecordatorio = ?", new String[]{ String.valueOf(this.codRecordatorio) });
            return (filasActualizadas == 1 ? true : false);
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }

    public boolean actualizar(Recordatorio recordatorio) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("TITULO", recordatorio.getTitulo());
        c.put("CONTENIDO", recordatorio.getContenido());
        int filasActualizadas;

        try {
            filasActualizadas = db.update("recordatorio",c,"codRecordatorio = ?", new String[]{ String.valueOf(recordatorio.getCodRecordatorio()) });
            return (filasActualizadas == 1 ? true : false);
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }

    public boolean eliminar(int codRecordatorio) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        int filasAfectadas;
        try {
            filasAfectadas = db.delete("recordatorio","codRecordatorio = ?", new String[]{ String.valueOf(codRecordatorio)});
            return (filasAfectadas == 1 ? true : false);
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }

    public int getCodRecordatorio() {
        return codRecordatorio;
    }

    public void setCodRecordatorio(int codRecordatorio) {
        this.codRecordatorio = codRecordatorio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString(){
        return this.titulo + ":" + this.contenido;
    }
}
