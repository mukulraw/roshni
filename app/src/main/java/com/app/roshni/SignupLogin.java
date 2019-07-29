package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class SignupLogin extends AppCompatActivity {

    Button login , signup;
    CountryCodePicker code;
    EditText phone;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);

        login = findViewById(R.id.button);
        signup = findViewById(R.id.button2);
        code = findViewById(R.id.code);
        phone = findViewById(R.id.phone);
        progress = findViewById(R.id.progressBar);

        code.registerPhoneNumberTextView(phone);

        login.setOnClickListener(new View.OnClickListener() {
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

                    Log.d("token" , SharePreferenceUtils.getInstance().getString("token"));

                    Call<verifyBean> call = cr.login(pho , SharePreferenceUtils.getInstance().getString("token"));
                    call.enqueue(new Callback<verifyBean>() {
                        @Override
                        public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {

                            if (response.body().getStatus().equals("1"))
                            {

                                Intent intent = new Intent(SignupLogin.this , OTP.class);
                                intent.putExtra("phone" , pho);
                                startActivity(intent);
                                Toast.makeText(SignupLogin.this, "Please verify OTP", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                            else
                            {
                                Toast.makeText(SignupLogin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(SignupLogin.this, "Invalid contact number", Toast.LENGTH_SHORT).show();
                }




            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupLogin.this , ChooseAccount.class);
                startActivity(intent);
            }
        });

    }
}
