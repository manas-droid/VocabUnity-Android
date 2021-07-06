package com.example.vocabunity_android.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabunity_android.R;
import com.example.vocabunity_android.models.network.Post.ExampleDTO;

import java.util.List;

public class UserExampleAdapter extends RecyclerView.Adapter<UserExampleAdapter.UserExampleViewHolder>{
    List<ExampleDTO> exampleList;

    public UserExampleAdapter(List<ExampleDTO> exampleList) {
        this.exampleList = exampleList;
    }

    @NonNull
    @Override
    public UserExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example, parent, false);
        return new UserExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserExampleViewHolder holder, int position) {
        holder.tv_example.setText(exampleList.get(position).getExample());
    }

    @Override
    public int getItemCount() {
        return this.exampleList.size();
    }


    public static class UserExampleViewHolder extends RecyclerView.ViewHolder{
        TextView tv_example;
        public UserExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_example = itemView.findViewById(R.id.exampleSentence);
        }
    }
}
