package com.example.android.judge;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.judge.Search.Card;
import com.example.android.judge.Search.RecyclerAdapter;

import java.util.ArrayList;

public class CardSearchFragment extends Fragment {

    RecyclerView cardRecyclerView;
    RecyclerAdapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    TextView emptyView;
    ProgressBar progressBar;
    private static final int CARD_LOADER_ID = 1;
    public static final String CARD_URL = "https://api.magicthegathering.io/v1/cards";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_card_search, container, false);
        cardRecyclerView = (RecyclerView) rootView.findViewById(R.id.card_search_recycler);
        recyclerAdapter = new RecyclerAdapter(this.getActivity(), new ArrayList<Card>());
        emptyView = (TextView) rootView.findViewById(R.id.empty);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        layoutManager = new LinearLayoutManager(getContext());
        cardRecyclerView.setLayoutManager(layoutManager);
        LoaderManager loaderManager = getLoaderManager();

        cardRecyclerView.setAdapter(recyclerAdapter);

        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_reset).setVisible(false);
        MenuItem item = menu.findItem(R.id.action_search);


        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), "EEEEEE", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("well", " this worked");
                return false;
            }
        });
        super.onPrepareOptionsMenu(menu);
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