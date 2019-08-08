package com.app.roshni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.roshni.contractorJobDetailsPOJO.Data;
import com.app.roshni.contractorJobDetailsPOJO.contractorJobDetailsBean;
import com.app.roshni.verifyPOJO.verifyBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ContractorJobDetails extends AppCompatActivity {

    ImageButton back;
    ImageView sample;
    TextView title, type, experience, days, rate;

    Button active , edit;

    ProgressBar progress;
    String jid , status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_job_details);

        jid = getIntent().getStringExtra("jid");
        status = getIntent().getStringExtra("status");

        back = findViewById(R.id.imageButton3);
        edit = findViewById(R.id.button9);

        title = findViewById(R.id.textView30);
        type = findViewById(R.id.type);
        experience = findViewById(R.id.experience);
        days = findViewById(R.id.days);
        rate = findViewById(R.id.rate);
        active = findViewById(R.id.button8);
        progress = findViewById(R.id.progressBar4);
        sample = findViewById(R.id.sample);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String t = active.getText().toString();

                if (t.equals("ACTIVE"))
                {

                    progress.setVisibility(View.VISIBLE);

                    Bean b = (Bean) getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseurl)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


                    Call<verifyBean> call = cr.contractor_ac_inac(jid , "Active");

                    call.enqueue(new Callback<verifyBean>() {
                        @Override
                        public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {


                            Toast.makeText(ContractorJobDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            progress.setVisibility(View.GONE);

                            finish();
                        }

                        @Override
                        public void onFailure(Call<verifyBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });

                }
                else
                {
                    progress.setVisibility(View.VISIBLE);

                    Bean b = (Bean) getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseurl)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


                    Call<verifyBean> call = cr.contractor_ac_inac(jid , "Inactive");

                    call.enqueue(new Callback<verifyBean>() {
                        @Override
                        public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {


                            Toast.makeText(ContractorJobDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            progress.setVisibility(View.GONE);

                            finish();

                        }

                        @Override
                        public void onFailure(Call<verifyBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });
                }

            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ContractorJobDetails.this , UpdateContractorJob.class);
                intent.putExtra("jid" , jid);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        progress.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

        Call<contractorJobDetailsBean> call = cr.getJobDetailsForContractor(SharePreferenceUtils.getInstance().getString("user_id"), jid);

        call.enqueue(new Callback<contractorJobDetailsBean>() {
            @Override
            public void onResponse(Call<contractorJobDetailsBean> call, Response<contractorJobDetailsBean> response) {


                if (response.body().getStatus().equals("1")) {

                    Data item = response.body().getData();


                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();
                    ImageLoader loader = ImageLoader.getInstance();
                    loader.displayImage(item.getSample() , sample , options);


                    title.setText(item.getTitle());
                    type.setText(item.getJobType());
                    days.setText(item.getDays());
                    experience.setText(item.getExperience());
                    rate.setText(item.getRate());

                    if (status.equals("Active"))
                    {
                        active.setText("INACTIVE");
                        //apply.setEnabled(false);
                    }
                    else
                    {
                        active.setText("ACTIVE");
                        //apply.setEnabled(true);
                    }

                }


                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<contractorJobDetailsBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }

}
