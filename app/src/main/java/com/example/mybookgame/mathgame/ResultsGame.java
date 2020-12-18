package com.example.mybookgame.mathgame;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ResultsGame {
    private ContentValues cv;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    private String name;
    private int result, id;

    public ResultsGame() {
        cv = new ContentValues();

        db = dbHelper.getWritableDatabase();
    }

    public void add(String name, int result) {
        // подготовим данные для вставки в виде пар: наименование столбца - значение
        cv.put(dbHelper.getCOLUMN_NAME(), name);
        cv.put(dbHelper.getCOLUMN_RESULT(), result);
        // вставляем запись и получаем ее ID
        long rowId = db.insert("results", null, cv);
    }

    public void read() {
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor cursor = db.query("results", null, null,
                null, null, null, null);
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex("_id");
            int name = cursor.getColumnIndex("name");
            int result = cursor.getColumnIndex("result");
        } else {
            cursor.close();
        }
    }

    //очистка таблицы
    public void clear() {
        int clearCount = db.delete("results", null, null);
    }




    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
