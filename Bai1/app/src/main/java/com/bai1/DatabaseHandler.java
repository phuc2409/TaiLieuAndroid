package com.bai1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HoTenSV_Sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Contact_TenSV";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUMBER = "phoneNumber";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = String.format("CREATE TABLE if not exists %s(%s INTEGER primary key, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_PHONE_NUMBER);
        db.execSQL(createTable);

        insertDefault(db, new Contact_TenSV(1, "Nam", "0987983793"));
        insertDefault(db, new Contact_TenSV(2, "Hữu Thắng", "0986774235"));
        insertDefault(db, new Contact_TenSV(3, "Toan", "0949739503"));
        insertDefault(db, new Contact_TenSV(7, "Name", "0123456789"));
        insertDefault(db, new Contact_TenSV(4, "Thành Mới", "0987888773"));
        insertDefault(db, new Contact_TenSV(5, "Minh Hiếu", "0964575786"));
        insertDefault(db, new Contact_TenSV(6, "Hoàng Anh", "0987886445"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(dropTable);

        onCreate(db);
    }

    public ArrayList<Contact_TenSV> getAll() {
        ArrayList<Contact_TenSV> list = new ArrayList<>();
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Contact_TenSV contact = new Contact_TenSV(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                list.add(contact);
            }
            cursor.close();
        }

        return list;
    }

    public void insert(Contact_TenSV contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_ID, contact.getId());
        value.put(KEY_NAME, contact.getName());
        value.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());

        db.insert(TABLE_NAME, null, value);
        db.close();
    }

    private void insertDefault(SQLiteDatabase db, Contact_TenSV contact) {
        ContentValues value = new ContentValues();
        value.put(KEY_ID, contact.getId());
        value.put(KEY_NAME, contact.getName());
        value.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());

        db.insert(TABLE_NAME, null, value);
    }

    public void update(Contact_TenSV contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_ID, contact.getId());
        value.put(KEY_NAME, contact.getName());
        value.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());

        db.update(TABLE_NAME, value, KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from " + TABLE_NAME + " where id = " + id;
        db.execSQL(sql);
    }
}
