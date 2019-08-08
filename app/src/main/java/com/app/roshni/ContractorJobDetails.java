package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.roshni.verifyPOJO.verifyBean;
import com.app.roshni.workerJobListPOJO.Datum;
import com.app.roshni.workerJobListPOJO.workerJobDetailBean;

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
        sample = findViewById(R.id.sample)

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


                    Call<verifyBean> call = cr.worker_ac_inac(jid , "Active");

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


                    Call<verifyBean> call = cr.worker_ac_inac(jid , "Inactive");

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

                Intent intent = new Intent(ContractorJobDetails.this , UpdateWorkerJob.class);
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

        Call<workerJobDetailBean> call = cr.getJobDetailForWorker(SharePreferenceUtils.getInstance().getString("user_id"), jid);

        call.enqueue(new Callback<workerJobDetailBean>() {
            @Override
            public void onResponse(Call<workerJobDetailBean> call, Response<workerJobDetailBean> response) {


                if (response.body().getStatus().equals("1")) {

                    Datum item = response.body().getData();

                    title.setText(item.getTitle());
                    skills.setText(item.getSkills());
                    preferred.setText(item.getPreferred());
                    location.setText(item.getLocation());
                    experience.setText(item.getExperience());
                    role.setText(item.getRole());
                    gender.setText(item.getGender());
                    education.setText(item.getEducation());
                    hours.setText(item.getHours());
                    salary.setText(item.getSalary());
                    stype.setText(item.getStype());

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
            public void onFailure(Call<workerJobDetailBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }

}
