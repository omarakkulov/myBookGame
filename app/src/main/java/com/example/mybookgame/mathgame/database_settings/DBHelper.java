package com.example.mybookgame.mathgame.database_settings;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_RESULT = "RESULT_TABLE";
    public static final String COLUMN_RESULT = "RESULT";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";

    public DBHelper(@Nullable Context context) {
        super(context, "usersResult.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_RESULT +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_RESULT + " INTEGER)");
    }

    //пока не трогаем
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //доавляем пункт
    public boolean add(DB_Template template) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, template.getName());
        cv.put(COLUMN_RESULT, template.getResult());

        long id = db.insert(TABLE_RESULT, null, cv);
        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    //результат будем вставлять в лист и отобразим все это в listview с помощью адаптера
    public List<DB_Template> getResult() {
        List<DB_Template> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlExpr = "SELECT * FROM " + TABLE_RESULT;
        Cursor cursor = db.rawQuery(sqlExpr, null);
        if (cursor.moveToFirst()) {
            do {
                int column_id = cursor.getInt(0);
                String column_name = cursor.getString(1);
                int column_result = cursor.getInt(2);

                //это пойдет в лист
                DB_Template template = new DB_Template(column_id, column_name, column_result);
                returnList.add(template);
            } while (cursor.moveToNext());
        } else {
            db.close();
            cursor.close();
        }
        return returnList;
    }

    //удаление пункта по id
    public boolean delete(DB_Template template) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlExpression = "DELETE FROM " + TABLE_RESULT + " WHERE " + COLUMN_ID + " = " + template.getId();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sqlExpression, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
}


