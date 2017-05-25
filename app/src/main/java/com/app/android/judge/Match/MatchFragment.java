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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.app.android.judge.R;

public class MatchFragment extends Fragment implements View.OnClickListener {

    private TextView player1LifeView;
    private TextView player2LifeView;
    private SharedPreferences sharedPreferences;


    private TextView player1EnergyText;

    private TextView player1ClueText;

    private TextView player1PoisonText;

    private TextView player2EnergyText;

    private TextView player2ClueText;

    private TextView player2PoisonText;

    private String player1Color;
    private String player2Color;

    private LinearLayout player1CountersLayout;
    private LinearLayout player2CountersLayout;

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        rootView = inflater.inflate(R.layout.fragment_match, container, false);
        player1CountersLayout = (LinearLayout) rootView.findViewById(R.id.player1_counters_view);
        player2CountersLayout = (LinearLayout) rootView.findViewById(R.id.player2_counters_view);
        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_reset).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        setPlayer1Life();
        setPlayer2Life();
        if (player1CounterCheck()) {
            player1CountersLayout.setVisibility(View.VISIBLE);
            setPlayer1Counters();
        } else {
            player1CountersLayout.setVisibility(View.GONE);
        }

        if (player2CounterCheck()) {
            player2CountersLayout.setVisibility(View.VISIBLE);
            setPlayer2Counters();
        } else {
            player2CountersLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        savePlayerValues();
    }

    @Override
    public void onClick(View view) {
        updatePlayer1(view);
        updatePlayer2(view);
    }

    private void setPlayer1Life() {
        String player1Life = sharedPreferences.getString(
                getString(R.string.player1_life_key),
                getString(R.string.player1_life_default_value));
        player1Color = sharedPreferences.getString(
                getString(R.string.player1_color_key),
                getString(R.string.player1_color_default_value));

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
    }

    private void setPlayer2Life() {
        String player2Life = sharedPreferences.getString(
                getString(R.string.player2_life_key),
                getString(R.string.player2_life_default_value));
        player2Color = sharedPreferences.getString(
                getString(R.string.player2_color_key),
                getString(R.string.player2_color_default_value));

        player1CountersLayout.setVisibility(View.VISIBLE);

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

    private void setPlayer1Counters() {
        String player1Energy = sharedPreferences.getString(
                getString(R.string.player1_energy_key),
                getString(R.string.player1_energy_default_value));
        String player1Clue = sharedPreferences.getString(
                getString(R.string.player1_clue_key),
                getString(R.string.player1_clue_default_value));
        String player1Poison = sharedPreferences.getString(
                getString(R.string.player1_poison_key),
                getString(R.string.player1_poison_default_value));

        player1CountersLayout.setVisibility(View.VISIBLE);
        player1CountersLayout.setBackgroundColor(Color.parseColor(player1Color));

        player1EnergyText = (TextView) rootView.findViewById(R.id.player1_energy_text);
        player1EnergyText.setText(player1Energy);
        ImageButton player1EnergyIncrement = (ImageButton) rootView.findViewById(R.id.player1_energy_increment);
        player1EnergyIncrement.setOnClickListener(this);
        ImageButton player1EnergyDecrement = (ImageButton) rootView.findViewById(R.id.player1_energy_decrement);
        player1EnergyDecrement.setOnClickListener(this);

        player1ClueText = (TextView) rootView.findViewById(R.id.player1_clue_text);
        player1ClueText.setText(player1Clue);
        ImageButton player1ClueIncrement = (ImageButton) rootView.findViewById(R.id.player1_clue_increment);
        player1ClueIncrement.setOnClickListener(this);
        ImageButton player1ClueDecrement = (ImageButton) rootView.findViewById(R.id.player1_clue_decrement);
        player1ClueDecrement.setOnClickListener(this);

        player1PoisonText = (TextView) rootView.findViewById(R.id.player1_poison_text);
        player1PoisonText.setText(player1Poison);
        ImageButton player1PoisonIncrement = (ImageButton) rootView.findViewById(R.id.player1_poison_increment);
        player1PoisonIncrement.setOnClickListener(this);
        ImageButton player1PoisonDecrement = (ImageButton) rootView.findViewById(R.id.player1_poison_decrement);
        player1PoisonDecrement.setOnClickListener(this);
    }

    private void setPlayer2Counters() {
        String player2Energy = sharedPreferences.getString(
                getString(R.string.player2_energy_key),
                getString(R.string.player2_energy_default_value));
        String player2Clue = sharedPreferences.getString(
                getString(R.string.player2_clue_key),
                getString(R.string.player2_clue_default_value));
        String player2Poison = sharedPreferences.getString(
                getString(R.string.player2_poison_key),
                getString(R.string.player2_poison_default_value));

        player2CountersLayout.setBackgroundColor(Color.parseColor(player2Color));

        player2EnergyText = (TextView) rootView.findViewById(R.id.player2_energy_text);
        player2EnergyText.setText(player2Energy);
        ImageButton player2EnergyIncrement = (ImageButton) rootView.findViewById(R.id.player2_energy_increment);
        player2EnergyIncrement.setOnClickListener(this);
        ImageButton player2EnergyDecrement = (ImageButton) rootView.findViewById(R.id.player2_energy_decrement);
        player2EnergyDecrement.setOnClickListener(this);

        player2ClueText = (TextView) rootView.findViewById(R.id.player2_clue_text);
        player2ClueText.setText(player2Clue);
        ImageButton player2ClueIncrement = (ImageButton) rootView.findViewById(R.id.player2_clue_increment);
        player2ClueIncrement.setOnClickListener(this);
        ImageButton player2ClueDecrement = (ImageButton) rootView.findViewById(R.id.player2_clue_decrement);
        player2ClueDecrement.setOnClickListener(this);

        player2PoisonText = (TextView) rootView.findViewById(R.id.player2_poison_text);
        player2PoisonText.setText(player2Poison);
        ImageButton player2PoisonIncrement = (ImageButton) rootView.findViewById(R.id.player2_poison_increment);
        player2PoisonIncrement.setOnClickListener(this);
        ImageButton player2PoisonDecrement = (ImageButton) rootView.findViewById(R.id.player2_poison_decrement);
        player2PoisonDecrement.setOnClickListener(this);
    }

    private void savePlayerValues() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //player 1
        editor.putString(getString(R.string.player1_life_key), player1LifeView.getText().toString());
        if (player1CounterCheck()) {
            editor.putString(getString(R.string.player1_energy_key), player1EnergyText.getText().toString());
            editor.putString(getString(R.string.player1_clue_key), player1ClueText.getText().toString());
            editor.putString(getString(R.string.player1_poison_key), player1PoisonText.getText().toString());
        }
        // player 2
        editor.putString(getString(R.string.player2_life_key), player2LifeView.getText().toString());
        if (player2CounterCheck()) {
            editor.putString(getString(R.string.player2_energy_key), player2EnergyText.getText().toString());
            editor.putString(getString(R.string.player2_clue_key), player2ClueText.getText().toString());
            editor.putString(getString(R.string.player2_poison_key), player2PoisonText.getText().toString());
        }

        editor.apply();

        player1CountersLayout.setVisibility(View.GONE);
        player2CountersLayout.setVisibility(View.GONE);
    }

    private boolean player1CounterCheck(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return sharedPreferences.getBoolean("player1_counters",true);
    }
    private boolean player2CounterCheck(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return sharedPreferences.getBoolean("player2_counters",true);
    }

    private void updatePlayer1(View view) {
        updatePlayer1Life(view);
        if (player1CounterCheck()) {
            updatePlayer1Counter(view);
        }
    }
    private void updatePlayer2(View view) {
        updatePlayer2Life(view);
        if (player2CounterCheck()) {
            updatePlayer2Counter(view);
        }
    }

    private void updatePlayer1Life(View view) {
        Integer player1CurrentLife = Integer.parseInt(player1LifeView.getText().toString());
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

    private void updatePlayer1Counter(View view) {
        Integer player1CurrentEnergy = Integer.parseInt(player1EnergyText.getText().toString());
        Integer player1CurrentClue = Integer.parseInt(player1ClueText.getText().toString());
        Integer player1CurrentPoison = Integer.parseInt(player1PoisonText.getText().toString());
        switch (view.getId()) {
            case R.id.player1_energy_increment:
                player1CurrentEnergy += 1;
                player1EnergyText.setText(player1CurrentEnergy.toString());
                break;
            case R.id.player1_energy_decrement:
                player1CurrentEnergy -= 1;
                player1EnergyText.setText(player1CurrentEnergy.toString());
                break;
            case R.id.player1_clue_increment:
                player1CurrentClue += 1;
                player1ClueText.setText(player1CurrentClue.toString());
                break;
            case R.id.player1_clue_decrement:
                player1CurrentClue -= 1;
                player1ClueText.setText(player1CurrentClue.toString());
                break;
            case R.id.player1_poison_increment:
                player1CurrentPoison += 1;
                player1PoisonText.setText(player1CurrentPoison.toString());
                break;
            case R.id.player1_poison_decrement:
                player1CurrentPoison -= 1;
                player1PoisonText.setText(player1CurrentPoison.toString());
                break;
        }
    }

    private void updatePlayer2Life(View view) {
        Integer player2CurrentLife = Integer.parseInt(player2LifeView.getText().toString());
        switch (view.getId()) {
            case R.id.player2_increment:
                player2CurrentLife += 1;
                player2LifeView.setText(player2CurrentLife.toString());
                break;
            case R.id.player2_decrement:
                player2CurrentLife -= 1;
                player2LifeView.setText(player2CurrentLife.toString());
                break;
        }
    }

    private void updatePlayer2Counter(View view) {
        Integer player2CurrentEnergy = Integer.parseInt(player2EnergyText.getText().toString());
        Integer player2CurrentClue = Integer.parseInt(player2ClueText.getText().toString());
        Integer player2CurrentPoison = Integer.parseInt(player2PoisonText.getText().toString());
        switch (view.getId()) {
            case R.id.player2_energy_increment:
                player2CurrentEnergy += 1;
                player2EnergyText.setText(player2CurrentEnergy.toString());
                break;
            case R.id.player2_energy_decrement:
                player2CurrentEnergy -= 1;
                player2EnergyText.setText(player2CurrentEnergy.toString());
                break;
            case R.id.player2_clue_increment:
                player2CurrentClue += 1;
                player2ClueText.setText(player2CurrentClue.toString());
                break;
            case R.id.player2_clue_decrement:
                player2CurrentClue -= 1;
                player2ClueText.setText(player2CurrentClue.toString());
                break;
            case R.id.player2_poison_increment:
                player2CurrentPoison += 1;
                player2PoisonText.setText(player2CurrentPoison.toString());
                break;
            case R.id.player2_poison_decrement:
                player2CurrentPoison += 1;
                player2PoisonText.setText(player2CurrentPoison.toString());
                break;
        }

    }
}
