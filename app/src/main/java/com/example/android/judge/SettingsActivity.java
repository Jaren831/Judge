package com.example.android.judge;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.judge.SettingsFragments.MatchPreferenceFragment;
import com.example.android.judge.SettingsFragments.RulebookPreferenceFragment;
import com.example.android.judge.SettingsFragments.SearchPreferenceFragment;


public class SettingsActivity extends AppCompatActivity {

    int currentFragmentInt;
    SharedPreferences sharedPreferences;


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
            case 1:
                SearchPreferenceFragment searchPreferenceFragment = new SearchPreferenceFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, searchPreferenceFragment)
                        .commit();
                break;
            case 2:
                RulebookPreferenceFragment rulebookPreferenceFragment = new RulebookPreferenceFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, rulebookPreferenceFragment)
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

    public void CurrentFragmentReset() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (currentFragment instanceof MatchFragment) {
            currentFragmentInt = 0;
        } else if (currentFragment instanceof CardSearchFragment) {
            currentFragmentInt = 1;
        } else if (currentFragment instanceof RuleBookFragment) {
            currentFragmentInt = 2;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("My Title");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                switch (currentFragmentInt) {
                    case 0:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(getString(R.string.player1_life_key), getString(R.string.player1_life_default_value));
                        editor.putString(getString(R.string.player2_life_key), getString(R.string.player2_life_default_value));
                        editor.putString(getString(R.string.player1_color_key), getString(R.string.player1_color_default_value));
                        editor.putString(getString(R.string.player2_color_key), getString(R.string.player2_color_default_value));
                        editor.apply();
                        MatchPreferenceFragment matchPreferenceFragment = new MatchPreferenceFragment();
                        getFragmentManager().beginTransaction().replace(R.id.settings_container, matchPreferenceFragment).commit();
                        break;
                    case 1:
                        break;
                    case 2:
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