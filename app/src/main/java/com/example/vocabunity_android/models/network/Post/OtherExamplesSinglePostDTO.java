package com.example.vocabunity_android.models.network.Post;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OtherExamplesSinglePostDTO {
    @SerializedName("postid")
    private Integer postId;

    @SerializedName("example")
    private List<ExampleDTO> examples;

    @SerializedName("upvote")
    private Integer upVote;

    @SerializedName("userimage")
    private String userImage;


    private String language;
    private String word;
    private String username;

    public OtherExamplesSinglePostDTO(Integer postId, List<ExampleDTO> examples, Integer upVote, String userImage, String language, String word, String username) {
        this.postId = postId;
        this.examples = examples;
        this.upVote = upVote;
        this.userImage = userImage;
        this.language = language;
        this.word = word;
        this.username = username;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public List<ExampleDTO> getExamples() {
        return examples;
    }

    public void setExamples(List<ExampleDTO> examples) {
        this.examples = examples;
    }

    public Integer getUpVote() {
        return upVote;
    }

    public void setUpVote(Integer upVote) {
        this.upVote = upVote;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}






/*
    Json Structure
    {
        "postid": current.postid ,
        "language":current.language ,
        "example": [{example: current.example , exampleid : current.exampleid }] ,
        "word" : current.word ,
        "username" : current.username ,
        "userimage" : current.userimage ,
        "upvote" : current.upvote
    }
 */