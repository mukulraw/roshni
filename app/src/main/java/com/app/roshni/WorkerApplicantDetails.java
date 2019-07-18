package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.roshni.verifyPOJO.verifyBean;
import com.app.roshni.workerListPOJO.Datum;
import com.app.roshni.workerListPOJO.workerListBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WorkerApplicantDetails extends AppCompatActivity {

    ImageButton back;
    TextView jobtitle , jobcategory , salary , posted;
    CircleImageView image;
    TextView name , phone , skill , experience , employment , dob , gender , current , permanent , category , religion , educational , marital , sector , employer , home;

    ProgressBar progress;

    Button accept , reject;

    String id , status , tit , cat , sal , pos , sts , jid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_applicant_details);

        id = getIntent().getStringExtra("jid");
        jid = getIntent().getStringExtra("id");
        status = getIntent().getStringExtra("status");
        tit = getIntent().getStringExtra("title");
        cat = getIntent().getStringExtra("category");
        sal = getIntent().getStringExtra("salary");
        pos = getIntent().getStringExtra("posted");
        sts = getIntent().getStringExtra("sts");

        back = findViewById(R.id.imageButton3);
        jobtitle = findViewById(R.id.textView30);
        jobcategory = findViewById(R.id.textView31);
        salary = findViewById(R.id.textView32);
        posted = findViewById(R.id.imageView6);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        skill = findViewById(R.id.skill);
        experience = findViewById(R.id.experience);
        employment = findViewById(R.id.employment);
        dob = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        current = findViewById(R.id.current);
        permanent = findViewById(R.id.permanent);
        category = findViewById(R.id.category);
        religion = findViewById(R.id.religion);
        educational = findViewById(R.id.education);
        marital = findViewById(R.id.marital);
        sector = findViewById(R.id.sector);
        employer = findViewById(R.id.employer);
        home = findViewById(R.id.home_based);
        progress = findViewById(R.id.progressBar5);
        reject = findViewById(R.id.button12);
        accept = findViewById(R.id.button10);

        progress.setVisibility(View.VISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        jobtitle.setText(tit);
        jobcategory.setText("Job category: " + cat);
        salary.setText("Salary: " + sal);
        posted.setText("Posted on: " + pos);


        if (status.equals("Pending"))
        {

            if (sts.equals("Active"))
            {
                accept.setVisibility(View.VISIBLE);
                reject.setVisibility(View.VISIBLE);
            }
            else
            {
                accept.setVisibility(View.GONE);
                reject.setVisibility(View.GONE);
            }


        }
        else
        {
            accept.setVisibility(View.GONE);
            reject.setVisibility(View.GONE);
        }

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


        Call<workerListBean> call = cr.getWorkerById(id);

        call.enqueue(new Callback<workerListBean>() {
            @Override
            public void onResponse(Call<workerListBean> call, Response<workerListBean> response) {

                Datum item = response.body().getData().get(0);


                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();
                ImageLoader loader = ImageLoader.getInstance();
                loader.displayImage(item.getPhoto() , image , options);

                name.setText(item.getName());
                phone.setText("+" + item.getPhone());
                skill.setText(item.getSkills());
                experience.setText(item.getExperience());
                employment.setText(item.getEmployment());
                dob.setText(item.getDob());
                gender.setText(item.getGender());
                current.setText(item.getCstreet() + ", " + item.getCarea() + ", " + item.getCdistrict() + ", " + item.getCstate() + "-" + item.getCpin());
                permanent.setText(item.getPstreet() + ", " + item.getParea() + ", " + item.getPdistrict() + ", " + item.getPstate() + "-" + item.getPpin());
                category.setText(item.getCategory());
                religion.setText(item.getReligion());
                educational.setText(item.getEducational());
                marital.setText(item.getMarital());
                sector.setText(item.getSector());
                employer.setText(item.getEmployer());
                home.setText(item.getHome());



                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<workerListBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress.setVisibility(View.VISIBLE);

                Bean b = (Bean) getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseurl)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


                Call<verifyBean> call = cr.worker_acept_reject(jid , id , "Approved");

                call.enqueue(new Callback<verifyBean>() {
                    @Override
                    public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {


                        Toast.makeText(WorkerApplicantDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        progress.setVisibility(View.GONE);

                        finish();
                    }

                    @Override
                    public void onFailure(Call<verifyBean> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                    }
                });

            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress.setVisibility(View.VISIBLE);

                Bean b = (Bean) getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseurl)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


                Call<verifyBean> call = cr.worker_acept_reject(jid , id , "Rejected");

                call.enqueue(new Callback<verifyBean>() {
                    @Override
                    public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {


                        Toast.makeText(WorkerApplicantDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        progress.setVisibility(View.GONE);

                        finish();
                    }

                    @Override
                    public void onFailure(Call<verifyBean> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                    }
                });

            }
        });

    }
}
