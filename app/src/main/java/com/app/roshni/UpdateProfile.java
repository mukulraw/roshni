package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

public class UpdateProfile extends AppCompatActivity {

    TabLayout tabs;
    CustomViewPager pager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        tabs = findViewById(R.id.tabLayout2);
        pager = findViewById(R.id.pager);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("UPDATE YOUR DETAILS");



        tabs.addTab(tabs.newTab().setText("PERSONAL"));
        tabs.addTab(tabs.newTab().setText("PROFESSIONAL"));


        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

        pager.setPagingEnabled(true);

        tabs.getTabAt(0).setText("PERSONAL");
        tabs.getTabAt(1).setText("PROFESSIONAL");

    }

    class PagerAdapter extends FragmentStatePagerAdapter
    {



        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
            {
                personal frag = new personal();
                frag.setData(pager);
                return frag;
            }
            else
            {
                return new professional();
            }

        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
