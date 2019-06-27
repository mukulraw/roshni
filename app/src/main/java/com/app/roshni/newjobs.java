package com.app.roshni;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class newjobs extends Fragment {

    RecyclerView grid;
    GridLayoutManager manager;
    JobsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jobs_layout, container, false);

        grid = view.findViewById(R.id.grid);

        adapter = new JobsAdapter(getContext());
        manager = new GridLayoutManager(getContext(), 1);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        return view;
    }

    class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {
        Context context;

        JobsAdapter(Context context) {
            this.context = context;
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

        }

        @Override
        public int getItemCount() {
            return 7;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

}
