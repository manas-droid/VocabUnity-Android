package com.example.vocabunity_android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vocabunity_android.ViewModelUtil.AddToPostFragmentViewModel;
import com.example.vocabunity_android.ViewModelUtil.OtherExamplesActivityViewModel;
import com.example.vocabunity_android.ViewModelUtil.OtherExamplesViewModelFactory;
import com.google.android.material.textfield.TextInputLayout;

public class AddToPostFragment extends Fragment {

    private static final String TAG = "AddToPostFragment";
    private final String [] languages = {
            "German",
            "Spanish",
            "English",
            "Italian",
            "French"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_to_post, container,false);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.option_language , languages);
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.optionLanguage);
        autoCompleteTextView.setAdapter(arrayAdapter);

        String language = autoCompleteTextView.getEditableText().toString();
        TextInputLayout word = view.findViewById(R.id.word);
        TextInputLayout example = view.findViewById(R.id.example);
        Button submit = view.findViewById(R.id.handleAddPost);

        AddToPostFragmentViewModel addToPostFragmentViewModel =  new ViewModelProvider(getActivity(), ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication()) ).get(AddToPostFragmentViewModel.class);

        submit.setOnClickListener(v-> {
            addToPostFragmentViewModel
                    .getAddPostResponse(word.getEditText().getText().toString(),
                    autoCompleteTextView.getEditableText().toString() ,
                    example.getEditText().getText().toString())
                    .observe(getActivity() , addToPostDTO -> {
                        if(addToPostDTO!=null){
                            if(addToPostDTO.getOk()){
                                Log.d(TAG, "onCreateView: Successfully uploaded the post");
                            }
                        }

                    });
        });

        return view;
    }


}
