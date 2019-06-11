package com.pushapp.DB;

import android.content.ContentValues;
import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance;

    private static final int database_ver = 1;
    private static final String database_name = "passwords.db";
    private static final String table_name = "password";
    private static final String column_name = "pass";
    private static final String PASS_PHARSE = "sdefe3re3f";

    private static final String SQL_CREATE_TABLE = new StringBuffer("CREATE TABLE ")
            .append(table_name)
            .append(" (")
            .append(column_name)
            .append(" TEXT PRIMARY KEY").toString();

    private static final String SQL_DELETE_TABLE = new StringBuffer("DROP TABLE IF EXISTS ")
            .append(table_name).toString();
    private SQLiteDatabase sqLiteDatabase;


    public DBHelper(Context context){
        super(context, database_name, null, database_ver);
    }

    static public synchronized DBHelper getInstance(Context context){
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    // CRUD
    public void insertNewPass(String pass){
        SQLiteDatabase db = instance.getWritableDatabase(PASS_PHARSE);

        ContentValues values = new ContentValues();
        values.put(column_name, pass);
        db.insert(table_name, null, values);
        db.close();
    }

    public void updatePass(String old_pass, String new_pass){
        net.sqlcipher.database.SQLiteDatabase db = instance.getWritableDatabase(PASS_PHARSE);

        ContentValues values = new ContentValues();
        values.put(column_name, new_pass);
        db.update(table_name, values, column_name + "='" + old_pass + "'", null);
        db.close();

    }

    public void deletePass(String pass){
        net.sqlcipher.database.SQLiteDatabase db = instance.getWritableDatabase(PASS_PHARSE);

        ContentValues values = new ContentValues();
        values.put(column_name, pass);
        db.delete(table_name,column_name + "='" + pass + "'", null);
        db.close();
    }



}

