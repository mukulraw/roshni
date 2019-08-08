package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.roshni.contractorJobDetailsPOJO.Data;
import com.app.roshni.contractorJobDetailsPOJO.contractorJobDetailsBean;
import com.app.roshni.verifyPOJO.verifyBean;
import com.app.roshni.workerJobListPOJO.Datum;
import com.app.roshni.workerJobListPOJO.workerJobDetailBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JobDetails2 extends AppCompatActivity {

    ImageButton back;
    TextView title, company, address, type, experience, days, rate , commp;

    Button apply;

    ProgressBar progress;
    CircleImageView image;
    String jid;
    ImageView sample;
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details2);

        jid = getIntent().getStringExtra("jid");

        back = findViewById(R.id.imageButton3);
        image = findViewById(R.id.imageView6);
        title = findViewById(R.id.textView30);
        company = findViewById(R.id.textView31);
        address = findViewById(R.id.textView32);
        type = findViewById(R.id.type);
        experience = findViewById(R.id.experience);
        days = findViewById(R.id.days);
        rate = findViewById(R.id.rate);
        apply = findViewById(R.id.button8);
        progress = findViewById(R.id.progressBar4);
        header = findViewById(R.id.constraintLayout);
        commp = findViewById(R.id.company);
        sample = findViewById(R.id.sample);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String t = apply.getText().toString();

                if (t.equals("APPLY NOW"))
                {

                    progress.setVisibility(View.VISIBLE);

                    Bean b = (Bean) getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseurl)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


                    Call<verifyBean> call = cr.apply_job2(jid , SharePreferenceUtils.getInstance().getString("user_id"));

                    call.enqueue(new Callback<verifyBean>() {
                        @Override
                        public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {


                            final Dialog dialog = new Dialog(JobDetails2.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(true);
                            dialog.setContentView(R.layout.apply_dialog);
                            dialog.show();

                            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialogInterface) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });



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
                    Toast.makeText(JobDetails2.this, "You have already applied for this job" , Toast.LENGTH_SHORT).show();
                }

            }
        });


        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomBrand2 bottom = bottomBrand2.newInstance();
                Bundle b = new Bundle();
                b.putString("jid" , jid);
                bottom.setArguments(b);
                bottom.show(getSupportFragmentManager() , "bottomBrand");


            }
        });

        commp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomBrand2 bottom = bottomBrand2.newInstance();
                Bundle b = new Bundle();
                b.putString("jid" , jid);
                bottom.setArguments(b);
                bottom.show(getSupportFragmentManager() , "bottomBrand");


            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        progress.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

        Call<contractorJobDetailsBean> call = cr.getJobDetailsForContractor(SharePreferenceUtils.getInstance().getString("user_id"), jid);

        call.enqueue(new Callback<contractorJobDetailsBean>() {
            @Override
            public void onResponse(Call<contractorJobDetailsBean> call, Response<contractorJobDetailsBean> response) {


                if (response.body().getStatus().equals("1")) {

                    Data item = response.body().getData();

                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
                    ImageLoader loader = ImageLoader.getInstance();
                    loader.displayImage(item.getLogo() , image , options);

                    loader.displayImage(item.getSample() , sample , options);

                    title.setText(item.getTitle());
                    company.setText(item.getBrandName());
                    address.setText(item.getBrandStreet() + ", " + item.getBrandArea());
                    title.setText(item.getTitle());
                    type.setText(item.getJobType());
                    days.setText(item.getDays());
                    experience.setText(item.getExperience());
                    rate.setText(item.getRate());

                    if (item.getStatus().equals("1"))
                    {
                        apply.setText("APPLIED");
                        //apply.setEnabled(false);
                    }
                    else
                    {
                        apply.setText("APPLY NOW");
                        //apply.setEnabled(true);
                    }

                }


                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<contractorJobDetailsBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }


}
