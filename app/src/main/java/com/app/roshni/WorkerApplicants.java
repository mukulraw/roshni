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

import com.app.roshni.workerListPOJO.Datum;
import com.app.roshni.workerListPOJO.workerListBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WorkerApplicants extends AppCompatActivity {

    RecyclerView grid;
    GridLayoutManager manager;
    JobsAdapter adapter;
    List<Datum> list;
    ProgressBar progress;
    ImageView nodata;
    Toolbar toolbar;
    String jid , title , category , salary , posted , sts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_applicants);

        jid = getIntent().getStringExtra("jid");
        title = getIntent().getStringExtra("title");
        category = getIntent().getStringExtra("category");
        salary = getIntent().getStringExtra("salary");
        posted = getIntent().getStringExtra("posted");
        sts = getIntent().getStringExtra("sts");

        list = new ArrayList<>();

        toolbar = findViewById(R.id.toolbar);
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
        toolbar.setTitle(getString(R.string.applicants));


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


        Call<workerListBean> call = cr.getAppliedWorkers(jid);

        call.enqueue(new Callback<workerListBean>() {
            @Override
            public void onResponse(Call<workerListBean> call, Response<workerListBean> response) {

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
            public void onFailure(Call<workerListBean> call, Throwable t) {
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
            View view = inflater.inflate(R.layout.worker_list_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            final Datum item = list.get(position);

            holder.reg.setAllCaps(true);

            holder.name.setText(item.getName());
            holder.skill.setText(item.getSkills());
            holder.exp.setText(item.getExperience());
            holder.emp.setText(item.getEmployment());
            holder.reg.setText(item.getStatus());
            holder.address.setText(item.getCstreet() + ", " + item.getCarea() + ", " + item.getCdistrict() + ", " + item.getCstate() + "-" + item.getCpin());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context , WorkerApplicantDetails.class);
                    intent.putExtra("jid" , item.getUserId());
                    intent.putExtra("id" , jid);
                    intent.putExtra("status" , item.getStatus());
                    intent.putExtra("title" , title);
                    intent.putExtra("category" , category);
                    intent.putExtra("salary" , salary);
                    intent.putExtra("posted" , posted);
                    intent.putExtra("sts" , sts);
                    startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView name , address, skill , exp , emp , reg;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.textView20);
                address = itemView.findViewById(R.id.textView26);
                skill = itemView.findViewById(R.id.textView28);
                exp = itemView.findViewById(R.id.textView34);
                emp = itemView.findViewById(R.id.textView35);
                reg = itemView.findViewById(R.id.textView22);

            }
        }
    }

}
