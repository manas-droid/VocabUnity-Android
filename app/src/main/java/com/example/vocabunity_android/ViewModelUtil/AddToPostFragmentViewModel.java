package com.example.vocabunity_android.ViewModelUtil;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vocabunity_android.REST.Post.Post;
import com.example.vocabunity_android.models.network.Post.AddToPostDTO;
import com.google.firebase.auth.FirebaseAuth;

public class AddToPostFragmentViewModel extends ViewModel {
    private String word,language,example,userId;

    private final Post post;
    public AddToPostFragmentViewModel() {
        this.setUserId();
        post  = new Post();
    }


    public  MutableLiveData<AddToPostDTO> getAddPostResponse(String word , String language , String example){
        return post.addPost(userId, word, language, example);
    }


    private void setUserId(){
        if(userId == null){
            userId = (FirebaseAuth.getInstance().getCurrentUser() != null) ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "";
        }
    }


}
