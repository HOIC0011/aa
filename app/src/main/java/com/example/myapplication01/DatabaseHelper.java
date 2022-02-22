package com.example.myapplication01;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "IT_AA.db";  //데이터베이스 명
    public static final String TABLE_NAME = "manage_AA_table";  //테이블 명
    //테이블 항목
    public static final String COl_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DEPT";
    public static final String COL_4 = "TYPE";
    public static final String COL_5 = "COMMENT";
    public static final String COL_6 = "IP";
    public static final String COL_7 = "DATE";
    public static final String COL_8 = "KOREA";
    public static final String COL_9 = "ACROBAT";
    public static final String COL_10 = "CAD";
    public static final String COL_11 = "PHOTOSHOP";
    public static final String COL_12= "SOLIDWORKS";
    public static final String COL_13 = "COMMENT_2";

    public DatabaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DEPT TEXT, TYPE TEXT, COMMENT TEXT," +
                "IP TEXT, DATE TEXT, KOREA TEXT, ACROBAT TEXT, CAD TEXT, PHOTOSHOP TEXT, SOLIDWORKS TEXT, COMMENT_2 TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    //데이터베이스 추가하기 Insert

    public boolean InsertData(String NAME, String DEPT, String Type, String COMMENT, String IP, String DATE, String KOREA, String ACROBAT, String CAD, String PHOTOSHOP, String SOLIDWORKS, String COMMENT_2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,NAME);
        contentValues.put(COL_3,DEPT);
        contentValues.put(COL_4,Type);
        contentValues.put(COL_5,COMMENT);
        contentValues.put(COL_6,IP);
        contentValues.put(COL_7,DATE);
        contentValues.put(COL_8,KOREA);
        contentValues.put(COL_9,ACROBAT);
        contentValues.put(COL_10,CAD);
        contentValues.put(COL_11,PHOTOSHOP);
        contentValues.put(COL_12,SOLIDWORKS);
        contentValues.put(COL_13,COMMENT_2);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    //데이터베이스 읽어오기

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME, null);
        return res;
    }


    //데이터베이스 삭제하기
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?", new String[]{id});
    }


    //데이터베이스 수정하기
    public boolean updateData(String ID, String NAME, String DEPT, String Type, String COMMENT, String IP, String DATE, String KOREA, String ACROBAT, String CAD, String PHOTOSHOP, String SOLIDWORKS, String COMMENT_2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COl_1,ID);
        contentValues.put(COL_2,NAME);
        contentValues.put(COL_3,DEPT);
        contentValues.put(COL_4,Type);
        contentValues.put(COL_5,COMMENT);
        contentValues.put(COL_6,IP);
        contentValues.put(COL_7,DATE);
        contentValues.put(COL_8,KOREA);
        contentValues.put(COL_9,ACROBAT);
        contentValues.put(COL_10,CAD);
        contentValues.put(COL_11,PHOTOSHOP);
        contentValues.put(COL_12,SOLIDWORKS);
        contentValues.put(COL_13,COMMENT_2);
        db.update(TABLE_NAME, contentValues,"id = ?", new String[]{ ID });
        return  true;
    }
}
