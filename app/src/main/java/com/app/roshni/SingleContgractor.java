package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SingleContgractor extends AppCompatActivity {

    Toolbar toolbar;
    CircleImageView image;
    TextView name , phone , total , experience , availability , dob , gender , current , permanent , home_based , home_location , male , female , type , employer;
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
        toolbar.setTitle("CONTRACTOR DETAILS");

    }
}
