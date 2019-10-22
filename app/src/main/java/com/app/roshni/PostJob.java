package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.roshni.sectorPOJO.sectorBean;
import com.app.roshni.verifyPOJO.verifyBean;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class PostJob extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Toolbar toolbar;
    EditText title, preferred, hours, salary;
    Spinner skills, location, experience, role, gender, education, stype;

    Button submit;
    ProgressBar progress;

    String skil, expe, loca, gend, educ, jrole, styp;

    List<String> ski, exp, loc, gen, edu, rol, sty;
    List<String> ski1, loc1, rol1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);


        ski = new ArrayList<>();
        exp = new ArrayList<>();
        gen = new ArrayList<>();
        loc = new ArrayList<>();
        edu = new ArrayList<>();
        loc1 = new ArrayList<>();
        rol = new ArrayList<>();
        sty = new ArrayList<>();
        ski1 = new ArrayList<>();
        rol1 = new ArrayList<>();

        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.title);
        preferred = findViewById(R.id.preferred);
        hours = findViewById(R.id.hours);
        salary = findViewById(R.id.salary);
        skills = findViewById(R.id.skills);
        location = findViewById(R.id.location);
        experience = findViewById(R.id.experience);
        role = findViewById(R.id.role);
        gender = findViewById(R.id.gender);
        education = findViewById(R.id.education);
        stype = findViewById(R.id.stype);
        submit = findViewById(R.id.submit);
        progress = findViewById(R.id.progress);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(getString(R.string.post_job));

        hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        PostJob.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                tpd.show(getFragmentManager(), "Timepickerdialog");

            }
        });

        gen.add("Select one --- ");
        gen.add("Male");
        gen.add("Female");

        exp.add("Select one --- ");
        exp.add("0 to 2 years");
        exp.add("3 to 5 years");
        exp.add("5 to 10 years");
        exp.add("more than 10 years");


        edu.add("Select one --- ");
        edu.add("Uneducated");
        edu.add("Primary (Class 1-5)");
        edu.add("Middle (Class 6-8)");
        edu.add("Secondary (Class 9-10)");
        edu.add("Senior Secondary (Class 11-12)");
        edu.add("Graduation");
        edu.add("Post Graduation");

        sty.add("Select one --- ");
        sty.add("Monthly");
        sty.add("Fortnightly");
        sty.add("Daily");
        sty.add("Piece-rate");
        sty.add("Weekly");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(this),
                R.layout.spinner_model, gen);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                R.layout.spinner_model, exp);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                R.layout.spinner_model, edu);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this,
                R.layout.spinner_model, sty);


        experience.setAdapter(adapter2);
        education.setAdapter(adapter3);
        gender.setAdapter(adapter);
        stype.setAdapter(adapter4);


        stype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    styp = sty.get(i);
                } else {
                    styp = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    gend = gen.get(i);
                } else {
                    gend = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        skills.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    skil = ski1.get(i - 1);
                } else {
                    skil = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    expe = exp.get(i);
                } else {
                    expe = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    educ = edu.get(i);
                } else {
                    educ = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    loca = loc1.get(i - 1);
                } else {
                    loca = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    jrole = rol1.get(i - 1);
                } else {
                    jrole = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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

                    rol.add("Select one --- ");


                    for (int i = 0; i < response.body().getData().size(); i++) {

                        rol.add(response.body().getData().get(i).getTitle());
                        rol1.add(response.body().getData().get(i).getId());

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostJob.this,
                            R.layout.spinner_model, rol);

                    role.setAdapter(adapter);

                    Log.d("sec", SharePreferenceUtils.getInstance().getString("sector"));


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

                    ski.add("Select one --- ");


                    for (int i = 0; i < response.body().getData().size(); i++) {

                        ski.add(response.body().getData().get(i).getTitle());
                        ski1.add(response.body().getData().get(i).getId());

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostJob.this,
                            R.layout.spinner_model, ski);

                    skills.setAdapter(adapter);


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

                    loc.add("Select one --- ");


                    for (int i = 0; i < response.body().getData().size(); i++) {

                        loc.add(response.body().getData().get(i).getTitle());
                        loc1.add(response.body().getData().get(i).getId());

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostJob.this,
                            R.layout.spinner_model, loc);

                    location.setAdapter(adapter);


                }

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<sectorBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String t = title.getText().toString();
                String p = preferred.getText().toString();
                String h = hours.getText().toString();
                String s = salary.getText().toString();


                if (t.length() > 0) {

                    if (skil.length() > 0) {
                        if (loca.length() > 0) {
                            if (expe.length() > 0) {

                                if (jrole.length() > 0) {

                                    if (h.length() > 0) {
                                        if (s.length() > 0) {
                                            if (styp.length() > 0) {




                                                progress.setVisibility(View.VISIBLE);

                                                Bean b = (Bean) getApplicationContext();

                                                Retrofit retrofit = new Retrofit.Builder()
                                                        .baseUrl(b.baseurl)
                                                        .addConverterFactory(ScalarsConverterFactory.create())
                                                        .addConverterFactory(GsonConverterFactory.create())
                                                        .build();

                                                AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

                                                Call<verifyBean> call1 = cr.postjob(
                                                        SharePreferenceUtils.getInstance().getString("user_id"),
                                                        t,
                                                        skil,
                                                        p,
                                                        loca,
                                                        expe,
                                                        jrole,
                                                        gend,
                                                        educ,
                                                        h,
                                                        s,
                                                        styp
                                                );

                                                call1.enqueue(new Callback<verifyBean>() {
                                                    @Override
                                                    public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {

                                                        if (response.body().getStatus().equals("1"))
                                                        {
                                                            Toast.makeText(PostJob.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                            finish();

                                                        }


                                                        progress.setVisibility(View.GONE);

                                                    }

                                                    @Override
                                                    public void onFailure(Call<verifyBean> call, Throwable t) {
                                                        progress.setVisibility(View.GONE);
                                                    }
                                                });



                                            } else {
                                                Toast.makeText(PostJob.this, "Invalid salary type", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(PostJob.this, "Invalid salary package", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(PostJob.this, "Invalid working hours", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(PostJob.this, "Invalid job role", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(PostJob.this, "Invalid experience", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(PostJob.this, "Invalid location", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PostJob.this, "Invalid skill", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PostJob.this, "Invalid job title", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {

        String time1 = ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
        String time2 = ((hourOfDayEnd > 12) ? hourOfDayEnd % 12 : hourOfDayEnd) + ":" + (minuteEnd < 10 ? ("0" + minuteEnd) : minuteEnd) + " " + ((hourOfDayEnd >= 12) ? "PM" : "AM");


        hours.setText(time1 + " to " + time2);


    }
}
