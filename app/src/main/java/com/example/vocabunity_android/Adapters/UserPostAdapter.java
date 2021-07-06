package com.example.vocabunity_android.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabunity_android.OtherExamplesActivity;
import com.example.vocabunity_android.R;
import com.example.vocabunity_android.models.network.Post.UserSinglePostDTO;

import java.util.List;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.UserPostViewHolder>{
    List<UserSinglePostDTO> userPost;
    private static final String TAG = "UserPostAdapter";
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public UserPostAdapter(List<UserSinglePostDTO> userPost) {
        this.userPost = userPost;
    }

    @NonNull
    @Override
    public UserPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_post, parent, false);
        return new UserPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserPostViewHolder holder, int position) {
        UserSinglePostDTO post = userPost.get(position);
        Log.d(TAG, " UserPost RecyclerView onBindViewHolder: "+position);
        holder.word.setText(post.getWord());
        String lang = "language: "+post.getLanguage();
        holder.language.setText(lang);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
        linearLayoutManager.setInitialPrefetchItemCount(post.getExamples().size());
        UserExampleAdapter userExampleAdapter = new UserExampleAdapter(post.getExamples());
        holder.exampleAdapter.setLayoutManager(linearLayoutManager);
        holder.exampleAdapter.setAdapter(userExampleAdapter);
        holder.exampleAdapter.setRecycledViewPool(viewPool);

        holder.checkForOtherExamples.setOnClickListener(view->{
            Intent intent = new Intent(view.getContext() , OtherExamplesActivity.class);
            intent.putExtra("word", post.getWord());
            view.getContext().startActivity(intent);
        });

    }
    @Override
    public int getItemCount() {
        return this.userPost.size();
    }

    public static class UserPostViewHolder extends RecyclerView.ViewHolder{
        private final RecyclerView exampleAdapter;
        private final TextView word,language;
        private final Button checkForOtherExamples;

        public UserPostViewHolder(@NonNull View itemView) {
            super(itemView);
            exampleAdapter = itemView.findViewById(R.id.examples);
            word = itemView.findViewById(R.id.word);
            language = itemView.findViewById(R.id.language);
//            delete = itemView.findViewById(R.id.delete);
            checkForOtherExamples = itemView.findViewById(R.id.checkOtherExamples);
        }
    }
}
