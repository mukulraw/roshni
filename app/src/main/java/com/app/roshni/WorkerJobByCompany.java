package com.app.roshni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.roshni.allWorkContrJobListPOJO.Datum;
import com.app.roshni.allWorkContrJobListPOJO.allWorkContrJobBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WorkerJobByCompany extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView grid;
    GridLayoutManager manager;
    JobsAdapter adapter;
    List<Datum> list;
    ProgressBar progress;
    ImageView nodata;

    String bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_job_by_company);

        bid = getIntent().getStringExtra("bid");

        list = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar3);


        grid = findViewById(R.id.grid);
        progress = findViewById(R.id.progressBar3);
        nodata = findViewById(R.id.imageView5);

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
        toolbar.setTitle(getString(R.string.comp_jobs));

        adapter = new JobsAdapter(this , list);
        manager = new GridLayoutManager(this, 1);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);


    }

    @Override
    public void onResume() {
        super.onResume();

        progress.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


        Call<allWorkContrJobBean> call = cr.getAllWorkerJobs(bid , "Active" , "");

        call.enqueue(new Callback<allWorkContrJobBean>() {
            @Override
            public void onResponse(Call<allWorkContrJobBean> call, Response<allWorkContrJobBean> response) {

                if (response.body().getData().size() > 0)
                {
                    nodata.setVisibility(View.GONE);
                }
                else
                {
                    nodata.setVisibility(View.VISIBLE);
                }


                adapter.setData(response.body().getData());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<allWorkContrJobBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }

    class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {
        Context context;
        List<Datum> list = new ArrayList<>();

        JobsAdapter(Context context, List<Datum> list) {
            this.context = context;
            this.list = list;
        }

        public void setData(List<Datum> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.worker_active_list_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            final Datum item = list.get(position);


            holder.category.setText("Job category: " + item.getRole());
            holder.title.setText(item.getTitle());
            holder.salary.setText("Salary: " + item.getSalary());
            holder.posted.setText("Posted on: " + item.getCreated());

            holder.applied.setText(item.getApplied() + " Applied");

            holder.details.setVisibility(View.GONE);
            holder.applicants.setVisibility(View.GONE);

            /*holder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context , WorkerJobDetails.class);
                    intent.putExtra("jid" , item.getId());
                    intent.putExtra("status" , item.getStatus());
                    startActivity(intent);

                }
            });

            holder.applicants.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context , WorkerApplicants.class);
                    intent.putExtra("jid" , item.getId());
                    startActivity(intent);

                }
            });*/

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView title , category , salary , posted , applied , applicants , details;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.textView20);
                category = itemView.findViewById(R.id.textView26);
                salary = itemView.findViewById(R.id.textView28);
                posted = itemView.findViewById(R.id.textView22);
                applied = itemView.findViewById(R.id.textView29);
                applicants = itemView.findViewById(R.id.textView38);
                details = itemView.findViewById(R.id.textView39);

            }
        }
    }

}
