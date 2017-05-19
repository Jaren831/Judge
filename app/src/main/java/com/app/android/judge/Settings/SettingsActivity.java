package com.app.android.judge.Settings;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.app.android.judge.Search.CardSearchFragment;
import com.app.android.judge.Match.MatchFragment;
import com.app.android.judge.R;

import com.app.android.judge.RuleBook.RuleBookFragment;

public class SettingsActivity extends AppCompatActivity {

    private int currentFragmentInt;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_settings);
        Bundle bundle = this.getIntent().getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switch (bundle.getInt("fragment")) {
            case 0:
                MatchPreferenceFragment matchPreferenceFragment = new MatchPreferenceFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, matchPreferenceFragment)
                        .commit();
                break;
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
            public void onClick(DialogInterface dialog, int id) {
                switch (currentFragmentInt) {
                    case 0:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(getString(R.string.player1_life_key), getString(R.string.player1_life_default_value));
                        editor.putString(getString(R.string.player1_color_key), getString(R.string.player1_color_default_value));
                        editor.putString(getString(R.string.player1_energy_key), getString(R.string.player1_energy_default_value));
                        editor.putString(getString(R.string.player1_clue_key), getString(R.string.player1_clue_default_value));
                        editor.putString(getString(R.string.player1_poison_key), getString(R.string.player1_poison_default_value));
                        editor.putBoolean(getString(R.string.player1_counters_key), true);

                        editor.putString(getString(R.string.player2_life_key), getString(R.string.player2_life_default_value));
                        editor.putString(getString(R.string.player2_color_key), getString(R.string.player2_color_default_value));
                        editor.putString(getString(R.string.player2_energy_key), getString(R.string.player2_energy_default_value));
                        editor.putString(getString(R.string.player2_clue_key), getString(R.string.player2_clue_default_value));
                        editor.putString(getString(R.string.player2_poison_key), getString(R.string.player2_poison_default_value));
                        editor.putBoolean(getString(R.string.player2_counters_key), true);

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