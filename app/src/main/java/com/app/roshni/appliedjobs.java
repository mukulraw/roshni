package com.app.roshni;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.roshni.workerJobListPOJO.Datum;
import com.app.roshni.workerJobListPOJO.workerJobListBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class appliedjobs extends Fragment {

    RecyclerView grid;
    GridLayoutManager manager;
    JobsAdapter adapter;
    List<Datum> list;
    ProgressBar progress;
    ImageView nodata;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.applied_jobs_layout, container, false);

        list = new ArrayList<>();

        grid = view.findViewById(R.id.grid);
        progress = view.findViewById(R.id.progressBar3);
        nodata = view.findViewById(R.id.imageView5);

        adapter = new JobsAdapter(getContext() , list);
        manager = new GridLayoutManager(getContext(), 1);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        progress.setVisibility(View.VISIBLE);

        Bean b = (Bean) getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


        Call<workerJobListBean> call = cr.getAppliedListForWorker(SharePreferenceUtils.getInstance().getString("user_id"));

        call.enqueue(new Callback<workerJobListBean>() {
            @Override
            public void onResponse(Call<workerJobListBean> call, Response<workerJobListBean> response) {

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
            public void onFailure(Call<workerJobListBean> call, Throwable t) {
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
            View view = inflater.inflate(R.layout.job_list_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            final Datum item = list.get(position);


            holder.company.setText(item.getBrandName());
            holder.title.setText(item.getTitle());
            holder.salary.setText("Salary: " + item.getSalary());
            holder.address.setText(item.getBrandStreet() + ", " + item.getBrandArea());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context , JobDetails.class);
                    intent.putExtra("jid" , item.getId());
                    startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView title , company , address , salary;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.textView20);
                company = itemView.findViewById(R.id.textView26);
                address = itemView.findViewById(R.id.textView28);
                salary = itemView.findViewById(R.id.textView22);

            }
        }
    }

}
