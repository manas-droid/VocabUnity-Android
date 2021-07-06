package com.example.vocabunity_android.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vocabunity_android.R;
import com.example.vocabunity_android.models.network.Post.ExampleDTO;
import com.example.vocabunity_android.models.network.Post.OtherExamplesSinglePostDTO;

import java.util.List;


public class OtherExamplesAdapter extends RecyclerView.Adapter<OtherExamplesAdapter.OtherExamplesViewHolder>  {
    private final List<OtherExamplesSinglePostDTO> otherExamplesList;
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final  ViewHolderResponder viewHolderResponder;
    private static final String TAG = "OtherExamplesAdapter";

    public OtherExamplesAdapter(List<OtherExamplesSinglePostDTO> otherExamplesList , ViewHolderResponder viewHolderResponder) {
        this.otherExamplesList = otherExamplesList;
        this.viewHolderResponder = viewHolderResponder;
    }

    @NonNull
    @Override
    public OtherExamplesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_example_post, parent, false);
        return new OtherExamplesViewHolder(view , this.viewHolderResponder);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherExamplesViewHolder holder, int position) {
        OtherExamplesSinglePostDTO singlePost = otherExamplesList.get(position);

        // this is callback is called in OtherExampleActivity;

        viewHolderResponder.getUpVotesPerPost(holder.thumbsUp, position, singlePost.getPostId());
        holder.upVoteCount.setText(singlePost.getUpVote()+" upvotes");

        holder.username.setText(singlePost.getUsername());
        holder.language.setText("language: "+singlePost.getLanguage());
        holder.word.setText(singlePost.getWord());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
        linearLayoutManager.setInitialPrefetchItemCount(singlePost.getExamples().size());

        OtherExamplesPostExampleAdapter userExampleAdapter = new OtherExamplesPostExampleAdapter(singlePost.getExamples() , singlePost.getPostId() , this.viewHolderResponder);

        holder.exampleAdapter.setLayoutManager(linearLayoutManager);
        holder.exampleAdapter.setAdapter(userExampleAdapter);
        holder.exampleAdapter.setRecycledViewPool(viewPool);

        holder.thumbsUp.setOnClickListener(view->{
            holder.thumbsUp.setImageResource(R.drawable.ic_thumb_up_green);
        });

        Glide.with(holder.itemView.getContext()).load(singlePost.getUserImage()).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return this.otherExamplesList.size();
    }




    public static class OtherExamplesViewHolder extends RecyclerView.ViewHolder{
        private final RecyclerView exampleAdapter;
        private final TextView word,language,username,upVoteCount;
        private final ImageView userImage , thumbsUp;
        public OtherExamplesViewHolder(@NonNull View itemView , ViewHolderResponder viewHolderResponder) {
            super(itemView);
            exampleAdapter = itemView.findViewById(R.id.examples);
            word = itemView.findViewById(R.id.word);
            language = itemView.findViewById(R.id.language);
            userImage = itemView.findViewById(R.id.user_image);
            username = itemView.findViewById(R.id.username);
            thumbsUp = itemView.findViewById(R.id.thumbsUp);
            upVoteCount = itemView.findViewById(R.id.upVoteCount);
        }


    }


    public interface ViewHolderResponder {
        void getUpVotesPerPost(ImageView view, Integer position , Integer postId);
        void addExampleToVocab(ImageView view , ExampleDTO exampleDTO , Integer postId);
        void getIsExampleAdded(ImageView view , Integer exampleId);
    }
}
