package com.app.roshni;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class jobsWorkers extends Fragment {

    TabLayout tabs;
    CustomViewPager pager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jobs_wworker_layout , container , false);

        tabs = view.findViewById(R.id.tabLayout2);
        pager = view.findViewById(R.id.pager);


        tabs.addTab(tabs.newTab().setText(getString(R.string.act)));
        tabs.addTab(tabs.newTab().setText(getString(R.string.inact)));


        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

        pager.setPagingEnabled(true);


        tabs.getTabAt(0).setText(getString(R.string.act));
        tabs.getTabAt(1).setText(getString(R.string.inact));



        return view;
    }

    class PagerAdapter extends FragmentStatePagerAdapter
    {


        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
            {
                return new workerActive();
            }
            else
            {
                return new workerInActive();
            }

        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
