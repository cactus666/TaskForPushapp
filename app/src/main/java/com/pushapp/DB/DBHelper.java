package com.pushapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.pushapp.POJO.Password;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance;
    private static final int database_ver = 1;
    private static final String database_name = "passwords.db";
    private static final String table_name = "password";
    private static final String column_name_id = "id";
    private static final String column_name_name = "name";
    private static final String column_name_link = "link";
    private static final String column_name_login = "login";
    private static final String column_name_pass = "pass";
    private static final String PASS_PHARSE = "sdefe3re3f";
    private static int id = 0;

    private static final String SQL_CREATE_TABLE = new StringBuffer("CREATE TABLE ")
            .append(table_name)
            .append(" (")
            .append(column_name_id)
            .append(" integer primary key autoincrement, ")
            .append(column_name_name)
            .append(" text, ")
            .append(column_name_link)
            .append(" text, ")
            .append(column_name_login)
            .append(" text, ")
            .append(column_name_pass)
            .append(" text)").toString();

    private static final String SQL_DELETE_TABLE = new StringBuffer("DROP TABLE IF EXISTS ")
            .append(table_name).toString();


    public DBHelper(Context context){
        super(context, database_name, null, database_ver);
    }

    static public synchronized DBHelper getInstance(Context context){
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }
//
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
    public void insertNewPass(Password element){
        SQLiteDatabase db = instance.getWritableDatabase(PASS_PHARSE);

        ContentValues values = new ContentValues();
        values.put(column_name_name, element.getName());
        values.put(column_name_link, element.getLink());
        values.put(column_name_login, element.getLogin());
        values.put(column_name_pass, element.getPass());
        db.insert(table_name, null, values);
        db.close();
    }

    public void updatePass(Password element){
        net.sqlcipher.database.SQLiteDatabase db = instance.getWritableDatabase(PASS_PHARSE);

        ContentValues values = new ContentValues();
        values.put(column_name_name, element.getName());
        values.put(column_name_link, element.getLink());
        values.put(column_name_login, element.getLogin());
        values.put(column_name_pass, element.getPass());
        db.update(table_name, values, column_name_id + "=" + element.getId(), null);

//        Cursor cs = db.rawQuery("UPDATE password SET name = '"+element.getName()+"', link ='"+ element.getLink()+"', login ='"+element.getLogin()+"', pass ='"+element.getPass()+"' WHERE id = "+element.getId(), null);
//        Cursor cs = db.rawQuery(SQL, null);
//        cs.moveToFirst();

        db.close();
    }

    public void deletePass(int id){
        net.sqlcipher.database.SQLiteDatabase db = instance.getWritableDatabase(PASS_PHARSE);
        db.delete(table_name,column_name_id + "=" + id, null);
        db.close();
    }

    public List<Password> getAllPassword(){
        SQLiteDatabase db = instance.getWritableDatabase(PASS_PHARSE);
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM '%s';", table_name), null);

        List<Password> pass_list = new ArrayList<>();
        String name_el, link_el, login_el, pass_el;
        int id_el;

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                name_el = cursor.getString(cursor.getColumnIndex(column_name_name));
                link_el = cursor.getString(cursor.getColumnIndex(column_name_link));
                login_el = cursor.getString(cursor.getColumnIndex(column_name_login));
                pass_el = cursor.getString(cursor.getColumnIndex(column_name_pass));
                id_el = cursor.getInt(cursor.getColumnIndex(column_name_id));
                Log.i("info", name_el);

                if(name_el.isEmpty()) name_el = "пусто";
                if(link_el.isEmpty()) link_el = "пусто";
                if(login_el.isEmpty()) login_el = "пусто";
                if(pass_el.isEmpty()) pass_el = "пусто";

                pass_list.add(new Password(name_el, link_el, login_el, pass_el, id_el));
                cursor.moveToNext();
            }
        }


        cursor.close();
        db.close();

        return pass_list;
    }

    public static int getId(){
        return id++;
    }
}

