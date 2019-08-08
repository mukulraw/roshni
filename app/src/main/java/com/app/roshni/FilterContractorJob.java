package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.roshni.sectorPOJO.sectorBean;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FilterContractorJob extends AppCompatActivity {

    ImageView cross;
    ChipGroup skills, experience;

    List<String> sk, ex;

    ProgressBar progress;
    LayoutInflater inflater;
    List<String> exp, ski;

    String skil1, expe1;

    Button filter, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_contractor_job);

        skil1 = getIntent().getStringExtra("skill");

        expe1 = getIntent().getStringExtra("experience");


        exp = new ArrayList<>();
        ski = new ArrayList<>();


        sk = new ArrayList<>();

        ex = new ArrayList<>();


        skills = findViewById(R.id.skills);
        cross = findViewById(R.id.imageButton4);

        experience = findViewById(R.id.experience);


        progress = findViewById(R.id.progressBar6);
        filter = findViewById(R.id.button13);
        clear = findViewById(R.id.button14);


        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);


        exp.add("0 to 2 years");
        exp.add("3 to 5 years");
        exp.add("5 to 10 years");
        exp.add("more than 10 years");


        experience.removeAllViews();

        String[] exp1 = expe1.split(",");

        for (int i = 0; i < exp.size(); i++) {

            Chip chip = (Chip) inflater.inflate(R.layout.chip, null);
            chip.setText(exp.get(i));

            for (String s : exp1) {
                if (exp.get(i).equals(s)) {
                    chip.setChecked(true);
                }
            }

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (b) {
                        ex.add(compoundButton.getText().toString());
                    } else {
                        ex.remove(compoundButton.getText().toString());
                    }

                }
            });

            experience.addView(chip);

        }


        ski.add("Embroidery");
        ski.add("Adda-Work");
        ski.add("Fashion Jewelry");
        ski.add("Bead-Work");
        ski.add("Stone-Work");
        ski.add("Artificial Jewelry");


        skills.removeAllViews();

        String[] ski1 = skil1.split(",");

        for (int i = 0; i < ski.size(); i++) {

            Chip chip = (Chip) inflater.inflate(R.layout.chip, null);
            chip.setText(ski.get(i));

            for (String s : ski1) {
                if (ski.get(i).equals(s)) {
                    chip.setChecked(true);
                }
            }

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (b) {
                        sk.add(compoundButton.getText().toString());
                    } else {
                        sk.remove(compoundButton.getText().toString());
                    }

                }
            });

            skills.addView(chip);

        }


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                skills.clearCheck();

                experience.clearCheck();


                sk.clear();

                ex.clear();


            }
        });


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String skil = TextUtils.join(",", sk);
                String expe = TextUtils.join(",", ex);


                Intent intent = new Intent();
                intent.putExtra("skill", skil);
                intent.putExtra("experience", expe);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }
}
