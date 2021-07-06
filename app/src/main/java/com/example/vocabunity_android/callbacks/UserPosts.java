package com.example.vocabunity_android.callbacks;

import com.example.vocabunity_android.models.network.Post.PostsDTO;
import com.example.vocabunity_android.models.network.Post.UserSinglePostDTO;

public interface UserPosts {
    void setUserPosts(PostsDTO<UserSinglePostDTO> userPosts);
}
