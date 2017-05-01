package com.example.android.judge.Search;

/**
 * Created by jaren on 4/30/2017.
 */

public class Card {
    public String mName;
    public String mColors;
    public String mType;
    public String mRarity;
    public String mImage;
    public String mText;

    public Card(String name, String color, String type, String rarity, String image, String text ) {
        mName = name;
        mColors = color;
        mType = type;
        mRarity = rarity;
        mImage = image;
        mType = text;
    }

    public String getName() {
        return mName;
    }

    public String getColors() {
        return mColors;
    }

    public String getType() {
        return mType;
    }

    public String getRarity() {
        return mRarity;
    }

    public String getImage() {
        return mImage;
    }

    public String getText() {
        return mText;
    }
}


