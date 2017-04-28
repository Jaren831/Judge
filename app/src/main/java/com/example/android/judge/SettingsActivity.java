package com.example.android.judge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.android.judge.SettingsFragments.MatchSettingsFragment;
import com.example.android.judge.SettingsFragments.RulebookSettingsFragment;
import com.example.android.judge.SettingsFragments.SearchSettingsFragment;


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
                MatchSettingsFragment matchPreferenceFragment = new MatchSettingsFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, matchPreferenceFragment)
                        .commit();
                break;
            case 1:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();

                SearchSettingsFragment searchSettingsFragment = new SearchSettingsFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, searchSettingsFragment)
                        .commit();
                break;
            case 2:
                Toast.makeText(this, "Rule", Toast.LENGTH_SHORT).show();

                RulebookSettingsFragment rulebookSettingsFragment = new RulebookSettingsFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, rulebookSettingsFragment)
                        .commit();
                break;
        }
    }
}