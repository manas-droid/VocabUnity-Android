package com.example.vocabunity_android.REST.Post;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.vocabunity_android.REST.GetDataInstance;
import com.example.vocabunity_android.REST.RetrofitInstance;
import com.example.vocabunity_android.callbacks.CheckOtherExamplesPosts;
import com.example.vocabunity_android.callbacks.UserPosts;
import com.example.vocabunity_android.models.network.Post.IsExampleAddedDTO;
import com.example.vocabunity_android.models.network.Post.OtherExamplesSinglePostDTO;
import com.example.vocabunity_android.models.network.Post.PostsDTO;
import com.example.vocabunity_android.models.network.Post.UpVoteDTO;
import com.example.vocabunity_android.models.network.Post.UserSinglePostDTO;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Get {
    private static final String TAG = "Get";
    private final GetDataInstance getDataInstance;
    public Get(){
        getDataInstance = RetrofitInstance.getInstance().create(GetDataInstance.class);
    }

    public void getYourPost(String uid , UserPosts userPosts ) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userid", uid);
        Call<PostsDTO<UserSinglePostDTO>> yourPosts = getDataInstance.yourPost(jsonObject);

        yourPosts.enqueue(new Callback<PostsDTO<UserSinglePostDTO>>() {
            @Override
            public void onResponse(@NonNull Call<PostsDTO<UserSinglePostDTO>> call, @NonNull Response<PostsDTO<UserSinglePostDTO>> response) {
                if(response.isSuccessful()){
                    PostsDTO<UserSinglePostDTO> posts = response.body();
                    Log.d(TAG, "onResponse: "+posts.getOk());
                    userPosts.setUserPosts(posts);
                }else{
                    ResponseBody errors = response.errorBody();
                    try {
                        Log.d(TAG, "onResponse: "+errors.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<PostsDTO<UserSinglePostDTO>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }



    public void getOtherExamples(String uid , String word , CheckOtherExamplesPosts checkOtherExamplesPosts){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("word", word);
        jsonObject.addProperty("userid", uid);

        Call<PostsDTO<OtherExamplesSinglePostDTO>> otherExamplePost = getDataInstance.checkOtherExamples(jsonObject);

        otherExamplePost.enqueue(new Callback<PostsDTO<OtherExamplesSinglePostDTO>>() {
            @Override
            public void onResponse(Call<PostsDTO<OtherExamplesSinglePostDTO>> call, Response<PostsDTO<OtherExamplesSinglePostDTO>> response) {

                if(response.isSuccessful()){
                    PostsDTO<OtherExamplesSinglePostDTO> examples = response.body();
                    if(examples.getPosts()!=null){
                        checkOtherExamplesPosts.setOtherExamplesPost(examples);
                    }
                    else{
                        Log.d(TAG, "onResponse: does not have examples");
                    }
                }
                else{
                    ResponseBody errors = response.errorBody();
                    try {
                        Log.d(TAG, "onResponse: "+errors.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<PostsDTO<OtherExamplesSinglePostDTO>> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t);
            }
        });

    }


    public MutableLiveData<UpVoteDTO> checkIfUserUpVotedExample(String uid , Integer postId){
        MutableLiveData<UpVoteDTO> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(null);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userid", uid);
        jsonObject.addProperty("postid", postId);

        Call<UpVoteDTO> upVoteCheck = getDataInstance.hasUserUpVoted(jsonObject);

        upVoteCheck.enqueue(new Callback<UpVoteDTO>() {
            @Override
            public void onResponse(Call<UpVoteDTO> call, Response<UpVoteDTO> response) {
                if(response.isSuccessful()){
                    UpVoteDTO votes = response.body();
                    Log.d(TAG, "onResponse: "+votes.getOk());
                    Log.d(TAG, "onResponse: "+votes.getPost());
                    mutableLiveData.postValue(votes);
                }else {
                    ResponseBody errors = response.errorBody();
                    try {
                        Log.d(TAG, "onResponse: "+errors.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onFailure(Call<UpVoteDTO> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t);
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<IsExampleAddedDTO> checkIfUserAddedExample(String userId , Integer exampleId){
        MutableLiveData<IsExampleAddedDTO> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(null);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userid", userId);
        jsonObject.addProperty("exampleid", exampleId);

        Call<IsExampleAddedDTO> isExampleAdded = getDataInstance.hasUserAddedTheExample(jsonObject);

        isExampleAdded.enqueue(new Callback<IsExampleAddedDTO>() {
            @Override
            public void onResponse(Call<IsExampleAddedDTO> call, Response<IsExampleAddedDTO> response) {

                if(response.isSuccessful()){
                    IsExampleAddedDTO add = response.body();
                    mutableLiveData.postValue(add);
                }
                else {
                    ResponseBody errors = response.errorBody();
                    try {
                        Log.d(TAG, "onResponse: "+errors.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<IsExampleAddedDTO> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t);
            }
        });
        return mutableLiveData;
    }

}
