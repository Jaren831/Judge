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

public class Player1Fragment extends Fragment {
    private FragmentStatePagerAdapter fragmentStatePagerAdapter1;
    private ViewPager viewPager1;
    private static final int NUM_PAGES = 2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_player1, container, false);
        viewPager1 = (ViewPager) rootView.findViewById(R.id.player1_view_pager);
        fragmentStatePagerAdapter1 = new PlayerPagerAdapter1(getChildFragmentManager());
        viewPager1.setAdapter(fragmentStatePagerAdapter1);

        return rootView;

    }

    public static class PlayerPagerAdapter1 extends FragmentStatePagerAdapter {
        private static int NUM_ITEMS = 2;

        public PlayerPagerAdapter1(FragmentManager fragmentManager) {
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
                    return Player1LifeFragment.newInstance();
                case 1:
                    return Player1InfoFragment.newInstance();
                default:
                    return Player1LifeFragment.newInstance();
            }
        }
    }
}