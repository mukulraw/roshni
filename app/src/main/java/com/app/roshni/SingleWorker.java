package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class SingleWorker extends AppCompatActivity {

    Toolbar toolbar;
    CircleImageView image;
    TextView name , phone , skill , experience , employment , dob , gender , current , permanent , category , religion , educational , marital , sector , employer , home;
    ProgressBar progress;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_worker);

        id = getIntent().getStringExtra("jid");

        toolbar = findViewById(R.id.toolbar2);
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
        toolbar.setTitle("WORKER DETAILS");

        progress.setVisibility(View.VISIBLE);

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
                phone.setText("+" + item.getName());
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


    }
}
