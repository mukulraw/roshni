package com.app.roshni;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.app.Activity.RESULT_OK;

public class newjobs extends Fragment {

    RecyclerView grid;
    GridLayoutManager manager;
    JobsAdapter adapter;
    List<Datum> list;
    ProgressBar progress;
    ImageView nodata;
    TextView date;
    String dd;
    List<String> sk , lo , ex , jo , ed , sa;
    FloatingActionButton filter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jobs_layout1, container, false);

        list = new ArrayList<>();
        sk = new ArrayList<>();
        lo = new ArrayList<>();
        ex = new ArrayList<>();
        jo = new ArrayList<>();
        ed = new ArrayList<>();
        sa = new ArrayList<>();

        grid = view.findViewById(R.id.grid);
        filter = view.findViewById(R.id.floatingActionButton2);
        date = view.findViewById(R.id.date);
        progress = view.findViewById(R.id.progressBar3);
        nodata = view.findViewById(R.id.imageView5);

        adapter = new JobsAdapter(getContext() , list);
        manager = new GridLayoutManager(getContext(), 1);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.date_dialog);
                dialog.show();


                final DatePicker picker = dialog.findViewById(R.id.date);
                Button ok = dialog.findViewById(R.id.ok);

                long now = System.currentTimeMillis() - 1000;
                picker.setMaxDate(now);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int year = picker.getYear();
                        int month = picker.getMonth();
                        int day = picker.getDayOfMonth();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String strDate = format.format(calendar.getTime());

                        dialog.dismiss();

                        date.setText("Date - " + strDate + " (click to change)");

                        dd = strDate;

                        progress.setVisibility(View.VISIBLE);

                        Bean b = (Bean) getContext().getApplicationContext();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseurl)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


                        Call<workerJobListBean> call = cr.getJobListForWorker(SharePreferenceUtils.getInstance().getString("user_id") , strDate);

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
                });


            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String skil = TextUtils.join(",", sk);
                String lcoa = TextUtils.join(",", lo);
                String expe = TextUtils.join(",", ex);
                String educ = TextUtils.join(",", ed);
                String jobr = TextUtils.join(",", jo);
                String sala = TextUtils.join(",", sa);

                Log.d("skills1" , skil);
                Log.d("location1" , lcoa);
                Log.d("experience1" , expe);
                Log.d("education1" , educ);
                Log.d("job_role1" , jobr);
                Log.d("salary_type1" , sala);


                Intent intent = new Intent(getContext() , FilterWorkerJob.class);
                intent.putExtra("skill" , skil);
                intent.putExtra("location" , lcoa);
                intent.putExtra("experience" , expe);
                intent.putExtra("education" , educ);
                intent.putExtra("job_role" , jobr);
                intent.putExtra("salary_type" , sala);
                startActivityForResult(intent , 123);

            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);

        Log.d("dddd", formattedDate);

        date.setText("Date - " + formattedDate + " (click to change)");

        dd = formattedDate;

        progress.setVisibility(View.VISIBLE);

        Bean b = (Bean) getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);


        Call<workerJobListBean> call = cr.getJobListForWorker(SharePreferenceUtils.getInstance().getString("user_id") , formattedDate);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123 && resultCode == RESULT_OK)
        {

            String skil1 = data.getStringExtra("skill");
            String loca1 = data.getStringExtra("location");
            String expe1 = data.getStringExtra("experience");
            String jobr1 = data.getStringExtra("job_role");
            String educ1 = data.getStringExtra("education");
            String sala1 = data.getStringExtra("salary_type");

            String [] ski1 = skil1.split(",");
            String [] loc1 = loca1.split(",");
            String [] exp1 = expe1.split(",");
            String [] job1 = jobr1.split(",");
            String [] edu1 = educ1.split(",");
            String [] sal1 = sala1.split(",");

            sk.addAll(Arrays.asList(ski1));
            lo.addAll(Arrays.asList(loc1));
            ex.addAll(Arrays.asList(exp1));
            jo.addAll(Arrays.asList(job1));
            ed.addAll(Arrays.asList(edu1));
            sa.addAll(Arrays.asList(sal1));

        }
    }
}
