package com.example.tomasyb.tomasybandroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库SQLite在Android平台的基本使用
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-8-2.9:19
 * @since JDK 1.8
 */

public class SQLiteDBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "ybandroid.db";//表名


    public SQLiteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int
            version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
