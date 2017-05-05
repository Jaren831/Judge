package com.example.android.judge.Search;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.judge.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.security.auth.Subject;

import static android.text.TextUtils.join;

/**
 * Created by jaren on 4/30/2017.
 */

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.CardViewHolder>{
    SpannableString finalString;
    SpannableString replaceMana;


    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardCard;
        TextView cardName;
        TextView cardMana;
        ImageView cardImage;


        CardViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cardCard = (CardView) itemView.findViewById(R.id.card_card);
            cardName = (TextView) itemView.findViewById(R.id.card_name_item);
            cardMana = (TextView) itemView.findViewById(R.id.card_mana_item);
            cardImage = (ImageView) itemView.findViewById(R.id.card_image_item);
        }

        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }

    private Context mContext;
    private ArrayList<Card> mCardArray;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Card currentCard = mCardArray.get(position);

        holder.cardName.setText(currentCard.getName());

        if (currentCard.getImage() != null && !currentCard.getImage().isEmpty()) {
            Picasso.with(mContext).load(currentCard.getImage()).fit().into(holder.cardImage);
        }

//        switch (currentCard.getIdentity()) {
//            case "W":
//                holder.cardName.setBackgroundColor(Color.parseColor("#fffada"));
//                holder.cardMana.setBackgroundColor(Color.parseColor("#faaa8f"));
//                break;
//            case "U":
//                holder.cardName.setBackgroundResource(R.color.mtg_blue);
//                holder.cardMana.setBackgroundColor(Color.parseColor("#a9e0f9"));
//                break;
//            case "B":
//                holder.cardName.setBackgroundColor(Color.parseColor("#9bd3ae"));
//                holder.cardMana.setBackgroundColor(Color.parseColor("#9bd3ae"));
//                break;
//            case "R":
//                holder.cardName.setBackgroundColor(Color.parseColor("#fffada"));
//                holder.cardMana.setBackgroundColor(Color.parseColor("#fffada"));
//                break;
//            case "G":
//                holder.cardName.setBackgroundColor(Color.GREEN);
//                holder.cardMana.setBackgroundColor(Color.parseColor("#cbc2bf"));
//                break;
//            default:
//                break;
//        }

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


    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}
