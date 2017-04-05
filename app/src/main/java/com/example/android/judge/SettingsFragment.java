package com.example.android.judge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.judge.SettingsFragments.MatchSettingsFragment;
import com.example.android.judge.SettingsFragments.RulebookSettingsFragment;
import com.example.android.judge.SettingsFragments.SearchSettingsFragment;


public class SettingsFragment extends android.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_settings, container, false);

        Bundle bundle = this.getArguments();


        switch (bundle.getInt("fragment")) {
            case 0:
                MatchSettingsFragment matchPreferenceFragment = new MatchSettingsFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, matchPreferenceFragment)
                        .commit();
                break;
            case 1:
                SearchSettingsFragment searchSettingsFragment = new SearchSettingsFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, searchSettingsFragment)
                        .commit();
                break;
            case 2:
                RulebookSettingsFragment rulebookSettingsFragment = new RulebookSettingsFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.settings_container, rulebookSettingsFragment)
                        .commit();
                break;
        }
        return rootView;

    }



}