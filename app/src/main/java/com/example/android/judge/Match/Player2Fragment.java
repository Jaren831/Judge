package com.example.android.judge.Match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.judge.R;

public class Player2Fragment extends Fragment {
    private ViewPager viewPager2;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter2;
    private static final int NUM_PAGES = 2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_player2, container, false);
        viewPager2 = (ViewPager) rootView.findViewById(R.id.player2_view_pager);
        fragmentStatePagerAdapter2 = new PlayerPagerAdapter2(getChildFragmentManager());
        viewPager2.setAdapter(fragmentStatePagerAdapter2);
        return rootView;

    }

    public static class PlayerPagerAdapter2 extends FragmentStatePagerAdapter {
        private static int NUM_ITEMS = 2;

        public PlayerPagerAdapter2(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Player2LifeFragment.newInstance();
                case 1:
                    return Player2InfoFragment.newInstance();
                default:
                    return Player2LifeFragment.newInstance();
            }
        }
    }
}
