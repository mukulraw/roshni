package com.app.roshni;

import androidx.annotation.NonNull;
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
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity2 extends AppCompatActivity {

    DrawerLayout drawer;
    ImageView toggle , notification;
    AHBottomNavigation bottom;

    TextView logout;

    CircleImageView image;
    TextView edit;

    TextView about , faq , policies , terms , support;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String languageToLoad  = SharePreferenceUtils.getInstance().getString("lang"); // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        drawer = findViewById(R.id.drawer);
        toggle = findViewById(R.id.imageButton);
        notification = findViewById(R.id.imageButton2);
        bottom = findViewById(R.id.bottomNavigationView);
        logout = findViewById(R.id.textView25);
        image = findViewById(R.id.imageView1);
        edit = findViewById(R.id.textView56);

        about = findViewById(R.id.textView59);
        faq = findViewById(R.id.textView60);
        policies = findViewById(R.id.textView61);
        terms = findViewById(R.id.textView62);
        support = findViewById(R.id.textView24);

        bottom.setBehaviorTranslationEnabled(false);
        bottom.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottom.setAccentColor(getResources().getColor(R.color.colorPrimary));

        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.navigation2);
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

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity2.this , Notifications.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(MainActivity2.this);
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

                        dialog.dismiss();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    FirebaseInstanceId.getInstance().deleteInstanceId();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                        SharePreferenceUtils.getInstance().deletePref();

                        Intent intent = new Intent(MainActivity2.this , Splash.class);
                        startActivity(intent);
                        finishAffinity();

                    }
                });





            }
        });


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity2.this , Web.class);
                intent.putExtra("title" ,  getString(R.string.about_us));
                intent.putExtra("url" , "https://mrtecks.com/roshni/about.php");
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity2.this , Web.class);
                intent.putExtra("title" , getString(R.string.faqs));
                intent.putExtra("url" , "https://mrtecks.com/roshni/faq.php");
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });


        policies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity2.this , Web.class);
                intent.putExtra("title" , getString(R.string.policies));
                intent.putExtra("url" , "https://mrtecks.com/roshni/policies.php");
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity2.this , Web.class);
                intent.putExtra("title" , getString(R.string.terms_amp_conditions));
                intent.putExtra("url" , "https://mrtecks.com/roshni/terms.php");
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity2.this , Web.class);
                intent.putExtra("title" , getString(R.string.support_help));
                intent.putExtra("url" , "https://mrtecks.com/roshni/support.php");
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

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
                        contractors test2 = new contractors();
                        ft2.replace(R.id.replace, test2);
                        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        //ft.addToBackStack(null);
                        ft2.commit();

                        return true;
                    case 2:
                        FragmentManager fm3 = getSupportFragmentManager();
                        FragmentTransaction ft3 = fm3.beginTransaction();
                        jobsbrand test3 = new jobsbrand();
                        ft3.replace(R.id.replace, test3);
                        ft3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        //ft.addToBackStack(null);
                        ft3.commit();

                        return true;
                    case 3:
                        FragmentManager fm4 = getSupportFragmentManager();
                        FragmentTransaction ft4 = fm4.beginTransaction();
                        profile2 test4 = new profile2();
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
        jobsbrand test = new jobsbrand();
        ft.replace(R.id.replace, test);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        //ft.addToBackStack(null);
        ft.commit();

        bottom.setCurrentItem(2);

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
        loader.displayImage(SharePreferenceUtils.getInstance().getString("logo") , image , options);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(singleReceiver);

    }

}
