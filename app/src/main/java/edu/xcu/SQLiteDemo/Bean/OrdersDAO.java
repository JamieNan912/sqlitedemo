package edu.xcu.SQLiteDemo.Bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrdersDAO {
    private Context context;
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public OrdersDAO(Context context) {
        this.context = context;
    }

    //打开数据库
    public void open() throws SQLiteException {
        dbHelper = new MyDBHelper(context);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    //关闭数据库
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    //注册用户
    public long registerUsers(User u) {
        // 创建ContentValues对象
        ContentValues values = new ContentValues();
        // 向该对象中插入键值对
        values.put("username", u.username);
        values.put("userpwd", u.userpwd);
        // 调用insert()方法将数据插入到数据库当中
        return db.insert("tb_User", null, values);
    }
    //添加订单
    public long addOrders(Order o) {
        // 创建ContentValues对象
        ContentValues values = new ContentValues();
        // 向该对象中插入键值对
        values.put("orderid", o.orderid);
        values.put("username", o.username);
        values.put("name", o.name);
        values.put("price", o.price);
        values.put("amount", o.amount);
        // 调用insert()方法将数据插入到数据库当中
        return db.insert("tb_Orders", null, values);
    }

    //删除指定订单
    public int deletOrders(Order o) {
        return db.delete("tb_Orders", "orderid=?", new String[]{String.valueOf(o.orderid)});
    }

    //修改指定订单
    public int updateOrders(Order o) {
        ContentValues value = new ContentValues();
        value.put("username", o.username);
        value.put("name", o.name);
        value.put("price", o.price);
        value.put("amount", o.amount);
        return db.update("tb_Orders", value, "orderid=?", new String[]{String.valueOf(o.orderid)});
    }

    //根据订单号查找订单
    public Order getOrders(String orderid) {
        //查询联系人
        Cursor cursor = db.query("tb_Orders", null, "orderid=?", new String[]{orderid}, null, null, null);
        Order o = new Order();
        while (cursor.moveToNext()) {
            o.orderid = cursor.getString(cursor.getColumnIndex("orderid"));
            o.username = cursor.getString(cursor.getColumnIndex("username"));
            o.name = cursor.getString(cursor.getColumnIndex("name"));
            o.price = cursor.getString(cursor.getColumnIndex("price"));
            o.amount = cursor.getString(cursor.getColumnIndex("amount"));
        }
        return o;
    }

    //查找所有订单
    public ArrayList<Map<String, Object>> getAllOrders() {
        ArrayList<Map<String, Object>> listOrders = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query("tb_Orders", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("orderid", cursor.getString(cursor.getColumnIndex("orderid")));
                map.put("username", cursor.getString(cursor.getColumnIndex("username")));
                map.put("name", cursor.getString(cursor.getColumnIndex("name")));
                map.put("price", cursor.getString(cursor.getColumnIndex("price")));
                map.put("amount", cursor.getString(cursor.getColumnIndex("amount")));
                listOrders.add(map);
            }
            return listOrders;
        }
    }
}