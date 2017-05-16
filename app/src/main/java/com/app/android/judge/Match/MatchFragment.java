package com.app.android.judge.Match;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.android.judge.R;
import com.bumptech.glide.load.engine.Resource;

import java.util.HashSet;
import java.util.Set;

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

    private String player2Life;
    private String player2Color;

    private LinearLayout player1CountersLayout;
    private LinearLayout player2CountersLayout;

    private LinearLayout player1EnergyView;
    private LinearLayout player1ClueView;
    private LinearLayout player1PoisonView;

    private LinearLayout player2EnergyView;
    private LinearLayout player2ClueView;
    private LinearLayout player2PoisonView;



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
        setPlayer1Life(sharedPreferences);
        setPlayer2Life(sharedPreferences);
        setPlayer1Counters(sharedPreferences);
        setPlayer2Counters(sharedPreferences);
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
        Set<String> player1Counters = preferences.getStringSet("player1_counters", null);
        String[] player1SelectedCounters = player1Counters.toArray(new String[] {});

        player1CountersLayout = (LinearLayout) rootView.findViewById(R.id.player1_counters_view);
        player1EnergyView = (LinearLayout) rootView.findViewById(R.id.player1_energy_view);
        player1ClueView = (LinearLayout) rootView.findViewById(R.id.player1_clue_view);
        player1PoisonView = (LinearLayout) rootView.findViewById(R.id.player1_poison_view);

        player1EnergyText = (TextView) rootView.findViewById(R.id.player1_energy_text);
        player1EnergyIncrement = (ImageButton) rootView.findViewById(R.id.player1_energy_increment);
        player1EnergyIncrement.setOnClickListener(this);
        player1EnergyDecrement =  (ImageButton) rootView.findViewById(R.id.player1_energy_decrement);
        player1EnergyDecrement.setOnClickListener(this);
        player1EnergyView.setBackgroundColor(Color.parseColor(player1Color));


        player1ClueText = (TextView) rootView.findViewById(R.id.player1_clue_text);
        player1ClueIncrement = (ImageButton) rootView.findViewById(R.id.player1_clue_increment);
        player1ClueIncrement.setOnClickListener(this);
        player1ClueDecrement =  (ImageButton) rootView.findViewById(R.id.player1_clue_decrement);
        player1ClueDecrement.setOnClickListener(this);
        player1ClueView.setBackgroundColor(Color.parseColor(player1Color));

        player1PoisonText = (TextView) rootView.findViewById(R.id.player1_poison_text);
        player1PoisonIncrement = (ImageButton) rootView.findViewById(R.id.player1_poison_increment);
        player1PoisonIncrement.setOnClickListener(this);
        player1PoisonDecrement =  (ImageButton) rootView.findViewById(R.id.player1_poison_decrement);
        player1PoisonDecrement.setOnClickListener(this);
        player1PoisonView.setBackgroundColor(Color.parseColor(player1Color));

        if (player1SelectedCounters != null && player1SelectedCounters.length > 0) {
            for (String aSelected : player1SelectedCounters) {
                if (aSelected != null) {
                    switch (aSelected) {
                        case "0":
                            player1EnergyView.setVisibility(View.VISIBLE);
                            break;
                        case "1":
                            player1ClueView.setVisibility(View.VISIBLE);
                            break;
                        case "2":
                            player1PoisonView.setVisibility(View.VISIBLE);
                    }
                }
            }
        } else {
            player1CountersLayout.setVisibility(View.GONE);
        }
    }

    private void setPlayer2Counters(SharedPreferences preferences) {
        Set<String> player2Counters = preferences.getStringSet("player2_counters", null);
        String[] player2SelectedCounter = player2Counters.toArray(new String[] {});

        player2CountersLayout = (LinearLayout) rootView.findViewById(R.id.player2_counters_view);
        player2EnergyView = (LinearLayout) rootView.findViewById(R.id.player2_energy_view);
        player2ClueView = (LinearLayout) rootView.findViewById(R.id.player2_clue_view);
        player2PoisonView = (LinearLayout) rootView.findViewById(R.id.player2_poison_view);

        player2EnergyText = (TextView) rootView.findViewById(R.id.player2_energy_text);
        player2EnergyIncrement = (ImageButton) rootView.findViewById(R.id.player2_energy_increment);
        player2EnergyIncrement.setOnClickListener(this);
        player2EnergyDecrement =  (ImageButton) rootView.findViewById(R.id.player2_energy_decrement);
        player2EnergyDecrement.setOnClickListener(this);
        player2EnergyView.setBackgroundColor(Color.parseColor(player2Color));

        player2ClueText = (TextView) rootView.findViewById(R.id.player2_clue_text);
        player2ClueIncrement = (ImageButton) rootView.findViewById(R.id.player2_clue_increment);
        player2ClueIncrement.setOnClickListener(this);
        player2ClueDecrement =  (ImageButton) rootView.findViewById(R.id.player2_clue_decrement);
        player2ClueIncrement.setOnClickListener(this);
        player2ClueView.setBackgroundColor(Color.parseColor(player2Color));

        player2PoisonText = (TextView) rootView.findViewById(R.id.player2_poison_text);
        player2PoisonIncrement = (ImageButton) rootView.findViewById(R.id.player2_poison_increment);
        player2PoisonIncrement.setOnClickListener(this);
        player2PoisonDecrement =  (ImageButton) rootView.findViewById(R.id.player2_poison_decrement);
        player2PoisonIncrement.setOnClickListener(this);
        player2PoisonView.setBackgroundColor(Color.parseColor(player2Color));

        if (player2SelectedCounter != null && player2SelectedCounter.length > 0) {
            for (String aSelected : player2SelectedCounter) {
                switch (aSelected) {
                    case "0":
                        player2EnergyView.setVisibility(View.VISIBLE);
                        break;
                    case "1":
                        player2ClueView.setVisibility(View.VISIBLE);
                        break;
                    case "2":
                        player2PoisonView.setVisibility(View.VISIBLE);
                }
            }
        } else {
            player2CountersLayout.setVisibility(View.GONE);
        }
    }

    private void savePlayerValues() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> player1CounterSet = new HashSet<>();
        Set<String> player2CounterSet = new HashSet<>();

        editor.putString(getString(R.string.player1_life_key), player1LifeView.getText().toString());
        player1CounterSet.add(player1EnergyText.getText().toString());
        player1CounterSet.add(player1ClueText.getText().toString());
        player1CounterSet.add(player1PoisonText.getText().toString());
        editor.putStringSet(getString(R.string.player1_counters_key), player1CounterSet);

        editor.putString(getString(R.string.player2_life_key), player2LifeView.getText().toString());
        player1CounterSet.add(player2EnergyText.getText().toString());
        player1CounterSet.add(player2ClueText.getText().toString());
        player1CounterSet.add(player2PoisonText.getText().toString());
        editor.putStringSet(getString(R.string.player2_counters_key), player2CounterSet);
        editor.apply();
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