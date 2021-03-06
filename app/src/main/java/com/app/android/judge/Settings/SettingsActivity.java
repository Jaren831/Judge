package com.app.android.judge.Settings;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.android.judge.Data.MatchHistoryContract;
import com.app.android.judge.Data.MatchHistoryDBHelper;
import com.app.android.judge.Search.CardSearchFragment;
import com.app.android.judge.Match.MatchFragment;
import com.app.android.judge.R;

import com.app.android.judge.RuleBook.RuleBookFragment;

import java.util.ArrayList;


public class SettingsActivity extends AppCompatActivity {

    private int currentFragmentInt;
    private SharedPreferences sharedPreferences;
    private static MatchHistoryDBHelper matchHistoryDBHelper;
    public static Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_settings);
        Bundle bundle = this.getIntent().getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        switch (bundle.getInt("fragment")) {
            case 0:
                MatchPreferenceFragment matchPreferenceFragment = new MatchPreferenceFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, matchPreferenceFragment)
                        .commit();
                break;
////            case 1:
////                SearchPreferenceFragment searchPreferenceFragment = new SearchPreferenceFragment();
////                getFragmentManager().beginTransaction()
////                        .add(R.id.settings_container, searchPreferenceFragment)
////                        .commit();
//                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fragment_menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_reset) {
            CurrentFragmentReset();
            return true;
        } else {
            return false;
        }
    }

    private void CurrentFragmentReset() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (currentFragment instanceof MatchFragment) {
            currentFragmentInt = 0;
        } else if (currentFragment instanceof CardSearchFragment) {
            currentFragmentInt = 1;
        } else if (currentFragment instanceof RuleBookFragment) {
            currentFragmentInt = 2;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.match_settings_reset_title);
        builder.setMessage(R.string.match_settings_reset_message);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            ArrayList<ContentValues> historyCVs = new ArrayList<>();
            public void onClick(DialogInterface dialog, int id) {
                switch (currentFragmentInt) {
                    case 0:
                        String player1Life = sharedPreferences.getString(
                                getString(R.string.player1_life_key),
                                getString(R.string.player1_life_default_value));
                        String player2Life = sharedPreferences.getString(
                                getString(R.string.player2_life_key),
                                getString(R.string.player2_life_default_value));
                        String player1Color = sharedPreferences.getString(
                                getString(R.string.player1_color_key),
                                getString(R.string.player1_color_default_value));
                        String player2Color = sharedPreferences.getString(
                                getString(R.string.player2_color_key),
                                getString(R.string.player2_color_default_value));
                        ContentValues historyCV = new ContentValues();
                        historyCV.put(MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER1_LIFE, player1Life);
                        historyCV.put(MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER2_LIFE, player2Life);
                        historyCV.put(MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER1_COLOR, player1Color);
                        historyCV.put(MatchHistoryContract.MatchHistoryEntry.COLUMN_PLAYER2_COLOR, player2Color);

                        historyCVs.add(historyCV);

                        getContentResolver()
                                .bulkInsert(
                                        MatchHistoryContract.MatchHistoryEntry.URI,
                                        historyCVs.toArray(new ContentValues[historyCVs.size()]));


                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(getString(R.string.player1_life_key), getString(R.string.player1_life_default_value));
                        editor.putString(getString(R.string.player2_life_key), getString(R.string.player2_life_default_value));
                        editor.putString(getString(R.string.player1_color_key), getString(R.string.player1_color_default_value));
                        editor.putString(getString(R.string.player2_color_key), getString(R.string.player2_color_default_value));
                        editor.apply();
                        MatchPreferenceFragment matchPreferenceFragment = new MatchPreferenceFragment();
                        getFragmentManager().beginTransaction().replace(R.id.settings_container, matchPreferenceFragment).commit();
                        break;
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}