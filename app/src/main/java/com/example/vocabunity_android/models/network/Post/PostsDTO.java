package com.example.vocabunity_android.models.network.Post;

import java.util.List;

public class PostsDTO <PostType>{
    private Boolean ok;
    private List<PostType> posts;

    public PostsDTO(Boolean ok, List<PostType> posts) {
        this.ok = ok;
        this.posts = posts;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<PostType> getPosts() {
        return posts;
    }

    public void setPosts(List<PostType> posts) {
        this.posts = posts;
    }
}


// PostType are of two types : UserSinglePost and OtherExamplesSinglePost
// Difference between the two : OtherExamplesSinglePost has user_name,user_image in it

/*
{
    ok: Boolean,
    posts: List<PostType>,
}

 */