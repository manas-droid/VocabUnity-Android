package com.example.vocabunity_android.models.network.Post;

public class AddToPostDTO {
    String post;
    Boolean ok;

    public AddToPostDTO(String post, Boolean ok) {
        this.post = post;
        this.ok = ok;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
