package com.vivekbalachandra.android.client.Adapters;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vivekbalachandra.android.client.Data.Database.Entity.TasksData;
import com.vivekbalachandra.android.client.Model.TaskModel;
import com.vivekbalachandra.android.client.Model.TasksViewModel;
import com.vivekbalachandra.android.client.R;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskItemHolder> {


    private List<TasksData> dataset;
    private View ItemView;
    public TaskListAdapter(Context context, List<TasksData> dataset)
    {
            this.dataset=dataset;

    }

    public void setDataset(List<TasksData> dataset){
        this.dataset=dataset;
        this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TaskItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item,parent);

        return new TaskItemHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull TaskItemHolder holder, int position) {

        holder.mHead.setText(dataset.get(position).header);
        holder.mDescription.setText(dataset.get(position).discription);

        return ;
    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class TaskItemHolder extends RecyclerView.ViewHolder{

        TextView mHead;
        TextView mDescription;

        public TaskItemHolder(View itemView)
        {

            super(itemView);
            mHead=itemView.findViewById(R.id.task_header);
            mDescription=itemView.findViewById(R.id.task_description);
        }
    }
}
