package com.app.android.judge.History;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.android.judge.Data.MatchHistoryContract;
import com.app.android.judge.Data.MatchHistoryDBHelper;
import com.app.android.judge.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jaren on 5/7/2017.
 */

public class HistoryFragment extends Fragment {


    private View rootView;
    private HistoryCursorAdapter historyCursorAdapter;
    private Cursor cursor;

    private MatchHistoryDBHelper matchHistoryDBHelper;
    private ListView historyListView;

    private TextView emptyTextView;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference(user.toString());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Toast.makeText(getContext(), databaseReference.toString(), Toast.LENGTH_SHORT).show();
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
