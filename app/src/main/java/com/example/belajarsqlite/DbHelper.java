package com.example.belajarsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "mhsdb";
    public static final String COLUUMN_ID = "id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_NO_HP = "noHp";
    public static final String TABLE_NAME = "mhstb";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String stat = "CREATE TABLE " + TABLE_NAME + "(" + COLUUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAMA + " TEXT, " + COLUMN_NIM + " TEXT, " + COLUMN_NO_HP + " TEXT)";
        sqLiteDatabase.execSQL(stat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean simpan (@NonNull mhsModel mm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues() ;
        cv.put(COLUMN_NAMA , mm.getNama());
        cv.put(COLUMN_NIM, mm.getNim());
        cv.put(COLUMN_NO_HP,mm.getNohp());

        long stts = db.insert(TABLE_NAME, null, cv);
        if (stts == -1)
            return false ;
        else
            return false;


    }
    public ArrayList<mhsModel> List(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<mhsModel> mhslist = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String nama = cursor.getString(1);
                String nim = cursor.getString(2);
                String noHp= cursor.getString(3);

                mhsModel mm = new mhsModel(id, nama, nim, noHp);
                mhslist.add(mm);
            }
        }

        return mhslist;

    }


    public boolean hapus (int id){

        SQLiteDatabase db = this.getWritableDatabase();
        int rowAffected = db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});

        if(rowAffected > 0)
            return true;
        else
            return false;
    }
    public boolean ubah (mhsModel mm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues() ;
        cv.put(COLUMN_NAMA , mm.getNama());
        cv.put(COLUMN_NIM, mm.getNim());
        cv.put(COLUMN_NO_HP,mm.getNohp());
        int rowAffected = db.update(TABLE_NAME, cv, "id = ?", new String[]{String.valueOf(mm.getId())});

        if(rowAffected > 0)
            return true;
        else
            return false;

    }
}
