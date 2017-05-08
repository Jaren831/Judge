package com.app.android.judge.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jaren on 5/7/2017.
 */

public class MatchHistoryContract {

    public static final String AUTHORITY = "com.app.android.judge";
    public static final String PATH_MATCH = "match";
    public static final String PATH_MATCH_WITH_ID = "match/*";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);


    private MatchHistoryContract () {}

    public static final class MatchHistoryEntry implements BaseColumns {

        public final static String TABLE_NAME = "match_history";
        public static final Uri URI = BASE_URI.buildUpon().appendPath(PATH_MATCH).build();
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PLAYER1_LIFE = "player1";
        public final static String COLUMN_PLAYER2_LIFE = "player2";
        public final static String COLUMN_PLAYER1_COLOR = "player1_color";
        public final static String COLUMN_PLAYER2_COLOR = "player2_color";
        public static final int POSITION_ID = 0;
        public static final int POSITION_PLAYER1_LIFE = 1;
        public static final int POSITION_PLAYER2_LIFE = 2;
        public static final int POSITION_PLAYER1_COLOR = 3;
        public static final int POSITION_PLAYER2_COLOR = 4;
        public static final String[] MATCH_COLUMNS = {
                _ID,
                COLUMN_PLAYER1_LIFE,
                COLUMN_PLAYER2_LIFE,
                COLUMN_PLAYER1_COLOR,
                COLUMN_PLAYER2_COLOR
        };

        public static Uri makeUriForStock(String symbol) {
            return URI.buildUpon().appendPath(symbol).build();
        }

        public static String getMatchFromUri(Uri queryUri) {
            return queryUri.getLastPathSegment();
        }

    }
}
