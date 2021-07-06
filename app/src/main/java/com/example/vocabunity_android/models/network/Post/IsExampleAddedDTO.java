package com.example.vocabunity_android.models.network.Post;

import com.google.gson.annotations.SerializedName;

public class IsExampleAddedDTO {

   private Boolean example , ok;

    public IsExampleAddedDTO(Boolean example, Boolean ok) {
        this.example = example;
        this.ok = ok;
    }

    public Boolean getExample() {
        return example;
    }

    public void setExample(Boolean example) {
        this.example = example;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
