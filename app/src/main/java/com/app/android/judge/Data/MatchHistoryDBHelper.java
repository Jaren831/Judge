package com.app.android.judge.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jaren on 5/7/2017.
 */

public class MatchHistoryDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "match_history.db";

    private static final int DATABASE_VERSION = 1;

    public MatchHistoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_MATCH_HISTORY_TABLE = "CREATE TABLE " + MatchHistoryContract.MatchHistoryEntry.TABLE_NAME + " ("
                + MatchHistoryContract.MatchHistoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER1_LIFE + " TEXT NOT NULL,"
                + MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER2_LIFE + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_MATCH_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
