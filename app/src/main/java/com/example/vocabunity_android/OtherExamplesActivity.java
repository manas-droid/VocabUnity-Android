package com.example.vocabunity_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.vocabunity_android.Adapters.OtherExamplesAdapter;
import com.example.vocabunity_android.REST.Post.Get;
import com.example.vocabunity_android.ViewModelUtil.OtherExamplesActivityViewModel;
import com.example.vocabunity_android.ViewModelUtil.OtherExamplesViewModelFactory;
import com.example.vocabunity_android.models.network.Post.ExampleDTO;
import com.google.firebase.auth.FirebaseAuth;

public class OtherExamplesActivity extends AppCompatActivity implements OtherExamplesAdapter.ViewHolderResponder {
    private static final String TAG = "OtherExamplesActivity";
    private OtherExamplesActivityViewModel viewModel;
    private final Get get = new Get();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_examples);
        Intent intent = getIntent();
        String word  = intent.getStringExtra("word");
        Log.d(TAG, "onCreate: "+word);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = new ViewModelProvider(this, new OtherExamplesViewModelFactory(word)).get(OtherExamplesActivityViewModel.class);

        viewModel.getOtherExamples().observe(this, otherExamplePost->{
                if(otherExamplePost!=null){
                    progressBar.setVisibility(View.GONE);
                    if(otherExamplePost.getPosts().size() == 0){
                        Log.d(TAG, "onCreate: no examples found");
                    }
                    else{
                        OtherExamplesAdapter otherExamplesAdapter = new OtherExamplesAdapter(otherExamplePost.getPosts() , OtherExamplesActivity.this);
                        recyclerView.setAdapter(otherExamplesAdapter);
                    }
                }
                else{
                    Log.d(TAG, "onCreate: not initialised");
                    progressBar.setVisibility(View.VISIBLE);
                }
        });

    }

    @Override
    public void getUpVotesPerPost(ImageView view, Integer position, Integer postId) {

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            get.checkIfUserUpVotedExample(uid, postId).observe(OtherExamplesActivity.this, upVote->{
                if(upVote!=null) {
                    Log.d(TAG, "getUpVotesPerPost: here");
                    if (upVote.getPost()) {
                        view.setImageResource(R.drawable.ic_thumb_up_green);
                    } else
                        view.setImageResource(R.drawable.ic_thumb_up_gray);
                }
            });
    }


    @Override
    public void addExampleToVocab( ImageView view,ExampleDTO exampleDTO, Integer postId) {
        view.setImageResource(R.drawable.ic_example_added);
        Log.d(TAG, "addExampleToVocab: post id"+postId);
        Log.d(TAG, "addExampleToVocab: example Sentence"+exampleDTO.getExample());
        Log.d(TAG, "addExampleToVocab: example id"+exampleDTO.getExampleId());

    }



    @Override
    public void getIsExampleAdded(ImageView view, Integer exampleId) {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        get.checkIfUserAddedExample(uid, exampleId).observe(OtherExamplesActivity.this, isExampleAddedDTO->{
            if(isExampleAddedDTO != null){
                Log.d(TAG, "getIsExampleAdded: "+isExampleAddedDTO.getExample());
                Log.d(TAG, "getIsExampleAdded: "+isExampleAddedDTO.getOk());

                if(isExampleAddedDTO.getExample()){
                    Log.d(TAG, "getIsExampleAdded: here");
                    view.setImageResource(R.drawable.ic_example_added);
                    view.setClickable(false);
                }
            }
        });

    }

}