package com.example.android.judge;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class MatchFragment extends Fragment implements View.OnClickListener {

    TextView player1LifeView;
    TextView player2LifeView;
    ImageButton player1Increment;
    ImageButton player1Decrement;
    ImageButton player2Increment;
    ImageButton player2Decrement;

    Integer player1CurrentLife;
    Integer player2CurrentLife;

    SharedPreferences sharedPreferences;

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
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

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


        player2LifeView = (TextView) rootView.findViewById(R.id.player2_life);
        player2LifeView.setText(player2Life);
        player2LifeView.setBackgroundColor(Color.parseColor(player2Color));

        player2Increment = (ImageButton) rootView.findViewById(R.id.player2_increment);
        player2Increment.setImageDrawable(getResources().getDrawable(R.drawable.increment_black));
        player2Increment.setBackgroundColor(Color.parseColor(player2Color));
        player2Increment.setOnClickListener(this);

        player2Decrement = (ImageButton) rootView.findViewById(R.id.player2_decrement);
        player2Decrement.setImageDrawable(getResources().getDrawable(R.drawable.decrement_black));
        player2Decrement.setBackgroundColor(Color.parseColor(player2Color));
        player2Decrement.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.player1_life_key), player1CurrentLife.toString());
        editor.putString(getString(R.string.player2_life_key), player2CurrentLife.toString());
        editor.apply();

    }


    @Override
    public void onClick(View view) {

        player1CurrentLife = Integer.parseInt(player1LifeView.getText().toString());
        player2CurrentLife = Integer.parseInt(player2LifeView.getText().toString());
        switch (view.getId()) {
            case R.id.player1_increment:
                player1CurrentLife += 1;
                player1LifeView.setText(player1CurrentLife.toString());
                break;
            case R.id.player1_decrement:
                player1CurrentLife -= 1;
                player1LifeView.setText(player1CurrentLife.toString());
                break;
            case R.id.player2_increment:
                player2CurrentLife += 1;
                player2LifeView.setText(player2CurrentLife.toString());
                break;
            case R.id.player2_decrement:
                player2CurrentLife -= 1;
                player2LifeView.setText(player2CurrentLife.toString());
        }
    }
}