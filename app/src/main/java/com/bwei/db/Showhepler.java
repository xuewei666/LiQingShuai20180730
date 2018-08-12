package com.bwei.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class Showhepler extends SQLiteOpenHelper {

    public Showhepler(Context context) {
        super(context,"bwei.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table bwei(id Integer primary key autoincrement ,url TEXT,json,TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
