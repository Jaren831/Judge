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

public class Player1Fragment extends Fragment implements View.OnClickListener {

    TextView player1LifeView;
    ImageButton player1Increment;
    ImageButton player1Decrement;
    Integer player1CurrentLife;
    SharedPreferences sharedPreferences;
    View rootView;
    String player1Life;
    String player1Color;

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

        player1Life = sharedPreferences.getString(
                getString(R.string.player1_life_key),
                getString(R.string.player1_life_default_value));
        player1Color = sharedPreferences.getString(
                getString(R.string.player1_color_key),
                getString(R.string.player1_color_default_value));

        player1LifeView = (TextView) rootView.findViewById(R.id.player1_life);
        player1LifeView.setText(player1Life);
        player1LifeView.setBackgroundColor(Color.parseColor(player1Color));

        player1Increment = (ImageButton) rootView.findViewById(R.id.player1_increment);
        player1Increment.setImageDrawable(getResources().getDrawable(R.drawable.increment_black));
        player1Increment.setBackgroundColor(Color.parseColor(player1Color));
        player1Increment.setOnClickListener(this);


        player1Decrement = (ImageButton) rootView.findViewById(R.id.player1_decrement);
        player1Decrement.setImageDrawable(getResources().getDrawable(R.drawable.decrement_black));
        player1Decrement.setBackgroundColor(Color.parseColor(player1Color));
        player1Decrement.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.player1_life_key), player1LifeView.getText().toString());
        editor.apply();
    }

    @Override
    public void onClick(View view) {
        player1CurrentLife = Integer.parseInt(player1LifeView.getText().toString());
        switch (view.getId()) {
            case R.id.player1_increment:
                player1CurrentLife += 1;
                player1LifeView.setText(player1CurrentLife.toString());
                break;
            case R.id.player1_decrement:
                player1CurrentLife -= 1;
                player1LifeView.setText(player1CurrentLife.toString());
                break;
        }
    }
}
