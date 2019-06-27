package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.roshni.verifyPOJO.Data;
import com.app.roshni.verifyPOJO.verifyBean;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class OTP2 extends AppCompatActivity {

    OtpView otp;
    Button submit;
    TextView resend;
    ProgressBar progress;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp2);

        phone = getIntent().getStringExtra("phone");

        otp = findViewById(R.id.textView5);
        submit = findViewById(R.id.button3);
        resend = findViewById(R.id.textView6);
        progress = findViewById(R.id.progressBar2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String ot = otp.getText().toString();

                Log.d("otp" , ot);

                if (ot.length() > 0)
                {

                    progress.setVisibility(View.VISIBLE);

                    Bean b = (Bean) getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseurl)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


                    Call<verifyBean> call = cr.verify(phone , ot);
                    call.enqueue(new Callback<verifyBean>() {
                        @Override
                        public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {


                            if (response.body().getStatus().equals("1"))
                            {


                                Data item = response.body().getData();

                                SharePreferenceUtils.getInstance().saveString("id" , item.getId());
                                SharePreferenceUtils.getInstance().saveString("type" , item.getType());
                                SharePreferenceUtils.getInstance().saveString("user_id" , item.getUserId());
                                SharePreferenceUtils.getInstance().saveString("name" , item.getName());
                                SharePreferenceUtils.getInstance().saveString("photo" , item.getPhoto());
                                SharePreferenceUtils.getInstance().saveString("dob" , item.getDob());
                                SharePreferenceUtils.getInstance().saveString("gender" , item.getGender());
                                SharePreferenceUtils.getInstance().saveString("phone" , item.getPhone());
                                SharePreferenceUtils.getInstance().saveString("cpin" , item.getCpin());
                                SharePreferenceUtils.getInstance().saveString("cstate" , item.getCstate());
                                SharePreferenceUtils.getInstance().saveString("cdistrict" , item.getCdistrict());
                                SharePreferenceUtils.getInstance().saveString("carea" , item.getCarea());
                                SharePreferenceUtils.getInstance().saveString("cstreet" , item.getCstreet());
                                SharePreferenceUtils.getInstance().saveString("ppin" , item.getPpin());
                                SharePreferenceUtils.getInstance().saveString("pstate" , item.getPstate());
                                SharePreferenceUtils.getInstance().saveString("pdistrict" , item.getPdistrict());
                                SharePreferenceUtils.getInstance().saveString("parea" , item.getParea());
                                SharePreferenceUtils.getInstance().saveString("pstreet" , item.getPstreet());
                                SharePreferenceUtils.getInstance().saveString("category" , item.getCategory());
                                SharePreferenceUtils.getInstance().saveString("religion" , item.getReligion());
                                SharePreferenceUtils.getInstance().saveString("educational" , item.getEducational());
                                SharePreferenceUtils.getInstance().saveString("marital" , item.getMarital());
                                SharePreferenceUtils.getInstance().saveString("children" , item.getChildren());
                                SharePreferenceUtils.getInstance().saveString("belowsix" , item.getBelowsix());
                                SharePreferenceUtils.getInstance().saveString("sixtofourteen" , item.getSixtofourteen());
                                SharePreferenceUtils.getInstance().saveString("fifteentoeighteen" , item.getFifteentoeighteen());
                                SharePreferenceUtils.getInstance().saveString("goingtoschool" , item.getGoingtoschool());
                                SharePreferenceUtils.getInstance().saveString("sector" , item.getSector());
                                SharePreferenceUtils.getInstance().saveString("skills" , item.getSkills());
                                SharePreferenceUtils.getInstance().saveString("experience" , item.getExperience());
                                SharePreferenceUtils.getInstance().saveString("employment" , item.getEmployment());
                                SharePreferenceUtils.getInstance().saveString("employer" , item.getEmployer());
                                SharePreferenceUtils.getInstance().saveString("home" , item.getHome());
                                SharePreferenceUtils.getInstance().saveString("workers" , item.getWorkers());
                                SharePreferenceUtils.getInstance().saveString("looms" , item.getLooms());
                                SharePreferenceUtils.getInstance().saveString("location" , item.getLocation());

                                if (item.getName().length() > 0)
                                {

                                    Intent intent = new Intent(OTP2.this , MainActivity.class);
                                    startActivity(intent);
                                    finishAffinity();

                                    Toast.makeText(OTP2.this, "Welcome " + item.getName(), Toast.LENGTH_SHORT).show();

                                }
                                else
                                {

                                    Intent intent = new Intent(OTP2.this , REgister.class);
                                    startActivity(intent);
                                    finishAffinity();

                                    Toast.makeText(OTP2.this, "Please complete your profile to continue", Toast.LENGTH_SHORT).show();

                                }




                            }
                            else
                            {
                                Toast.makeText(OTP2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            progress.setVisibility(View.GONE);


                        }

                        @Override
                        public void onFailure(Call<verifyBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });




                }
                else
                {
                    Toast.makeText(OTP2.this, "Please enter a valid OTP", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
