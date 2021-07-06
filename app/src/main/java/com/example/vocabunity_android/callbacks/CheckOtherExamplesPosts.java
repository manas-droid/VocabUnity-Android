package com.example.vocabunity_android.callbacks;

import com.example.vocabunity_android.models.network.Post.OtherExamplesSinglePostDTO;
import com.example.vocabunity_android.models.network.Post.PostsDTO;

public interface CheckOtherExamplesPosts {
    void setOtherExamplesPost(PostsDTO<OtherExamplesSinglePostDTO> posts);
}
