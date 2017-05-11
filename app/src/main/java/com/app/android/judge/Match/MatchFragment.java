package com.app.android.judge.Match;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.android.judge.R;

public class MatchFragment extends Fragment implements View.OnClickListener {

    private TextView player1LifeView;
    private TextView player2LifeView;
    private SharedPreferences sharedPreferences;

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        rootView = inflater.inflate(R.layout.fragment_match, container, false);
        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_reset).setVisible(false);
        super.onPrepareOptionsMenu(menu);
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


        ImageButton player1Increment = (ImageButton) rootView.findViewById(R.id.player1_increment);
        player1Increment.setImageDrawable(getResources().getDrawable(R.drawable.increment_black));
        player1Increment.setBackgroundColor(Color.parseColor(player1Color));
        player1Increment.setOnClickListener(this);


        ImageButton player1Decrement = (ImageButton) rootView.findViewById(R.id.player1_decrement);
        player1Decrement.setImageDrawable(getResources().getDrawable(R.drawable.decrement_black));
        player1Decrement.setBackgroundColor(Color.parseColor(player1Color));
        player1Decrement.setOnClickListener(this);


        player2LifeView = (TextView) rootView.findViewById(R.id.player2_life);
        player2LifeView.setText(player2Life);
        player2LifeView.setBackgroundColor(Color.parseColor(player2Color));


        ImageButton player2Increment = (ImageButton) rootView.findViewById(R.id.player2_increment);
        player2Increment.setImageDrawable(getResources().getDrawable(R.drawable.increment_black));
        player2Increment.setBackgroundColor(Color.parseColor(player2Color));
        player2Increment.setOnClickListener(this);

        ImageButton player2Decrement = (ImageButton) rootView.findViewById(R.id.player2_decrement);
        player2Decrement.setImageDrawable(getResources().getDrawable(R.drawable.decrement_black));
        player2Decrement.setBackgroundColor(Color.parseColor(player2Color));
        player2Decrement.setOnClickListener(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.player1_life_key), player1LifeView.getText().toString());
        editor.putString(getString(R.string.player2_life_key), player2LifeView.getText().toString());
        editor.apply();
    }

    @Override
    public void onClick(View view) {

        Integer player1CurrentLife = Integer.parseInt(player1LifeView.getText().toString());
        Integer player2CurrentLife = Integer.parseInt(player2LifeView.getText().toString());
        switch (view.getId()) {
            case R.id.player1_increment:
                player1CurrentLife += 1;
                player1LifeView.setText(String.format(player1CurrentLife.toString()));

                break;
            case R.id.player1_decrement:
                player1CurrentLife -= 1;
                player1LifeView.setText(String.format(player1CurrentLife.toString()));
                break;
            case R.id.player2_increment:
                player2CurrentLife += 1;
                player2LifeView.setText(String.format(player2CurrentLife.toString()));
                break;
            case R.id.player2_decrement:
                player2CurrentLife -= 1;
                player2LifeView.setText(String.format(player2CurrentLife.toString()));
                break;
        }
    }
}