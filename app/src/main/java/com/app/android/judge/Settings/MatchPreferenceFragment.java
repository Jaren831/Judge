package com.app.android.judge.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.Menu;

import com.app.android.judge.R;

/**
 * Created by Jaren Lynch on 4/5/2017.
 */

public class MatchPreferenceFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.match_preference);

//        Preference player1Life = findPreference(getString(R.string.player1_life_key));
//        Preference player1Color = findPreference(getString(R.string.player1_color_key));
//
//        Preference player2Life = findPreference(getString(R.string.player2_life_key));
//        Preference player2Color = findPreference(getString(R.string.player2_color_key));
//
//        Preference player1Counters = findPreference(getString(R.string.player1_counters_key));
//        Preference player2Counters = findPreference(getString(R.string.player2_counters_key));
//
//        bindPreferenceSummaryToValue(player1Life);
//        bindPreferenceSummaryToValue(player1Color);
//        bindPreferenceSummaryToValue(player1Counters);
//
//        bindPreferenceSummaryToValue(player2Life);
//        bindPreferenceSummaryToValue(player2Color);
//        bindPreferenceSummaryToValue(player2Counters);

        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_settings).setVisible(false);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        if ( instanceof ListPreference) {
//            ListPreference listPreference = (ListPreference) sharedPreferences;
//            int prefIndex = listPreference.findIndexOfValue(key);
//            if (prefIndex >= 0) {
//                CharSequence[] labels = listPreference.getEntries();
//                ((ListPreference) sharedPreferences).setSummary(labels[prefIndex]);
//            }
//        } else {
//            ((ListPreference) sharedPreferences).setSummary(key);
//        }
        switch (key) {
            case "player1_life_key":
                Preference player1LifePref = findPreference(key);
                player1LifePref.setSummary(sharedPreferences.getString(key, ""));
                break;
            case "player1_color_key":
                Preference player1ColorPref = findPreference(key);
                player1ColorPref.setSummary(sharedPreferences.getString(key, ""));
                break;
            case "player1_counter_key":
                Preference player1CounterPref = findPreference(key);
                player1CounterPref.setSummary(sharedPreferences.getString(key, ""));
                break;
            case "player2_life_key":
                Preference player2LifePref = findPreference(key);
                player2LifePref.setSummary(sharedPreferences.getString(key, ""));
                break;
            case "player2_color_key":
                Preference player2ColorPref = findPreference(key);
                player2ColorPref.setSummary(sharedPreferences.getString(key, ""));
                break;
            case "player2_counter_key":
                Preference player2CounterPref = findPreference(key);
                player2CounterPref.setSummary(sharedPreferences.getString(key, ""));
                break;
        }
    }
//
//    private void bindPreferenceSummaryToValue(SharedPreferences sharedPreferences) {
//        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String preferenceString = sharedPreferences.getString(sharedPreferences.get(), "");
//        onSharedPreferenceChanged(shouldShowRequestPermissionRationale(), preferenceString);
//    }

//    sharedPreferences.();
//    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
//    String preferenceString = sharedPreferences.getString(preference.getKey(), "");
//    onSharedPreferenceChanged(shouldShowRequestPermissionRationale(), preferenceString);
}
