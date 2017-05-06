package com.example.android.judge.Match;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.judge.R;

/**
 * Created by jaren on 5/5/2017.
 */

public class Player2Fragment extends Fragment implements View.OnClickListener {

    TextView player2LifeView;
    ImageButton player2Increment;
    ImageButton player2Decrement;
    Integer player2CurrentLife;
    SharedPreferences sharedPreferences;
    View rootView;
    String player2Life;
    String player2Color;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.player1_fragment, container, false);
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
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        player2Life = sharedPreferences.getString(
                getString(R.string.player2_life_key),
                getString(R.string.player2_life_default_value));
        player2Color = sharedPreferences.getString(
                getString(R.string.player2_color_key),
                getString(R.string.player2_color_default_value));

        player2LifeView = (TextView) rootView.findViewById(R.id.player1_life);
        player2LifeView.setText(player2Life);
        player2LifeView.setBackgroundColor(Color.parseColor(player2Color));

        player2Increment = (ImageButton) rootView.findViewById(R.id.player1_increment);
        player2Increment.setImageDrawable(getResources().getDrawable(R.drawable.increment_black));
        player2Increment.setBackgroundColor(Color.parseColor(player2Color));
        player2Increment.setOnClickListener(this);


        player2Decrement = (ImageButton) rootView.findViewById(R.id.player1_decrement);
        player2Decrement.setImageDrawable(getResources().getDrawable(R.drawable.decrement_black));
        player2Decrement.setBackgroundColor(Color.parseColor(player2Color));
        player2Decrement.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.player1_life_key), player2LifeView.getText().toString());
        editor.apply();
    }

    @Override
    public void onClick(View view) {
        player2CurrentLife = Integer.parseInt(player2LifeView.getText().toString());
        switch (view.getId()) {
            case R.id.player1_increment:
                player2CurrentLife += 1;
                player2LifeView.setText(player2CurrentLife.toString());
                break;
            case R.id.player1_decrement:
                player2CurrentLife -= 1;
                player2LifeView.setText(player2CurrentLife.toString());
                break;
        }
    }
}
