package com.example.vocabunity_android.REST;

import com.example.vocabunity_android.models.network.Post.AddToPostDTO;
import com.example.vocabunity_android.models.network.Post.IsExampleAddedDTO;
import com.example.vocabunity_android.models.network.Post.OtherExamplesSinglePostDTO;
import com.example.vocabunity_android.models.network.Post.PostsDTO;
import com.example.vocabunity_android.models.network.Post.UpVoteDTO;
import com.example.vocabunity_android.models.network.Post.UserSinglePostDTO;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetDataInstance {
    @Headers("Content-Type: application/json")
    @POST("api/posts/get")
    Call<PostsDTO<UserSinglePostDTO>> yourPost(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("api/posts/find-another-example")
    Call<PostsDTO<OtherExamplesSinglePostDTO>> checkOtherExamples(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("api/posts/isLiked")
    Call<UpVoteDTO> hasUserUpVoted(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("api/posts/hasExample")
    Call<IsExampleAddedDTO> hasUserAddedTheExample(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("api/posts/add")
    Call<AddToPostDTO> addUserPost(@Body JsonObject jsonObject);


}
