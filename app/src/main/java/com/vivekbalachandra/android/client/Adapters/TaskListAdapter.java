package com.vivekbalachandra.android.client.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vivekbalachandra.android.client.Model.TaskModel;

import java.util.ArrayList;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskItemHolder> {

    public TaskListAdapter(Context context, ArrayList<TaskModel> dataset)
    {

    }
    @NonNull
    @Override
    public TaskItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TaskItemHolder extends RecyclerView.ViewHolder{

        TextView Head;
        Button detail;


        public TaskItemHolder(View itemView) {
            super(itemView);
        }
    }
}
