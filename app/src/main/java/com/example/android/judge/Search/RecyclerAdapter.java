package com.example.android.judge.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.judge.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jaren on 4/30/2017.
 */

public class RecyclerAdapter implements RecyclerView.Adapter<RecyclerAdapter.CardViewHolder>{
    ArrayList<Card> cards;
    View 


    public RecyclerAdapter(Context context, ArrayList<Card> cards) {
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cardItemView = convertView;
        if (cardItemView == null) {
            cardItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.recycler_item, parent, false);
        }
        Card currentCard = getItem(position);

        ImageView cardImage = (ImageView) cardItemView.findViewById(R.id.card_image_item);
        if (currentCard.getImage() != null) {
            Picasso.with(getContext())
                    .load(currentCard.getImage())
                    .into(cardImage);        }
        else {
            cardImage.setImageResource(com.example.android.judge.R.drawable.ic_help_outline_black_24dp);
        }

        TextView cardName = (TextView) cardItemView.findViewById(R.id.card_name_item);
        cardName.setText(currentCard.getName());

        TextView cardMana = (TextView) cardItemView.findViewById(R.id.card_mana_item);
        cardMana.setText(currentCard.getMana());

        return cardItemView;
    }
}
