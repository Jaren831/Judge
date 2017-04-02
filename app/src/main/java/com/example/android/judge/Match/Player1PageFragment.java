package com.example.android.judge.Match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.judge.R;

public class Player1PageFragment extends Fragment {
    public static final String ARG_PAGE = "page";
    private int mPageNumber;

    public static Player1PageFragment create(int pageNumber) {
        Player1PageFragment fragment = new Player1PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Player1PageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_player1_page, container, false);
        return rootView;
    }

    public int getPageNumber() {
        return mPageNumber;
    }
}