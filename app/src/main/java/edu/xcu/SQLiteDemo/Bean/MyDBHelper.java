package edu.xcu.SQLiteDemo.Bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    //常量定义
    public static final String DB_NAME = "db_order.db";
    public static final int DB_VERSION = 1;
    public static final String CREATE_USERDATA1 = "create table tb_User(username varchar(20) primary key,userpwd varchar(20))";
    public static final String CREATE_USERDATA2 = "create table tb_Orders(orderid char(10)primary key,username varchar(20),name varchar(20),price varchar(20), amount varchar(20))";
    //构造函数
    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA1);
        db.execSQL(CREATE_USERDATA2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
