package com.example.mybookgame.mathgame;
//пока в процессе

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "resultstore.db"; //название бд
    private static int SCHEMA = 1; // версия бд
    private final String TABLE = "results"; //название таблицы в бд

    //названия столбцов в бд
    private final String COLUMN_ID = "_id";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_RESULT = "result";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //создание таблицы
        db.execSQL("CREATE TABLE results (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT, " + COLUMN_RESULT + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public String getCOLUMN_ID() {
        return COLUMN_ID;
    }

    public String getCOLUMN_NAME() {
        return COLUMN_NAME;
    }

    public String getCOLUMN_RESULT() {
        return COLUMN_RESULT;
    }
}
