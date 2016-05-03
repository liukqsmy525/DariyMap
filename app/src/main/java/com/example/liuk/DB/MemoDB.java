package com.example.liuk.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LIUK on 2016/5/2.
 */
public class MemoDB extends SQLiteOpenHelper {

    public  static final String TABLE_MEMO = "memo";
    public  static final String TABLE_MEDIA = "media";

    public  static final String COLUMN_ID = "id";
    public  static final String COLUMN_MEMO_CONTENT = "content";
    public  static final String COLUMN_USER_ID = "user_id";
    public  static final String COLUMN_PHONE_ID = "phone_id";
    public  static final String COLUMN_MEMO_TITLE = "title";
    public  static final String COLUMN_MEMO_GEO = "geo";
    public  static final String COLUMN_MEMO_ADRESS = "adress";
    public  static final String COLUMN_MEMO_GEO_IDENTIFIER = "geo_identifier";
    public  static final String COLUMN_MEMO_CREATE_DATE = "create_date";
    public  static final String COLUMN_MEMO_CREATE_TIME = "create_time";
    public  static final String COLUMN_MEMO_REMIND_DATE = "remind_date";
    public  static final String COLUMN_MEMO_REMIND_TIME = "remind_time";
    public  static final String COLUMN_MEMO_MODIFY_DATE = "modify_date";
    public  static final String COLUMN_MEMO_MODIFY_TIME = "modifu_time";
    public  static final String COLUMN_MEMO_IS_REMEND = "is_remind";
    public  static final String COLUMN_MEMO_IS_REPEAT = "is_repeat";
    public  static final String COLUMN_MEMO_REPEAT_CODE = "repeat_code";
    public  static final String COLUMN_MEMO_STATUS = "status";

    public  static final String COLUMN_MEDIA_PATH = "path";
    public  static final String COLUMN_MEDIA_OWNER_ID = "ower_id";


    public MemoDB(Context context) {
        super(context, TABLE_MEMO, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table" + TABLE_MEMO + "(" +
                COLUMN_ID + " integer primary key autoincreament, " +
                COLUMN_MEMO_CONTENT + " text not null default \"\"," +
                COLUMN_MEMO_STATUS + " integer not null default 0," +
                COLUMN_MEMO_TITLE + " text not null default \"\"," +
                COLUMN_USER_ID + "integer not null," +
                COLUMN_PHONE_ID + "integer not null," +
                COLUMN_MEMO_GEO + " text not null default \"\"," +
                COLUMN_MEMO_GEO_IDENTIFIER + " text not null default \"\"," +
                COLUMN_MEMO_ADRESS + " text not null default \"\"," +
                COLUMN_MEMO_CREATE_DATE + " text not null default \"\"," +
                COLUMN_MEMO_CREATE_TIME + " text not null default \"\"," +
                COLUMN_MEMO_REMIND_DATE + " text not null default \"\"," +
                COLUMN_MEMO_REMIND_TIME + " text not null default \"\"," +
                COLUMN_MEMO_MODIFY_DATE + " text not null default \"\"," +
                COLUMN_MEMO_MODIFY_TIME + " text not null default \"\"," +
                COLUMN_MEMO_IS_REMEND + "integer not null default 0," +
                COLUMN_MEMO_IS_REPEAT + "integer not null default 0," +
                COLUMN_MEMO_REPEAT_CODE + "integer not null default 0," +")"
        );

        sqLiteDatabase.execSQL("create table" + TABLE_MEDIA + "(" +
                COLUMN_ID + " integer primary key autoincreament, " +
                COLUMN_MEDIA_PATH + " text not null default \"\"," +
                COLUMN_MEDIA_OWNER_ID + " integer not null default 0," +")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
