package com.app.android.judge.Search;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.android.judge.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by jaren on 4/30/2017.
 */

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.CardViewHolder>{
    private SpannableString replaceMana;
    private Card currentCard;
    private final Context mContext;
    private final ArrayList<Card> mCardArray;



    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final CardView cardCard;
        final TextView cardName;
        final TextView cardMana;
        final ImageView cardImage;

        CardViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cardCard = (CardView) itemView.findViewById(R.id.card_card);
            cardName = (TextView) itemView.findViewById(R.id.card_name_item);
            cardMana = (TextView) itemView.findViewById(R.id.card_mana_item);
            cardImage = (ImageView) itemView.findViewById(R.id.card_image_item);
        }

        @Override
        public void onClick(View view) {

            Fragment detailFragment = new SearchDetailFragment();
            Bundle args = new Bundle();
            args.putString("url", currentCard.getImage());
            detailFragment.setArguments(args);
            FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, detailFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }

    public CardRecyclerAdapter(Context context, ArrayList<Card> cardArray) {
        mContext = context;
        mCardArray = cardArray;
    }

    @Override
    public int getItemCount() {
        return mCardArray.size();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        currentCard = mCardArray.get(position);

        holder.cardName.setText(currentCard.getName());

        if (currentCard.getImage() != null && !currentCard.getImage().isEmpty()) {
            Picasso.with(mContext).load(currentCard.getImage()).fit().into(holder.cardImage);
        }

        holder.cardMana.setText("");
        String[] separateMana = currentCard.getMana().split("");
        for (String aSeparateMana : separateMana) {
            if (!Objects.equals(aSeparateMana, "{") && !Objects.equals(aSeparateMana, "}")) {
                switch (aSeparateMana) {
                    case "U":
                        aSeparateMana = "B";
                        replaceMana = new SpannableString(aSeparateMana);
                        replaceMana.setSpan(new ForegroundColorSpan(Color.BLUE), 0, replaceMana.length(), 0);
                        holder.cardMana.append(replaceMana);
                        break;
                    case "R":
                        replaceMana = new SpannableString(aSeparateMana);
                        replaceMana.setSpan(new ForegroundColorSpan(Color.RED), 0, replaceMana.length(), 0);
                        holder.cardMana.append(replaceMana);
                        break;
                    case "B":
                        replaceMana = new SpannableString(aSeparateMana);
                        replaceMana.setSpan(new ForegroundColorSpan(Color.BLACK), 0, replaceMana.length(), 0);
                        holder.cardMana.append(replaceMana);
                        break;
                    case "G":
                        replaceMana = new SpannableString(aSeparateMana);
                        replaceMana.setSpan(new ForegroundColorSpan(Color.GREEN), 0, replaceMana.length(), 0);
                        holder.cardMana.append(replaceMana);
                        break;
                    case "W":
                        replaceMana = new SpannableString(aSeparateMana);
                        replaceMana.setSpan(new ForegroundColorSpan(Color.WHITE), 0, replaceMana.length(), 0);
                        holder.cardMana.append(replaceMana);
                        break;
                    default:
                        replaceMana = new SpannableString(aSeparateMana);
                        holder.cardMana.append(replaceMana);
                        break;
                }
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
