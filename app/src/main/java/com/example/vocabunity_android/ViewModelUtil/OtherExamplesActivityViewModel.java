package com.example.vocabunity_android.ViewModelUtil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vocabunity_android.REST.Post.Get;
import com.example.vocabunity_android.callbacks.CheckOtherExamplesPosts;
import com.example.vocabunity_android.models.network.Post.OtherExamplesSinglePostDTO;
import com.example.vocabunity_android.models.network.Post.PostsDTO;
import com.google.firebase.auth.FirebaseAuth;


public class OtherExamplesActivityViewModel extends ViewModel implements CheckOtherExamplesPosts {

    private final LiveData<PostsDTO<OtherExamplesSinglePostDTO>> otherExamples;
    private final MutableLiveData<PostsDTO<OtherExamplesSinglePostDTO>> otherExamplesMutableData;
    private String uid;
    Get get;

    public OtherExamplesActivityViewModel(String word){
        this.setUserId();
        otherExamplesMutableData = new MutableLiveData<>();
        otherExamplesMutableData.setValue(null);
        otherExamples = otherExamplesMutableData;
        get = new Get();
        get.getOtherExamples(this.uid, word , this);
    }

    public LiveData<PostsDTO<OtherExamplesSinglePostDTO>> getOtherExamples(){
        return otherExamples;
    }

    private void setUserId(){
        if(uid == null){
            uid = (FirebaseAuth.getInstance().getCurrentUser() != null) ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "";
        }
    }

    @Override
    public void setOtherExamplesPost(PostsDTO<OtherExamplesSinglePostDTO> posts) {
        otherExamplesMutableData.postValue(posts);
    }

}
