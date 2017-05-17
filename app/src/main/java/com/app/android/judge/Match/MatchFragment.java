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

    private ImageButton player1Increment;
    private ImageButton player1Decrement;
    private ImageButton player2Increment;
    private ImageButton player2Decrement;


    private TextView player1EnergyText;
    private ImageButton player1EnergyDecrement;
    private ImageButton player1EnergyIncrement;

    private TextView player1ClueText;
    private ImageButton player1ClueDecrement;
    private ImageButton player1ClueIncrement;

    private TextView player1PoisonText;
    private ImageButton player1PoisonDecrement;
    private ImageButton player1PoisonIncrement;

    private TextView player2EnergyText;
    private ImageButton player2EnergyDecrement;
    private ImageButton player2EnergyIncrement;

    private TextView player2ClueText;
    private ImageButton player2ClueDecrement;
    private ImageButton player2ClueIncrement;

    private TextView player2PoisonText;
    private ImageButton player2PoisonDecrement;
    private ImageButton player2PoisonIncrement;

    private String player1Life;
    private String player1Color;
    private String player1Energy;
    private String player1Clue;
    private String player1Poison;

    private String player2Life;
    private String player2Color;
    private String player2Energy;
    private String player2Clue;
    private String player2Poison;

    private LinearLayout player1CountersLayout;
    private LinearLayout player2CountersLayout;

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
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
    public void onResume() {
        super.onResume();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        setPlayer1Life(sharedPreferences);
        setPlayer2Life(sharedPreferences);
        if (sharedPreferences.getBoolean("player1_counters_key", true)) {
            setPlayer1Counters(sharedPreferences);
        }
        if (sharedPreferences.getBoolean("player2_counters_key", true)) {
            setPlayer2Counters(sharedPreferences);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        savePlayerValues();

    }

    @Override
    public void onClick(View view) {
        updatePlayer1(view);
        updatePlayer2(view);
    }

    private void setPlayer1Life(SharedPreferences preferences) {
        player1Life = preferences.getString(
                getString(R.string.player1_life_key),
                getString(R.string.player1_life_default_value));
        player1Color = preferences.getString(
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

    private void setPlayer2Life(SharedPreferences preferences) {
        player2Life = preferences.getString(
                getString(R.string.player2_life_key),
                getString(R.string.player2_life_default_value));
        player2Color = preferences.getString(
                getString(R.string.player2_color_key),
                getString(R.string.player2_color_default_value));

        player1CountersLayout.setVisibility(View.VISIBLE);

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

    private void setPlayer1Counters(SharedPreferences preferences) {
        player1Energy = preferences.getString(
                getString(R.string.player1_energy_key),
                getString(R.string.player1_energy_default_value));
        player1Clue = preferences.getString(
                getString(R.string.player1_clue_key),
                getString(R.string.player1_clue_default_value));
        player1Poison = preferences.getString(
                getString(R.string.player1_poison_key),
                getString(R.string.player1_poison_default_value));

        player2CountersLayout.setVisibility(View.VISIBLE);
        player1CountersLayout.setBackgroundColor(Color.parseColor(player1Color));

        player1EnergyText = (TextView) rootView.findViewById(R.id.player1_energy_text);
        player1EnergyText.setText(player1Energy);
        player1EnergyIncrement = (ImageButton) rootView.findViewById(R.id.player1_energy_increment);
        player1EnergyIncrement.setOnClickListener(this);
        player1EnergyDecrement =  (ImageButton) rootView.findViewById(R.id.player1_energy_decrement);
        player1EnergyDecrement.setOnClickListener(this);

        player1ClueText = (TextView) rootView.findViewById(R.id.player1_clue_text);
        player1ClueText.setText(player1Clue);
        player1ClueIncrement = (ImageButton) rootView.findViewById(R.id.player1_clue_increment);
        player1ClueIncrement.setOnClickListener(this);
        player1ClueDecrement =  (ImageButton) rootView.findViewById(R.id.player1_clue_decrement);
        player1ClueDecrement.setOnClickListener(this);

        player1PoisonText = (TextView) rootView.findViewById(R.id.player1_poison_text);
        player1PoisonText.setText(player1Poison);
        player1PoisonIncrement = (ImageButton) rootView.findViewById(R.id.player1_poison_increment);
        player1PoisonIncrement.setOnClickListener(this);
        player1PoisonDecrement =  (ImageButton) rootView.findViewById(R.id.player1_poison_decrement);
        player1PoisonDecrement.setOnClickListener(this);
    }

    private void setPlayer2Counters(SharedPreferences preferences) {
        player2Energy = preferences.getString(
                getString(R.string.player2_energy_key),
                getString(R.string.player2_energy_default_value));
        player2Clue = preferences.getString(
                getString(R.string.player2_clue_key),
                getString(R.string.player2_clue_default_value));
        player2Poison = preferences.getString(
                getString(R.string.player2_poison_key),
                getString(R.string.player2_poison_default_value));

        player2CountersLayout.setBackgroundColor(Color.parseColor(player2Color));

        player2EnergyText = (TextView) rootView.findViewById(R.id.player2_energy_text);
        player2EnergyText.setText(player2Energy);
        player2EnergyIncrement = (ImageButton) rootView.findViewById(R.id.player2_energy_increment);
        player2EnergyIncrement.setOnClickListener(this);
        player2EnergyDecrement =  (ImageButton) rootView.findViewById(R.id.player2_energy_decrement);
        player2EnergyDecrement.setOnClickListener(this);

        player2ClueText = (TextView) rootView.findViewById(R.id.player2_clue_text);
        player2ClueText.setText(player2Clue);
        player2ClueIncrement = (ImageButton) rootView.findViewById(R.id.player2_clue_increment);
        player2ClueIncrement.setOnClickListener(this);
        player2ClueDecrement =  (ImageButton) rootView.findViewById(R.id.player2_clue_decrement);
        player2ClueIncrement.setOnClickListener(this);

        player2PoisonText = (TextView) rootView.findViewById(R.id.player2_poison_text);
        player2PoisonText.setText(player2Poison);
        player2PoisonIncrement = (ImageButton) rootView.findViewById(R.id.player2_poison_increment);
        player2PoisonIncrement.setOnClickListener(this);
        player2PoisonDecrement =  (ImageButton) rootView.findViewById(R.id.player2_poison_decrement);
        player2PoisonIncrement.setOnClickListener(this);
    }

    private void savePlayerValues() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //player 1
        editor.putString(getString(R.string.player1_life_key), player1LifeView.getText().toString());
        editor.putString(getString(R.string.player1_energy_key), player1EnergyText.getText().toString());
        editor.putString(getString(R.string.player1_clue_key), player1ClueText.getText().toString());
        editor.putString(getString(R.string.player1_poison_key), player1PoisonText.getText().toString());

        // player 2
        editor.putString(getString(R.string.player2_life_key), player2LifeView.getText().toString());
        editor.putString(getString(R.string.player2_energy_key), player2EnergyText.getText().toString());
        editor.putString(getString(R.string.player2_clue_key), player2ClueText.getText().toString());
        editor.putString(getString(R.string.player2_poison_key), player2PoisonText.getText().toString());
        editor.apply();

        player1CountersLayout.setVisibility(View.GONE);
        player2CountersLayout.setVisibility(View.GONE);
    }

    private void updatePlayer1(View view) {
        Integer player1CurrentLife = Integer.parseInt(player1LifeView.getText().toString());
        Integer player1CurrentEnergy = Integer.parseInt(player1EnergyText.getText().toString());
        Integer player1CurrentClue = Integer.parseInt(player1ClueText.getText().toString());
        Integer player1CurrentPoison = Integer.parseInt(player1PoisonText.getText().toString());

        switch (view.getId()) {
            case R.id.player1_increment:
                player1CurrentLife += 1;
                player1LifeView.setText(String.format(player1CurrentLife.toString()));
                break;
            case R.id.player1_decrement:
                player1CurrentLife -= 1;
                player1LifeView.setText(String.format(player1CurrentLife.toString()));
                break;
            case R.id.player1_energy_increment:
                player1CurrentEnergy += 1;
                player1EnergyText.setText(String.format(player1CurrentEnergy.toString()));
                break;
            case R.id.player1_energy_decrement:
                player1CurrentEnergy -= 1;
                player1EnergyText.setText(String.format(player1CurrentEnergy.toString()));
                break;
            case R.id.player1_clue_increment:
                player1CurrentClue += 1;
                player1ClueText.setText(String.format(player1CurrentClue.toString()));
                break;
            case R.id.player1_clue_decrement:
                player1CurrentClue -= 1;
                player1ClueText.setText(String.format(player1CurrentClue.toString()));
                break;
            case R.id.player1_poison_increment:
                player1CurrentPoison += 1;
                player1PoisonText.setText(String.format(player1CurrentPoison.toString()));
                break;
            case R.id.player1_poison_decrement:
                player1CurrentPoison -= 1;
                player1PoisonText.setText(String.format(player1CurrentPoison.toString()));
                break;
        }
    }
    private void updatePlayer2(View view) {
        Integer player2CurrentLife = Integer.parseInt(player2LifeView.getText().toString());
        Integer player2CurrentEnergy = Integer.parseInt(player2EnergyText.getText().toString());
        Integer player2CurrentClue = Integer.parseInt(player2ClueText.getText().toString());
        Integer player2CurrentPoison = Integer.parseInt(player2PoisonText.getText().toString());

        switch (view.getId()) {
            case R.id.player2_increment:
                player2CurrentLife += 1;
                player2LifeView.setText(String.format(player2CurrentLife.toString()));
                break;
            case R.id.player2_decrement:
                player2CurrentLife -= 1;
                player2LifeView.setText(String.format(player2CurrentLife.toString()));
                break;
            case R.id.player2_energy_increment:
                player2CurrentEnergy += 1;
                player2EnergyText.setText(String.format(player2CurrentEnergy.toString()));
                break;
            case R.id.player2_energy_decrement:
                player2CurrentEnergy -= 1;
                player2EnergyText.setText(String.format(player2CurrentEnergy.toString()));
                break;
            case R.id.player2_clue_increment:
                player2CurrentClue += 1;
                player2ClueText.setText(String.format(player2CurrentClue.toString()));
                break;
            case R.id.player2_clue_decrement:
                player2CurrentClue -= 1;
                player2ClueText.setText(String.format(player2CurrentClue.toString()));
                break;
            case R.id.player2_poison_increment:
                player2CurrentPoison += 1;
                player2PoisonText.setText(String.format(player2CurrentPoison.toString()));
                break;
            case R.id.player2_poison_decrement:
                player2CurrentPoison += 1;
                player2PoisonText.setText(String.format(player2CurrentPoison.toString()));
                break;
        }
    }
}