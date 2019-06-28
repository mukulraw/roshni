package com.app.roshni;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class First extends Fragment {


    TextView next;

    ViewPager pager;

    public void setData(ViewPager pager) {
        this.pager = pager;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first, container, false);

        next = view.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pager.setCurrentItem(1);


                /*FragmentManager fm = getChildFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                First f = new First();
                ft.replace(R.id.replace, f);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

                ft.commit();
*/

            }
        });
        return view;
    }
}
