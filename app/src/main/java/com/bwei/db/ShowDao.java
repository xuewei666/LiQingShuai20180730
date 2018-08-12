package com.bwei.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

import com.bwei.bean.NewsKu;

public class ShowDao {

    private final Showhepler showhepler;

    private final SQLiteDatabase db;

    public ShowDao(Context context) {
        showhepler = new Showhepler(context);
        db = showhepler.getWritableDatabase();
    }


    public void insert(NewsKu bean){
        ContentValues values = new ContentValues();
        values.put("url",bean.url);
        values.put("json",bean.json);
        db.replace("bwei",null,values);

    }
    public NewsKu query (String url){
        NewsKu bean = null;
        Cursor cursor = db.rawQuery("select * from bwei where url = ?", new String[]{url});
        if(cursor.moveToNext()){
            bean = new NewsKu();
            bean.url  = cursor.getString(cursor.getColumnIndex("url"));
            bean.json  = cursor.getString(cursor.getColumnIndex("json"));

        }
        return bean;
    }

}
