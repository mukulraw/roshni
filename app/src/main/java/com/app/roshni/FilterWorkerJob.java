package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.roshni.sectorPOJO.sectorBean;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FilterWorkerJob extends AppCompatActivity {

    ImageView cross;
    ChipGroup skills , location , experience , job_role , education , salary_type;

    List<String> sk , lo , ex , jo , ed , sa;

    ProgressBar progress;
    LayoutInflater inflater;
    List<String> exp , edu , sty;

    String skil1 , loca1 , expe1 , jobr1 , educ1 , sala1;

    Button filter , clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_worker_job);

        skil1 = getIntent().getStringExtra("skill");
        loca1 = getIntent().getStringExtra("location");
        expe1 = getIntent().getStringExtra("experience");
        jobr1 = getIntent().getStringExtra("job_role");
        educ1 = getIntent().getStringExtra("education");
        sala1 = getIntent().getStringExtra("salary_type");

        exp = new ArrayList<>();
        edu = new ArrayList<>();
        sty = new ArrayList<>();

        sk = new ArrayList<>();
        lo = new ArrayList<>();
        ex = new ArrayList<>();
        jo = new ArrayList<>();
        ed = new ArrayList<>();
        sa = new ArrayList<>();

        skills = findViewById(R.id.skills);
        cross = findViewById(R.id.imageButton4);
        location = findViewById(R.id.location);
        experience = findViewById(R.id.experience);
        job_role = findViewById(R.id.job_role);
        education = findViewById(R.id.education);
        salary_type = findViewById(R.id.salary_type);
        progress = findViewById(R.id.progressBar6);
        filter = findViewById(R.id.button13);
        clear = findViewById(R.id.button14);


        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED,returnIntent);
                finish();
            }
        });

        inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);


        exp.add("0 to 2 years");
        exp.add("3 to 5 years");
        exp.add("5 to 10 years");
        exp.add("more than 10 years");


        experience.removeAllViews();

        String[] exp1 = expe1.split(",");

        for (int i = 0; i < exp.size(); i++) {

            Chip chip = (Chip) inflater.inflate(R.layout.chip , null);
            chip.setText(exp.get(i));

            for (String s : exp1) {
                if (exp.get(i).equals(s)) {
                    chip.setChecked(true);
                }
            }

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (b)
                    {
                        ex.add(compoundButton.getText().toString());
                    }
                    else
                    {
                        ex.remove(compoundButton.getText().toString());
                    }

                }
            });

            experience.addView(chip);

        }



        edu.add("Uneducated");
        edu.add("Primary (Class 1-5)");
        edu.add("Middle (Class 6-8)");
        edu.add("Secondary (Class 9-10)");
        edu.add("Senior Secondary (Class 11-12)");
        edu.add("Graduation");
        edu.add("Post Graduation");


        education.removeAllViews();

        String[] edu1 = educ1.split(",");

        for (int i = 0; i < edu.size(); i++) {

            Chip chip = (Chip) inflater.inflate(R.layout.chip , null);
            chip.setText(edu.get(i));

            for (String s : edu1) {
                if (edu.get(i).equals(s)) {
                    chip.setChecked(true);
                }
            }


            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (b)
                    {
                        ed.add(compoundButton.getText().toString());
                    }
                    else
                    {
                        ed.remove(compoundButton.getText().toString());
                    }

                }
            });

            education.addView(chip);

        }


        sty.add("Monthly");
        sty.add("Fortnightly");
        sty.add("Daily");
        sty.add("Piece-rate");
        sty.add("Weekly");


        salary_type.removeAllViews();

        String[] sty1 = sala1.split(",");

        for (int i = 0; i < sty.size(); i++) {

            Chip chip = (Chip) inflater.inflate(R.layout.chip , null);
            chip.setText(sty.get(i));

            for (String s : sty1) {
                if (sty.get(i).equals(s)) {
                    chip.setChecked(true);
                }
            }

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (b)
                    {
                        sa.add(compoundButton.getText().toString());
                    }
                    else
                    {
                        sa.remove(compoundButton.getText().toString());
                    }

                }
            });

            salary_type.addView(chip);

        }




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

                job_role.removeAllViews();
                String[] job1 = jobr1.split(",");
                if (response.body().getStatus().equals("1")) {

                    for (int i = 0; i < response.body().getData().size(); i++) {

                        Chip chip = (Chip) inflater.inflate(R.layout.chip , null);
                        chip.setText(response.body().getData().get(i).getTitle());

                        for (String s : job1) {
                            if (response.body().getData().get(i).getTitle().equals(s)) {
                                chip.setChecked(true);
                            }
                        }

                        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                                if (b)
                                {
                                    jo.add(compoundButton.getText().toString());
                                }
                                else
                                {
                                    jo.remove(compoundButton.getText().toString());
                                }

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


        Call<sectorBean> call2 = cr.getSkills();

        call2.enqueue(new Callback<sectorBean>() {
            @Override
            public void onResponse(Call<sectorBean> call, Response<sectorBean> response) {


                if (response.body().getStatus().equals("1")) {

                    skills.removeAllViews();
                    String[] ski1 = skil1.split(",");

                    for (int i = 0; i < response.body().getData().size(); i++) {

                        Chip chip = (Chip) inflater.inflate(R.layout.chip , null);
                        chip.setText(response.body().getData().get(i).getTitle());

                        for (String s : ski1) {
                            if (response.body().getData().get(i).getTitle().equals(s)) {
                                chip.setChecked(true);
                            }
                        }

                        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                                if (b)
                                {
                                    sk.add(compoundButton.getText().toString());
                                }
                                else
                                {
                                    sk.remove(compoundButton.getText().toString());
                                }

                            }
                        });

                        skills.addView(chip);

                    }



                }

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<sectorBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


        Call<sectorBean> call3 = cr.getLocations();

        call3.enqueue(new Callback<sectorBean>() {
            @Override
            public void onResponse(Call<sectorBean> call, Response<sectorBean> response) {


                if (response.body().getStatus().equals("1")) {

                    location.removeAllViews();
                    String[] loc1 = loca1.split(",");

                    for (int i = 0; i < response.body().getData().size(); i++) {

                        Chip chip = (Chip) inflater.inflate(R.layout.chip , null);
                        chip.setText(response.body().getData().get(i).getTitle());

                        for (String s : loc1) {
                            if (response.body().getData().get(i).getTitle().equals(s)) {
                                chip.setChecked(true);
                            }
                        }

                        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                                if (b)
                                {
                                    lo.add(compoundButton.getText().toString());
                                }
                                else
                                {
                                    lo.remove(compoundButton.getText().toString());
                                }

                            }
                        });

                        location.addView(chip);

                    }



                }

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<sectorBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                skills.clearCheck();
                location.clearCheck();
                experience.clearCheck();
                education.clearCheck();
                salary_type.clearCheck();
                job_role.clearCheck();

                sk.clear();
                lo.clear();
                ex.clear();
                jo.clear();
                ed.clear();
                sa.clear();

            }
        });


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String skil = TextUtils.join(",", sk);
                String lcoa = TextUtils.join(",", lo);
                String expe = TextUtils.join(",", ex);
                String educ = TextUtils.join(",", ed);
                String jobr = TextUtils.join(",", jo);
                String sala = TextUtils.join(",", sa);

                Log.d("skills" , skil);
                Log.d("location" , lcoa);
                Log.d("experience" , expe);
                Log.d("education" , educ);
                Log.d("job_role" , jobr);
                Log.d("salary_type" , sala);

                Intent intent = new Intent();
                intent.putExtra("skill" , skil);
                intent.putExtra("location" , lcoa);
                intent.putExtra("experience" , expe);
                intent.putExtra("education" , educ);
                intent.putExtra("job_role" , jobr);
                intent.putExtra("salary_type" , sala);
                setResult(RESULT_OK,intent);
                finish();

            }
        });


    }
}
