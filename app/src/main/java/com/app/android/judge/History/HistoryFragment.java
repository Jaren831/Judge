package com.app.android.judge.History;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.android.judge.Data.MatchHistoryContract;
import com.app.android.judge.Data.MatchHistoryDBHelper;
import com.app.android.judge.MainActivity;
import com.app.android.judge.R;
import com.squareup.picasso.Picasso;

/**
 * Created by jaren on 5/7/2017.
 */

public class HistoryFragment extends Fragment {


    private View rootView;
    HistoryCursorAdapter historyCursorAdapter;
    private Cursor cursor;

    private MatchHistoryDBHelper matchHistoryDBHelper;
    private ListView historyListView;

    TextView emptyTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.history_title));
        setHasOptionsMenu(true);
        rootView = inflater.inflate(R.layout.fragment_history, container, false);
        emptyTextView = (TextView) rootView.findViewById(R.id.history_empty);
        matchHistoryDBHelper = new MatchHistoryDBHelper(getContext());
        historyListView = (ListView) rootView.findViewById(R.id.history_listView);
        historyListView.setEmptyView(emptyTextView);
        displayDatabaseInfo();
        return rootView;
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_reset).setVisible(true);
        menu.findItem(R.id.action_settings).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void displayDatabaseInfo() {
        matchHistoryDBHelper = new MatchHistoryDBHelper(getContext());

        SQLiteDatabase db = matchHistoryDBHelper.getReadableDatabase();

        String[] project = {
                MatchHistoryContract.MatchHistoryEntry._ID,
                MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER1_LIFE,
                MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER2_LIFE,
                MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER1_COLOR,
                MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER2_COLOR
        };

        cursor = db.query(
                MatchHistoryContract.MatchHistoryEntry.TABLE_NAME,
                project,
                null,
                null,
                null,
                null,
                null);

        historyListView = (ListView) rootView.findViewById(R.id.history_listView);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                historyCursorAdapter = new HistoryCursorAdapter(
                        getContext(),
                        cursor);
                historyListView.setAdapter(historyCursorAdapter);
            }
        });
    }
}
