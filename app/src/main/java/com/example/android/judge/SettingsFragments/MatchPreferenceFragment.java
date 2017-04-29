package com.example.android.judge.SettingsFragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.example.android.judge.R;

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

        Preference player2Life = findPreference(getString(R.string.player2_life_key));
        Preference player2Color = findPreference(getString(R.string.player2_color_key));

        bindPreferenceSummaryToValue(player1Life);
        bindPreferenceSummaryToValue(player1Color);

        bindPreferenceSummaryToValue(player2Life);
        bindPreferenceSummaryToValue(player2Color);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                CharSequence[] labels = listPreference.getEntries();
                preference.setSummary(labels[prefIndex]);
            }
        } else {
            preference.setSummary(stringValue);
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
