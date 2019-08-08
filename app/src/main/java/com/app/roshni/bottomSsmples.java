package com.app.roshni;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.roshni.samplePOJO.Datum;
import com.app.roshni.samplePOJO.sampleBean;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class bottomSsmples extends BottomSheetDialogFragment {

    RecyclerView grid;
    StaggeredGridLayoutManager manager;
    Button upload , finish;
    ProgressBar progress;
    List<Datum> list;
    SampleAdapter adapter;
    ImageView nodata;
    private Uri uri;
    private File f1;

    String jid;

    static bottomSsmples newInstance() {

        return new bottomSsmples();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.samples_layout , container , false);

        jid = getArguments().getString("jid");

        list = new ArrayList<>();

        grid = view.findViewById(R.id.grid);
        upload = view.findViewById(R.id.button16);
        finish = view.findViewById(R.id.button15);
        progress = view.findViewById(R.id.progressBar3);
        nodata = view.findViewById(R.id.imageView5);

        manager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
        adapter = new SampleAdapter(getContext() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);



        finish.setVisibility(View.GONE);
        upload.setVisibility(View.GONE);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();


        progress.setVisibility(View.VISIBLE);

        Bean b = (Bean) getActivity().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

        Call<sampleBean> call = cr.getSamples(jid);

        call.enqueue(new Callback<sampleBean>() {
            @Override
            public void onResponse(Call<sampleBean> call, Response<sampleBean> response) {

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
            public void onFailure(Call<sampleBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });




    }

    class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder>
    {
        Context context;
        List<Datum> list = new ArrayList<>();


        SampleAdapter(Context context, List<Datum> list)
        {
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
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.sample_list_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            final Datum item = list.get(position);

            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
            ImageLoader loader = ImageLoader.getInstance();
            loader.displayImage(item.getFile() , holder.image , options);


            holder.delete.setVisibility(View.GONE);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

            ImageView image;
            ImageButton delete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                image = itemView.findViewById(R.id.image);
                delete = itemView.findViewById(R.id.delete);

            }
        }
    }

}
