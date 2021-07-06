package com.example.vocabunity_android.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabunity_android.R;
import com.example.vocabunity_android.models.network.Post.ExampleDTO;

import java.util.List;

public class OtherExamplesPostExampleAdapter extends RecyclerView.Adapter<OtherExamplesPostExampleAdapter.OtherExamplesPostViewHolder> {
    List<ExampleDTO> exampleList;
    Integer postId;
    OtherExamplesAdapter.ViewHolderResponder viewHolderResponder;
    public OtherExamplesPostExampleAdapter(List<ExampleDTO> exampleList , Integer postId , OtherExamplesAdapter.ViewHolderResponder viewHolderResponder) {
        this.exampleList = exampleList;
        this.postId=postId;
        this.viewHolderResponder = viewHolderResponder;
    }

    @NonNull
    @Override
    public OtherExamplesPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_other_examples_example, parent, false);
        return new OtherExamplesPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherExamplesPostViewHolder holder, int position) {

        holder.tv_example.setText(exampleList.get(position).getExample());

        this.viewHolderResponder.getIsExampleAdded(holder.addToVocab, exampleList.get(position).getExampleId());

        holder.addToVocab.setOnClickListener(view->viewHolderResponder.addExampleToVocab(holder.addToVocab , exampleList.get(position), postId));
    }

    @Override
    public int getItemCount() {
        return this.exampleList.size();
    }

    public static class OtherExamplesPostViewHolder extends RecyclerView.ViewHolder{
        TextView tv_example;
        ImageView addToVocab;
        public OtherExamplesPostViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_example = itemView.findViewById(R.id.exampleSentence);
            addToVocab = itemView.findViewById(R.id.addToVocab);
        }
    }
}
