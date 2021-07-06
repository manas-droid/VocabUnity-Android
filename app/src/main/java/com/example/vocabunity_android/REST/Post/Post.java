package com.example.vocabunity_android.REST.Post;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.vocabunity_android.REST.GetDataInstance;
import com.example.vocabunity_android.REST.RetrofitInstance;
import com.example.vocabunity_android.models.network.Post.AddToPostDTO;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Post {

    private final GetDataInstance getDataInstance;
    private static final String TAG = "Post";
    public Post(){
        getDataInstance = RetrofitInstance.getInstance().create(GetDataInstance.class);
    }

    public MutableLiveData<AddToPostDTO> addPost( String userId, String word , String language , String example){

        MutableLiveData<AddToPostDTO> addToPostMutableLiveData = new MutableLiveData<>();
        addToPostMutableLiveData.setValue(null);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userid", userId);
        jsonObject.addProperty("word", word);
        jsonObject.addProperty("language", language);
        jsonObject.addProperty("example", example);

        Call<AddToPostDTO> post  =  getDataInstance.addUserPost(jsonObject);

        post.enqueue(new Callback<AddToPostDTO>() {
            @Override
            public void onResponse(Call<AddToPostDTO> call, Response<AddToPostDTO> response) {
                if(response.isSuccessful()){
                    AddToPostDTO responsePost = response.body();
                    addToPostMutableLiveData.postValue(responsePost);

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
            public void onFailure(Call<AddToPostDTO> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        return addToPostMutableLiveData;
    }

}
