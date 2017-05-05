package com.example.android.judge.Search;

/**
 * Created by jaren on 4/30/2017.
 */

public class Card {
    public String mName;
    public String mMana;
    public String mType;
    public String mRarity;
    public String mImage;
    public String mText;
    public String mColors;
    public String mColorIdentity;

    public Card(String name, String mana, String type, String rarity, String image, String text, String colors, String colorIdentity ) {
        mName = name;
        mMana = mana;
        mType = type;
        mRarity = rarity;
        mImage = image;
        mText = text;
        mColors = colors;
        mColorIdentity = colorIdentity;
    }

    public String getName() {
        return mName;
    }

    public String getMana() {
        return mMana;
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

    public String getColors() {
        return mColors;
    }

    public String getIdentity() {
        return mColorIdentity;
    }
}


