package com.example.todo.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {

    private static  final  int DATABASE_VERSION = 1;
    static final  String DATABASE_NAME ="note";

    public Database (Context context)
    {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE note (id INTEGER PRIMARY KEY autoincrement,title TEXT NOT NULL, description TEXT NOT NULL)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS note");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAll()
    {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM note";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("title", cursor.getString(1));
                map.put("description", cursor.getString(2));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert(String title, String description)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO note (title,description) VALUES('"+title+"', '"+description+"')";
        database.execSQL(QUERY);
    }

    public void update (int id, String title, String description)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE note SET title = '"+title+"', description'"+description+"' WHERE id ="+id;
        database.execSQL(QUERY);
    }

    public void delete (int id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM note WHERE id ="+id;
        database.execSQL(QUERY);
    }
}
