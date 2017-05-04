package com.example.android.judge.Search;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import java.util.List;

/**
 * Created by jaren on 4/30/2017.
 */

public class CardSearchLoader extends AsyncTaskLoader<List<Card>> {
    private String mUrl;
    private Context mContext;

    public CardSearchLoader(Context context, String url) {
        super(context);
        mUrl = url;
        mContext = context;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Card> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<Card> cards = CardQuery.fetchCardData(mUrl, mContext);
        return cards;

    }
}
