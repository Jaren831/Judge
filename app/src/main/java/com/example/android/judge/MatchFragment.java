package com.example.android.judge;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.judge.Match.Player1Fragment;
import com.example.android.judge.Match.Player2Fragment;

public class MatchFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_match, container, false);
        Fragment player1Fragment = new Player1Fragment();
        Fragment player2Fragment = new Player2Fragment();

        getFragmentManager().beginTransaction()
                .add(R.id.player1_container, player1Fragment)
                .add(R.id.player2_container, player2Fragment)
                .commit();

        // Inflate the layout for this fragment
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





}