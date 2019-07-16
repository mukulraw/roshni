package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseJob extends AppCompatActivity {

    Button business , contractor , worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_job);


        business = findViewById(R.id.button4);
        contractor = findViewById(R.id.button5);
        worker = findViewById(R.id.button6);



        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseJob.this , PostJob.class);
                startActivity(intent);
                finish();

            }
        });

        contractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseJob.this , PostJobContractor.class);
                startActivity(intent);
                finish();

            }
        });



    }
}
