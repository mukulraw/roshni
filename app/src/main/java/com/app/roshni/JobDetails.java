package com.app.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class JobDetails extends AppCompatActivity {

    ImageButton back;
    TextView title, company, address, skills, preferred, location, experience, role, gender, education, hours, salary, stype , commp;

    Button apply;

    ProgressBar progress;
    CircleImageView image;
    String jid;

    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        jid = getIntent().getStringExtra("jid");

        back = findViewById(R.id.imageButton3);
        image = findViewById(R.id.imageView6);
        title = findViewById(R.id.textView30);
        company = findViewById(R.id.textView31);
        address = findViewById(R.id.textView32);
        skills = findViewById(R.id.skills);
        preferred = findViewById(R.id.preferred);
        location = findViewById(R.id.location);
        experience = findViewById(R.id.experience);
        role = findViewById(R.id.role);
        gender = findViewById(R.id.gender);
        education = findViewById(R.id.education);
        hours = findViewById(R.id.hours);
        salary = findViewById(R.id.salary);
        stype = findViewById(R.id.stype);
        apply = findViewById(R.id.button8);
        progress = findViewById(R.id.progressBar4);
        header = findViewById(R.id.constraintLayout);
        commp = findViewById(R.id.company);


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


                    Call<verifyBean> call = cr.apply_job(jid , SharePreferenceUtils.getInstance().getString("user_id"));

                    call.enqueue(new Callback<verifyBean>() {
                        @Override
                        public void onResponse(Call<verifyBean> call, Response<verifyBean> response) {


                            final Dialog dialog = new Dialog(JobDetails.this);
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
                    Toast.makeText(JobDetails.this, "You haev already applied for this job" , Toast.LENGTH_SHORT).show();
                }

            }
        });


        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomBrand bottom = bottomBrand.newInstance();
                Bundle b = new Bundle();
                b.putString("jid" , jid);
                bottom.setArguments(b);
                bottom.show(getSupportFragmentManager() , "bottomBrand");


            }
        });

        commp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomBrand bottom = bottomBrand.newInstance();
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

        Call<workerJobDetailBean> call = cr.getJobDetailForWorker(SharePreferenceUtils.getInstance().getString("user_id"), jid);

        call.enqueue(new Callback<workerJobDetailBean>() {
            @Override
            public void onResponse(Call<workerJobDetailBean> call, Response<workerJobDetailBean> response) {


                if (response.body().getStatus().equals("1")) {

                    Datum item = response.body().getData();

                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
                    ImageLoader loader = ImageLoader.getInstance();
                    loader.displayImage(item.getLogo() , image , options);

                    title.setText(item.getTitle());
                    company.setText(item.getBrandName());
                    address.setText(item.getBrandStreet() + ", " + item.getBrandArea());
                    skills.setText(item.getSkills());
                    preferred.setText(item.getPreferred());
                    location.setText(item.getLocation());
                    experience.setText(item.getExperience());
                    role.setText(item.getRole());
                    gender.setText(item.getGender());
                    education.setText(item.getEducation());
                    hours.setText(item.getHours());
                    salary.setText(item.getSalary());
                    stype.setText(item.getStype());

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
            public void onFailure(Call<workerJobDetailBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }
}
