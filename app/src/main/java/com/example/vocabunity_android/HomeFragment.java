package com.example.vocabunity_android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabunity_android.Adapters.UserPostAdapter;
import com.example.vocabunity_android.ViewModelUtil.HomeFragmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeFragmentViewModel homeFragmentViewModel;
    private static final String TAG = "HomeFragment";
    private FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeFragmentViewModel = new ViewModelProvider(getActivity(),
                ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication()))
                .get(HomeFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container,false);
        final ProgressBar progressBar = view.findViewById(R.id.progressBar);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        floatingActionButton = view.findViewById(R.id.addPost);
        homeFragmentViewModel.getUserPosts().observe(getViewLifecycleOwner(), userPosts->{
            if(userPosts!=null){
                Log.d(TAG, "onCreateView: user post available");
                progressBar.setVisibility(View.GONE);
                UserPostAdapter userPostAdapter = new UserPostAdapter(userPosts.getPosts());
                recyclerView.setAdapter(userPostAdapter);
            }else{
                progressBar.setVisibility(View.VISIBLE);
                Log.d(TAG, "onCreateView: user posts have not been initialised");
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        floatingActionButton.setOnClickListener(view->
        {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(((ViewGroup)getView().getParent()).getId(),
                    new AddToPostFragment()).commit();

        });

    }

}
