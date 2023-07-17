package com.vincentwijaya202102315.tugasakhir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "pemain.db";
    public static final int DBVERSION =2;

    public DBHelper(Context context) {
        super(context, "pemain.db", null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT)");
        sqLiteDatabase.execSQL("create table datapemain(namapemain TEXT primary key, umurpemain TEXT, golonganpemain TEXT, alamat TEXT, kategori TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists datapemain");
        onCreate(sqLiteDatabase);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if (result == 0) return false;
        else
            return true;
    }

    //check username function
    public Boolean checkusername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //check username Password function
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean editData(String namapemain, String umurpemain, String golonganpemain, String alamat, String kategori) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("namapemain", namapemain);
        values.put("umurpemain", umurpemain);
        values.put("golonganpemain", golonganpemain);
        values.put("alamat", alamat);
        values.put("kategori", kategori);
        Cursor cursor = db.rawQuery("Select * from datapemain where namapemain=?", new String[]{namapemain});
        if (cursor.getCount() > 0) {
            long result = db.update("datapemain", values, "namapemain=?", new String[]{namapemain});
            if (result == 1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean checkkodepemain(String namapemain) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from datapemain where namapemain=?", new String[]{namapemain});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean insertDataPemain(String namapemain, String umurpemain, String golonganpemain, String alamat, String kategori) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("namapemain", namapemain);
        values.put("umurpemain", umurpemain);
        values.put("golonganpemain", golonganpemain);
        values.put("alamat", alamat);
        values.put("kategori", kategori);
        long result = db.insert("datapemain", null, values);
        if (result == 0) return false;
        else
            return true;
    }

    public boolean hapusData(String namapemain) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from datapemain where namapemain=?", new String[]{namapemain});
        if (cursor.getCount() > 0) {
            long result = db.delete("datapemain", "namapemain =?", new String[]{namapemain});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor tampilData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from datapemain", null);
        return cursor;
    }
}


