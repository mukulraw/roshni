package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.roshni.contractorPOJO.Data;
import com.app.roshni.contractorPOJO.contractorBean;
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

public class ContractorApplicantDetails extends AppCompatActivity {

    ImageButton back;
    TextView jobtitle , jobcategory , salary , posted;
    CircleImageView image;
    TextView name , phone , total , experience , availability , dob , gender , current , permanent , home_based , home_location , male , female , type , employer , about , samples;

    ProgressBar progress;

    Button accept , reject;

    String id , status , tit , cat , sal , pos , sts , jid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_applicant_details);

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
        total = findViewById(R.id.total);
        experience = findViewById(R.id.experience);
        availability = findViewById(R.id.availability);
        dob = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        current = findViewById(R.id.current);
        permanent = findViewById(R.id.permanent);
        home_based = findViewById(R.id.home_based);
        home_location = findViewById(R.id.home_location);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        type = findViewById(R.id.type);
        employer = findViewById(R.id.employer);
        about = findViewById(R.id.about);
        samples = findViewById(R.id.samples);
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
        salary.setText("Piece rate: " + sal);
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


        Call<contractorBean> call = cr.getContractorById(id);

        call.enqueue(new Callback<contractorBean>() {
            @Override
            public void onResponse(Call<contractorBean> call, Response<contractorBean> response) {

                Data item = response.body().getData();


                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();
                ImageLoader loader = ImageLoader.getInstance();
                loader.displayImage(item.getPhoto() , image , options);

                name.setText(item.getName());
                phone.setText("+" + item.getPhone());
                total.setText(String.valueOf(Integer.parseInt(item.getWorkersMale()) + Integer.parseInt(item.getWorkersFemale())) + " workers");
                experience.setText(item.getExperience());
                availability.setText(item.getAvailability());
                dob.setText(item.getDob());
                gender.setText(item.getGender());
                current.setText(item.getCstreet() + ", " + item.getCarea() + ", " + item.getCdistrict() + ", " + item.getCstate() + "-" + item.getCpin());
                permanent.setText(item.getPstreet() + ", " + item.getParea() + ", " + item.getPdistrict() + ", " + item.getPstate() + "-" + item.getPpin());
                home_based.setText(item.getHomeUnits());
                home_location.setText(item.getHomeLocation());
                male.setText(item.getWorkersMale());
                female.setText(item.getWorkersFemale());
                type.setText(item.getWorkType());
                employer.setText(item.getEmployer());
                about.setText(item.getAbout());



                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<contractorBean> call, Throwable t) {
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


                Call<verifyBean> call = cr.contractor_acept_reject(jid , id , "Approved");

                call.enqueue(new Callback<verifyBean>() {
                    @Override
                    public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {


                        Toast.makeText(ContractorApplicantDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

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


                Call<verifyBean> call = cr.contractor_acept_reject(jid , id , "Rejected");

                call.enqueue(new Callback<verifyBean>() {
                    @Override
                    public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {


                        Toast.makeText(ContractorApplicantDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

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

        samples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSsmples bottom = bottomSsmples.newInstance();
                Bundle b = new Bundle();
                b.putString("jid" , id);
                bottom.setArguments(b);
                bottom.show(getSupportFragmentManager() , "bottomBrand");

            }
        });

    }
}
