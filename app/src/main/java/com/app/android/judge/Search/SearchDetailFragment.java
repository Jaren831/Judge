package com.app.android.judge.Search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.android.judge.MainActivity;
import com.app.android.judge.R;
import com.squareup.picasso.Picasso;

/**
 * Created by jaren on 4/30/2017.
 */

public class SearchDetailFragment extends Fragment {


    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.detail_title));
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_card_detail, container, false);
        ImageView cardDetailImage = (ImageView) rootView.findViewById(R.id.card_detail_image);

        Bundle args = getArguments();
        String cardUrl = args.getString("url");
        if (cardUrl != null && !cardUrl.isEmpty()) {
            Picasso.with(getContext()).load(cardUrl).fit().centerCrop().into(cardDetailImage);
        } else {
            cardDetailImage.setImageResource(R.drawable.ic_help_outline_black_24dp);
            Toast.makeText(getContext(), getResources().getString(R.string.image_not_found), Toast.LENGTH_SHORT).show();

        }
        return rootView;
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_reset).setVisible(false);
        menu.findItem(R.id.action_settings).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

}
