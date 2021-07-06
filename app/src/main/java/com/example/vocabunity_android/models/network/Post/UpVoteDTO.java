package com.example.vocabunity_android.models.network.Post;

public class UpVoteDTO {
    private Boolean post , ok;

    public UpVoteDTO(Boolean post, Boolean ok) {
        this.post = post;
        this.ok = ok;
    }

    public Boolean getPost() {
        return post;
    }

    public void setPost(Boolean post) {
        this.post = post;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}


// used to check if the user upVoted the example