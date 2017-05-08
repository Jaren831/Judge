package com.app.android.judge.History;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.android.judge.Data.MatchHistoryContract;
import com.app.android.judge.R;

/**
 * Created by jaren on 5/7/2017.
 */

public class HistoryCursorAdapter extends android.widget.CursorAdapter {

    private final LayoutInflater cursorInflater;

    public HistoryCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(final Context context, Cursor cursor, final ViewGroup parent) {
        return cursorInflater.inflate(R.layout.history_list_item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        // Find fields to populate in inflated template
        TextView player1 = (TextView) view.findViewById(R.id.history_player1);
        TextView player2 = (TextView) view.findViewById(R.id.history_player2);
        TextView historyId  = (TextView) view.findViewById(R.id.history_item_id);

        String player1Life = cursor.getString(cursor.getColumnIndex(MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER1_LIFE));
        String player2Life = cursor.getString(cursor.getColumnIndex(MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER2_LIFE));
        String player1Color = cursor.getString(cursor.getColumnIndex(MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER1_COLOR));
        String player2Color = cursor.getString(cursor.getColumnIndex(MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER2_COLOR));
        String historyId_text = cursor.getString(cursor.getColumnIndex(MatchHistoryContract.MatchHistoryEntry._ID));


        player1.setText(player1Life);
        player2.setText(player2Life);
        player1.setBackgroundColor(Color.parseColor(player1Color));
        player2.setBackgroundColor(Color.parseColor(player2Color));
        historyId.setText(historyId_text);
    }

}
