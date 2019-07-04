package com.app.roshni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    Timer timer;
    String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (hasPermissions(this, PERMISSIONS)) {

            startApp();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS);
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {

            Log.d("permmm", "1");

            if (hasPermissions(this, PERMISSIONS)) {

                Log.d("permmm", "2");

                startApp();

            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                ) {

                    Log.d("permmm", "3");

                    Toast.makeText(getApplicationContext(), "Permissions are required for this app", Toast.LENGTH_SHORT).show();
                    finish();

                } else {

                    Log.d("permmm", "4");
                    Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                            .show();
                    finish();
                    //                            //proceed with logic by disabling the related features or quit the app.
                }
            }
        }

    }

    void startApp() {

        String id = SharePreferenceUtils.getInstance().getString("user_id");
        String name = SharePreferenceUtils.getInstance().getString("name");

        Log.d("iidd" , id);

        if (id.length() > 0 && name.length() > 0)
        {

            String type = SharePreferenceUtils.getInstance().getString("type");

            if (type.equals("brand"))
            {
                Intent intent = new Intent(Splash.this , MainActivity2.class);
                startActivity(intent);
                finishAffinity();
            }
            else if (type.equals("worker"))
            {
                Intent intent = new Intent(Splash.this , MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
            else
            {
                Intent intent = new Intent(Splash.this , MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }


        }
        else
        {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {


                    Intent i = new Intent(Splash.this , Sliders.class);
                    startActivity(i);
                    finish();

                }
            } , 1500);
        }



    }

}
