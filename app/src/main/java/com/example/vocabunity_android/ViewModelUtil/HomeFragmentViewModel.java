package com.example.vocabunity_android.ViewModelUtil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vocabunity_android.REST.Post.Get;
import com.example.vocabunity_android.callbacks.UserPosts;
import com.example.vocabunity_android.models.network.Post.PostsDTO;
import com.example.vocabunity_android.models.network.Post.UserSinglePostDTO;
import com.google.firebase.auth.FirebaseAuth;


public class HomeFragmentViewModel  extends ViewModel  implements UserPosts {
    private final MutableLiveData<PostsDTO<UserSinglePostDTO>> mutableUserPosts;
    private final LiveData<PostsDTO<UserSinglePostDTO>> userPosts;
    private Get get;
    private String uid;
    public HomeFragmentViewModel(){
        mutableUserPosts = new MutableLiveData<>();
        mutableUserPosts.setValue(null);
        userPosts = mutableUserPosts;
        this.setUserId();
        get = new Get();
        get.getYourPost(uid, this);
    }

    private void setUserId(){
        if(uid == null){
            uid = (FirebaseAuth.getInstance().getCurrentUser() != null) ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "";
        }
    }


    public LiveData<PostsDTO<UserSinglePostDTO>> getUserPosts(){
        return userPosts;
    }


    @Override
    public void setUserPosts(PostsDTO<UserSinglePostDTO> userPosts) {
        mutableUserPosts.postValue(userPosts);
    }
}
