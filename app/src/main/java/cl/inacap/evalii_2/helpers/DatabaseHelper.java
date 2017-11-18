package cl.inacap.evalii_2.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "eval2_2.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE recordatorio(codRecordatorio INTEGER PRIMARY KEY, titulo TEXT NOT NULL, contenido TEXT NULL);");
        db.execSQL("INSERT INTO recordatorio(titulo, contenido) VALUES ('Comprar queso', 'No olvidar para la once');");
        db.execSQL("INSERT INTO recordatorio(titulo, contenido) VALUES ('Prueba de Aplicaciones Móviles', 'Para el 2 de noviembre');");
        db.execSQL("INSERT INTO recordatorio(titulo, contenido) VALUES ('Defensas de los compañeros', 'En diciembre podremos visitar las defensas de título de los chicos de cuarto año');");
        db.execSQL("INSERT INTO recordatorio(titulo, contenido) VALUES ('Evento de halloween', 'Sacar las skins exclusivas antes de que se acabe el evento');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
