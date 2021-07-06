package com.example.vocabunity_android.models.network.Post;

import com.google.gson.annotations.SerializedName;

public class ExampleDTO {

    @SerializedName("exampleid")
    private Integer exampleId;
    private String example;

    public ExampleDTO(Integer exampleId, String example) {
        this.exampleId = exampleId;
        this.example = example;
    }

    public Integer getExampleId() {
        return exampleId;
    }

    public void setExampleId(Integer exampleId) {
        this.exampleId = exampleId;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
