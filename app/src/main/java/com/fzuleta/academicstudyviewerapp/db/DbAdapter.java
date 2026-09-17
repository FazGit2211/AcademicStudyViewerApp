package com.fzuleta.academicstudyviewerapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fzuleta.academicstudyviewerapp.models.Career;

public class DbAdapter {
    /*
     * 1- Definir las constantes con los campos de la entidad a mapear
     * 2- Definir constantes relacionados con el nombre de la bd, tabla.
     * 3- Definir la query para crear la tabla en la bd
     * */
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DURATION = "duration";
    private static final String TAG = "DbAdapter";

    private static final String DATABASE_NAME = "AcademicStudyViewerDb";
    private static final String DATABASE_TABLE = "careers";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table careers (_id integer primary key autoincrement," + "name text not null,duration integer not null);";
    private final Context context;
    private DatabaseHelper DBHelper;

    private SQLiteDatabase db;

    public DbAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database version from" + oldVersion + "to" + newVersion);
            db.execSQL("DROP TABLE IF EXISTS carrers");
            onCreate(db);
        }
    }

    /*
     * Methods open and close connection database
     * */
    public DbAdapter open() throws SQLException {
        this.db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    /*
     * Methods for crud operations
     * */
    public void insertCareer(Career career) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, career.getName());
        contentValues.put(KEY_DURATION, career.getDuration());
        db.insert(DATABASE_TABLE, null, contentValues);
    }

    public Cursor getAllCareers() throws SQLException {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_DURATION}, null, null, null, null, null);
    }
}
