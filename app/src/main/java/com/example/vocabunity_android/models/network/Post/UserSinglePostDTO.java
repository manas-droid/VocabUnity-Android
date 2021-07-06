package com.example.vocabunity_android.models.network.Post;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserSinglePostDTO {

    @SerializedName("postid")
    private Integer postId;
    private String language , word;

    @SerializedName("example")
    private List<ExampleDTO> examples;


    public UserSinglePostDTO(Integer postId, String language, String word, List<ExampleDTO> examples) {
        this.postId = postId;
        this.language = language;
        this.word = word;
        this.examples = examples;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<ExampleDTO> getExamples() {
        return examples;
    }

    public void setExamples(List<ExampleDTO> examples) {
        this.examples = examples;
    }
}


/*
  {
    "postid": current.postid ,
    "language":current.language ,
    "example": [{example: current.example , exampleid : current.exampleid }] ,
    "word" : current.word
  }
 */