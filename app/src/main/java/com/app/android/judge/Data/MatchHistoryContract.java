package com.app.android.judge.Data;

import android.provider.BaseColumns;

/**
 * Created by jaren on 5/7/2017.
 */

public class MatchHistoryContract {
    private MatchHistoryContract () {}

    public static final class MatchHistoryEntry implements BaseColumns {

        public final static String TABLE_NAME = "match_history";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PLAYER1_LIFE = "player1";
        public final static String COLUMN_PLAYER2_LIFE = "player2";
        public final static String COLUMN_PLAYER1_COLOR = "player1_color";
        public final static String COLUMN_PLAYER2_COLOR = "player2_color";
    }
}
