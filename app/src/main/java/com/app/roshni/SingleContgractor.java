package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.roshni.contractorPOJO.Data;
import com.app.roshni.contractorPOJO.contractorBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SingleContgractor extends AppCompatActivity {

    Toolbar toolbar;
    CircleImageView image;
    TextView name , phone , total , experience , availability , dob , gender , current , permanent , home_based , home_location , male , female , type , employer , about , samples;
    ProgressBar progress;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contgractor);

        id = getIntent().getStringExtra("jid");

        toolbar = findViewById(R.id.toolbar2);
        image = findViewById(R.id.image);
        progress = findViewById(R.id.progressBar5);
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
        toolbar.setTitle(getString(R.string.cont_details));

        progress.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


        Call<contractorBean> call = cr.getContractorById(id);

        Log.d("jid" , id);

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
