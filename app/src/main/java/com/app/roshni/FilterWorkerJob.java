package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.roshni.sectorPOJO.sectorBean;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FilterWorkerJob extends AppCompatActivity {

    ImageView cross;
    ChipGroup skills , location , experience , job_role , education , salary_type;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_worker_job);

        skills = findViewById(R.id.skills);
        location = findViewById(R.id.location);
        experience = findViewById(R.id.experience);
        job_role = findViewById(R.id.job_role);
        education = findViewById(R.id.education);
        salary_type = findViewById(R.id.salary_type);
        progress = findViewById(R.id.progressBar6);


        progress.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


        final Call<sectorBean> call = cr.getRoles();

        call.enqueue(new Callback<sectorBean>() {
            @Override
            public void onResponse(Call<sectorBean> call, Response<sectorBean> response) {


                if (response.body().getStatus().equals("1")) {

                    for (int i = 0; i < response.body().getData().size(); i++) {

                        final Chip chip = new Chip(FilterWorkerJob.this);
                        chip.setChipDrawable(ChipDrawable.createFromResource(FilterWorkerJob.this , R.xml.chip));
                        chip.setText(response.body().getData().get(i).getTitle());

                        chip.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                chip.toggle();
                            }
                        });

                        job_role.addView(chip);
                    }



                }

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<sectorBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }
}
