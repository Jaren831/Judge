package com.example.android.judge;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MatchFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String player1Life = sharedPreferences.getString(
                getString(R.string.player1_life_key),
                getString(R.string.player1_life_default_value));
        String player1Color = sharedPreferences.getString(
                getString(R.string.player1_color_key),
                getString(R.string.player1_color_default_value));

        String player2Life = sharedPreferences.getString(
                getString(R.string.player2_life_key),
                getString(R.string.player2_life_default_value));
        String player2Color = sharedPreferences.getString(
                getString(R.string.player2_color_key),
                getString(R.string.player2_color_default_value));

        TextView player1LifeView = (TextView) getActivity().findViewById(R.id.player1_life);
        player1LifeView.setText(player1Life);
        player1LifeView.setBackgroundColor(Color.parseColor(player1Color));

        TextView player2LifeView = (TextView) getActivity().findViewById(R.id.player2_life);
        player2LifeView.setText(player2Life);
        player2LifeView.setBackgroundColor(Color.parseColor(player2Color));

        return inflater.inflate(R.layout.fragment_match, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }





}