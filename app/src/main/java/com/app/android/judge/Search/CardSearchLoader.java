package com.app.android.judge.Search;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import java.util.List;

/**
 * Created by jaren on 4/30/2017.
 */

class CardSearchLoader extends AsyncTaskLoader<List<Card>> {
    private final String mUrl;
    private final Context mContext;
    private final String mQuery;

    public CardSearchLoader(Context context, String url, String query) {
        super(context);
        mUrl = url;
        mContext = context;
        mQuery = query;
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
        return CardQuery.fetchCardData(mUrl, mQuery);

    }
}
