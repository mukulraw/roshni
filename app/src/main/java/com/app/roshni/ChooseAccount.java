package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseAccount extends AppCompatActivity {

    Button business , contractor , worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);

        business = findViewById(R.id.button4);
        contractor = findViewById(R.id.button5);
        worker = findViewById(R.id.button6);



        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseAccount.this , Signup.class);
                intent.putExtra("type" , "worker");
                startActivity(intent);
                finishAffinity();

            }
        });

        contractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseAccount.this , Signup.class);
                intent.putExtra("type" , "contractor");
                startActivity(intent);
                finishAffinity();

            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseAccount.this , Signup.class);
                intent.putExtra("type" , "brand");
                startActivity(intent);
                finishAffinity();

            }
        });

    }
}
