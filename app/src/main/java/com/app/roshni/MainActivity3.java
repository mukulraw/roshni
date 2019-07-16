package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity3 extends AppCompatActivity {
    DrawerLayout drawer;
    ImageView toggle;
    AHBottomNavigation bottom;

    TextView logout;

    CircleImageView image;
    TextView edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        drawer = findViewById(R.id.drawer);
        toggle = findViewById(R.id.imageButton);
        bottom = findViewById(R.id.bottomNavigationView);
        logout = findViewById(R.id.textView25);
        image = findViewById(R.id.imageView1);
        edit = findViewById(R.id.textView56);

        bottom.setBehaviorTranslationEnabled(false);
        bottom.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottom.setAccentColor(getResources().getColor(R.color.colorPrimary));

        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.navigation3);
        navigationAdapter.setupWithBottomNavigation(bottom);


        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer();
            }
        });

        /*edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this , UpdateProfile.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });*/

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(MainActivity3.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.quit_dialog_layout);
                dialog.show();

                Button ookk = dialog.findViewById(R.id.button2);
                Button canc = dialog.findViewById(R.id.button4);

                canc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                ookk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharePreferenceUtils.getInstance().deletePref();

                        Intent intent = new Intent(MainActivity3.this , SignupLogin.class);
                        startActivity(intent);
                        finishAffinity();

                    }
                });





            }
        });

        bottom.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {


                switch (position) {
                    case 0:
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        workers test = new workers();
                        ft.replace(R.id.replace, test);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        //ft.addToBackStack(null);
                        ft.commit();

                        return true;
                    case 1:
                        FragmentManager fm2 = getSupportFragmentManager();
                        FragmentTransaction ft2 = fm2.beginTransaction();
                        newjobs test2 = new newjobs();
                        ft2.replace(R.id.replace, test2);
                        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        //ft.addToBackStack(null);
                        ft2.commit();

                        return true;
                    case 2:
                        FragmentManager fm3 = getSupportFragmentManager();
                        FragmentTransaction ft3 = fm3.beginTransaction();
                        appliedjobs test3 = new appliedjobs();
                        ft3.replace(R.id.replace, test3);
                        ft3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        //ft.addToBackStack(null);
                        ft3.commit();

                        return true;
                    case 3:
                        FragmentManager fm4 = getSupportFragmentManager();
                        FragmentTransaction ft4 = fm4.beginTransaction();
                        profile3 test4 = new profile3();
                        ft4.replace(R.id.replace, test4);
                        ft4.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        //ft.addToBackStack(null);
                        ft4.commit();

                        return true;
                }


                return true;
            }
        });


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        newjobs test = new newjobs();
        ft.replace(R.id.replace, test);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        //ft.addToBackStack(null);
        ft.commit();

        bottom.setCurrentItem(0);

        singleReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (Objects.equals(intent.getAction(), "photo")) {
                    Log.d("local" , "called");
                    onResume();
                }

            }
        };


        LocalBroadcastManager.getInstance(this).registerReceiver(singleReceiver,
                new IntentFilter("photo"));

    }

    void toggleDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    BroadcastReceiver singleReceiver;

    @Override
    protected void onResume() {
        super.onResume();

        edit.setText(SharePreferenceUtils.getInstance().getString("name"));

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();

        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(SharePreferenceUtils.getInstance().getString("photo") , image , options);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(singleReceiver);

    }


}
