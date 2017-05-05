package com.example.android.judge.Search;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaren on 5/2/2017.
 */

public class CardQuery {

    public static final String LOG_TAG = CardQuery.class.getSimpleName();

    private CardQuery() {}


    public static List<Card> fetchCardData(String requestURL, Context context, String query) {
        URL url = createUrl(requestURL + "?name=" + query);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        return extractCards(jsonResponse, context, query);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error return code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the card search JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset().forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Card> extractCards(String cardJSON, Context context, String query) {
        if (TextUtils.isEmpty(cardJSON)) {
            return null;
        }
        List<Card> cards = new ArrayList<>();

        try {
            JSONObject jsonRootObject = new JSONObject(cardJSON);
            JSONArray jsonArray = jsonRootObject.optJSONArray("cards");
            cards.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject currentCard = jsonArray.getJSONObject(i);
                String cardName = currentCard.optString("name");
                String cardManaCost = currentCard.optString("manaCost");
                String cardType = currentCard.optString("type");
                String cardRarity = currentCard.optString("rarity");
                String cardImage = currentCard.optString("imageUrl");
                String cardText = currentCard.optString("text");
                String cardcolors = currentCard.optString("colors");

                cards.add(new Card(cardName, cardManaCost, cardType, cardRarity, cardImage, cardText, cardcolors));


            }
        } catch (JSONException e) {
            Log.e("CardQuery", "Problem parsing the card json results", e);
        }
        return cards;
    }
}
