package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.ViewPropertyAnimator;

import me.relex.circleindicator.CircleIndicator;

public class Sliders extends AppCompatActivity {

    ViewPager pager;

    CircleIndicator indicator;

    SliderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliders);
        pager = findViewById(R.id.pager);



        //pager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % ListUtils.getSize(imageIdList));


        indicator = findViewById(R.id.indicator);

        adapter = new SliderAdapter(getSupportFragmentManager(), 3);

        pager.setAdapter(adapter);

        indicator.setViewPager(pager);
    }



    @Override
    public void onPause() {
        super.onPause();
        // stop auto scroll when onPause

    }

    @Override
    public void onResume() {
        super.onResume();
        // start auto scroll when onResume

    }



    public class SliderAdapter extends FragmentStatePagerAdapter {


        public SliderAdapter(FragmentManager fm , int list) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {


            if (i == 0){

                First frag = new First();
                frag.setData(pager);
                return frag;

            }else if (i == 1){
                return new Two();

            }else if (i == 2){
                return new  Three();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
