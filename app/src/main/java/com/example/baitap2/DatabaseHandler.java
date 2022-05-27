package com.example.baitap2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DeviceManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Devices";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_STATUS = "status";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = String.format("CREATE TABLE if not exists %s(%s INTEGER primary key, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_IMAGE, KEY_STATUS);
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(dropTable);

        onCreate(db);
    }

    public ArrayList<Device> getAll() {
        ArrayList<Device> list = new ArrayList<>();
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Device device = new Device(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4) == 1);
                list.add(device);
            }
            cursor.close();
        }

        return list;
    }

    public void insert(Device device) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_ID, device.getId());
        value.put(KEY_NAME, device.getName());
        value.put(KEY_DESCRIPTION, device.getDescription());
        value.put(KEY_IMAGE, device.getImage());
        value.put(KEY_STATUS, device.getStatus());

        db.insert(TABLE_NAME, null, value);
        db.close();
    }

    public void update(Device device) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_ID, device.getId());
        value.put(KEY_NAME, device.getName());
        value.put(KEY_DESCRIPTION, device.getDescription());
        value.put(KEY_IMAGE, device.getImage());
        value.put(KEY_STATUS, device.getStatus());

        db.update(TABLE_NAME, value, KEY_ID + "=?", new String[]{String.valueOf(device.getId())});
        db.close();
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from " + TABLE_NAME + " where id = " + id;
        db.execSQL(sql);
    }
}
