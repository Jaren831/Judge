package com.example.android.judge;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MatchFragment extends Fragment implements View.OnClickListener {

    TextView player1LifeView;
    TextView player2LifeView;
    FloatingActionButton player1Increment;
    FloatingActionButton player1Decrement;
    FloatingActionButton player2Increment;
    FloatingActionButton player2Decrement;

    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_match, container, false);
        return rootView;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
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

        player1LifeView = (TextView) rootView.findViewById(R.id.player1_life);
        player1Increment = (FloatingActionButton) rootView.findViewById(R.id.player1_increment);
        player1Decrement = (FloatingActionButton) rootView.findViewById(R.id.player1_decrement);

        player1LifeView.setText(player1Life);
        player1LifeView.setBackgroundColor(Color.parseColor(player1Color));
        player1Increment.setBackgroundColor(Color.parseColor(player1Color));
        player1Decrement.setBackgroundColor(Color.parseColor(player1Color));

        player2LifeView = (TextView) rootView.findViewById(R.id.player2_life);
        player2Increment = (FloatingActionButton) rootView.findViewById(R.id.player2_increment);
        player2Decrement = (FloatingActionButton) rootView.findViewById(R.id.player2_decrement);

        player2LifeView.setText(player2Life);
        player2LifeView.setBackgroundColor(Color.parseColor(player2Color));
        player2Increment.setBackgroundColor(Color.parseColor(player2Color));
        player2Decrement.setBackgroundColor(Color.parseColor(player2Color));

    }

    @Override
    public void onClick(View v) {
        // implements your things
    }
}