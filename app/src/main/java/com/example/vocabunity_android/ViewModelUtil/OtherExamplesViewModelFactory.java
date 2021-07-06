package com.example.vocabunity_android.ViewModelUtil;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class OtherExamplesViewModelFactory implements ViewModelProvider.Factory {
    String word;
    public OtherExamplesViewModelFactory(String word) {
        this.word = word;
    }



    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new OtherExamplesActivityViewModel(this.word);
    }
}
