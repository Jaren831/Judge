package com.example.android.judge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.android.judge.SettingsFragments.MatchPreferenceFragment;
import com.example.android.judge.SettingsFragments.RulebookPreferenceFragment;
import com.example.android.judge.SettingsFragments.SearchPreferenceFragment;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_settings);
        Bundle bundle = this.getIntent().getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switch (bundle.getInt("fragment")) {
            case 0:
                Toast.makeText(this, "Match", Toast.LENGTH_SHORT).show();
                MatchPreferenceFragment matchPreferenceFragment = new MatchPreferenceFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, matchPreferenceFragment)
                        .commit();
                break;
            case 1:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();

                SearchPreferenceFragment searchPreferenceFragment = new SearchPreferenceFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, searchPreferenceFragment)
                        .commit();
                break;
            case 2:
                Toast.makeText(this, "Rule", Toast.LENGTH_SHORT).show();

                RulebookPreferenceFragment rulebookPreferenceFragment = new RulebookPreferenceFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, rulebookPreferenceFragment)
                        .commit();
                break;
        }
    }
}