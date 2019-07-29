package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.roshni.verifyPOJO.verifyBean;
import com.rilixtech.CountryCodePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Signup extends AppCompatActivity {

    Button login , signup;
    CountryCodePicker code;
    EditText phone;
    ProgressBar progress;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        type = getIntent().getStringExtra("type");

        login = findViewById(R.id.button2);
        signup = findViewById(R.id.button);

        code = findViewById(R.id.code);
        phone = findViewById(R.id.phone);
        progress = findViewById(R.id.progressBar);

        code.registerPhoneNumberTextView(phone);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String p = phone.getText().toString();

                if (p.length() == 10)
                {


                    final String pho = code.getFullNumber();

                    progress.setVisibility(View.VISIBLE);

                    Bean b = (Bean) getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseurl)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


                    Call<verifyBean> call = cr.worker_signup(pho , type , SharePreferenceUtils.getInstance().getString("token"));
                    call.enqueue(new Callback<verifyBean>() {
                        @Override
                        public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {

                            if (response.body().getStatus().equals("1"))
                            {

                                Intent intent = new Intent(Signup.this , OTP2.class);
                                intent.putExtra("phone" , pho);
                                startActivity(intent);
                                Toast.makeText(Signup.this, "Please verify OTP", Toast.LENGTH_SHORT).show();
                                finishAffinity();

                            }
                            else
                            {
                                Toast.makeText(Signup.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<verifyBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });



                }
                else
                {
                    Toast.makeText(Signup.this, "Invalid contact number", Toast.LENGTH_SHORT).show();
                }



            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this , SignupLogin.class);
                startActivity(intent);
                finishAffinity();
            }
        });

    }
}
