package com.app.android.judge.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jaren on 5/8/2017.
 */

public class MatchHistoryProvider extends ContentProvider {


    static final int MATCH = 0;
    static final int MATCH_ID = 1;

    static UriMatcher uriMatcher = buildUriMatcher();

    private MatchHistoryDBHelper matchHistoryDBHelper;

    static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(MatchHistoryContract.AUTHORITY, MatchHistoryContract.PATH_MATCH, MATCH);
        matcher.addURI(MatchHistoryContract.AUTHORITY, MatchHistoryContract.PATH_MATCH_WITH_ID, MATCH_ID);
        return matcher;
    }


    @Override
    public boolean onCreate() {
        matchHistoryDBHelper = new MatchHistoryDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor returnCursor;
        SQLiteDatabase db = matchHistoryDBHelper.getReadableDatabase();

        switch (uriMatcher.match(uri)) {
            case MATCH:
                returnCursor = db.query(
                        MatchHistoryContract.MatchHistoryEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case MATCH_ID:
                returnCursor = db.query(
                        MatchHistoryContract.MatchHistoryEntry.TABLE_NAME,
                        projection,
                        MatchHistoryContract.MatchHistoryEntry._ID + " = ?",
                        new String[]{MatchHistoryContract.MatchHistoryEntry.getMatchFromUri(uri)},
                        null,
                        null,
                        sortOrder
                );

                break;
            default:
                throw new UnsupportedOperationException("Unknown URI:" + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = matchHistoryDBHelper.getWritableDatabase();
        Uri returnUri;

        switch (uriMatcher.match(uri)) {
            case MATCH:
                db.insert(
                        MatchHistoryContract.MatchHistoryEntry.TABLE_NAME,
                        null,
                        values
                );
                returnUri = MatchHistoryContract.MatchHistoryEntry.URI;
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI:" + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);


        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = matchHistoryDBHelper.getWritableDatabase();
        int rowsDeleted;

        if (null == selection) {
            selection = "1";
        }
        switch (uriMatcher.match(uri)) {
            case MATCH:
                rowsDeleted = db.delete(
                        MatchHistoryContract.MatchHistoryEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );

                break;

            case MATCH_ID:
                String symbol = MatchHistoryContract.MatchHistoryEntry.getMatchFromUri(uri);
                rowsDeleted = db.delete(
                        MatchHistoryContract.MatchHistoryEntry.TABLE_NAME,
                        '"' + symbol + '"' + " =" + MatchHistoryContract.MatchHistoryEntry._ID,
                        selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI:" + uri);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {

        final SQLiteDatabase db = matchHistoryDBHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case MATCH:
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        db.insert(
                                MatchHistoryContract.MatchHistoryEntry.TABLE_NAME,
                                null,
                                value
                        );
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            default:
                return super.bulkInsert(uri, values);
        }


    }
}
