package com.example.android.judge;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
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
import com.example.android.judge.Search.CardRecyclerAdapter;
import com.example.android.judge.Search.CardSearchLoader;

import java.util.ArrayList;
import java.util.List;

public class CardSearchFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Card>>{

    RecyclerView cardRecyclerView;
    CardRecyclerAdapter cardRecyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    TextView emptyView;
    ProgressBar progressBar;
    LoaderManager loaderManager = getLoaderManager();
    private static final int CARD_LOADER_ID = 1;
    public static final String CARD_URL = "https://api.magicthegathering.io/v1/cards";
    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            settingsReload();
        }
    };

    ArrayList<Card> cardList = new ArrayList<Card>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_card_search, container, false);
        cardRecyclerView = (RecyclerView) rootView.findViewById(R.id.card_search_recycler);
        cardRecyclerAdapter = new CardRecyclerAdapter(this.getActivity(), cardList);
        emptyView = (TextView) rootView.findViewById(R.id.empty);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        layoutManager = new LinearLayoutManager(getContext());
        cardRecyclerView.setLayoutManager(layoutManager);
        cardRecyclerView.setAdapter(cardRecyclerAdapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        preferences.registerOnSharedPreferenceChangeListener(listener);


        setHasOptionsMenu(true);
        while (cardRecyclerView == null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        getActivity();
        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            progressBar.setVisibility(View.GONE);
            emptyView.setText(com.example.android.judge.R.string.noInternet);
        }

        loaderManager.initLoader(CARD_LOADER_ID, null, CardSearchFragment.this).forceLoad();


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
                loaderManager.initLoader(CARD_LOADER_ID, null, CardSearchFragment.this).forceLoad();
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

    @Override
    public Loader<List<Card>> onCreateLoader(int id, Bundle bundle) {
        Uri baseUri = Uri.parse(CARD_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        return new CardSearchLoader(this.getContext(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Card>> loader, List<Card> cards) {
        emptyView.setText(com.example.android.judge.R.string.empty);
        progressBar.setVisibility(View.GONE);
        cardList.addAll(cards);

        Toast.makeText(getActivity(), cardList.size(), Toast.LENGTH_LONG).show();


        cardRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Card>> loader) {
        cardRecyclerAdapter.notifyDataSetChanged();
    }

    public void settingsReload() {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(CARD_LOADER_ID, null, this);
        progressBar.setVisibility(View.VISIBLE);
        Toast updateToast = Toast.makeText(getActivity(), "Settings Saved", Toast.LENGTH_SHORT);
        updateToast.show();
    }
}