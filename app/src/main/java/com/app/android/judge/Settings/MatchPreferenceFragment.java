package com.app.android.judge.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
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
        implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.match_preference);

        Preference player1Life = findPreference(getString(R.string.player1_life_key));
        Preference player1Color = findPreference(getString(R.string.player1_color_key));
        Preference player1Energy = findPreference(getString(R.string.player1_energy_key));
        Preference player1Clues = findPreference(getString(R.string.player1_clue_key));
        Preference player1Poison = findPreference(getString(R.string.player1_poison_key));

        Preference player2Life = findPreference(getString(R.string.player2_life_key));
        Preference player2Color = findPreference(getString(R.string.player2_color_key));
        Preference player2Energy = findPreference(getString(R.string.player2_energy_key));
        Preference player2Clues = findPreference(getString(R.string.player2_clue_key));
        Preference player2Poison = findPreference(getString(R.string.player2_poison_key));


        bindPreferenceSummaryToValue(player1Life);
        bindPreferenceSummaryToValue(player1Color);
        bindPreferenceSummaryToValue(player1Energy);
        bindPreferenceSummaryToValue(player1Clues);
        bindPreferenceSummaryToValue(player1Poison);

        bindPreferenceSummaryToValue(player2Life);
        bindPreferenceSummaryToValue(player2Color);
        bindPreferenceSummaryToValue(player2Energy);
        bindPreferenceSummaryToValue(player2Clues);
        bindPreferenceSummaryToValue(player2Poison);

        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_settings).setVisible(false);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onPreferenceChange(Preference preferences, Object key) {
        String stringValue = key.toString();
        if (preferences instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preferences;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                CharSequence[] labels = listPreference.getEntries();
                preferences.setSummary(labels[prefIndex]);
            }
        } else if (preferences instanceof EditTextPreference) {
            preferences.setSummary(stringValue);
        }
        return true;
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
        String preferenceString = preferences.getString(preference.getKey(), "");
        onPreferenceChange(preference, preferenceString);
    }
}
