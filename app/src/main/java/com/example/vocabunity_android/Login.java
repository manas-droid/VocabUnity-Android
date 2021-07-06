package com.example.vocabunity_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextInputLayout userEmail , userPassword;
    Button btnLogin;
    private static final String TAG = "Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.signIn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnLogin.setOnClickListener(this::handleLogin);
    }

    public void handleLogin(View view){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String email = userEmail.getEditText().getText().toString();
        String password = userPassword.getEditText().getText().toString();

         Task <AuthResult> authResultTask = firebaseAuth.signInWithEmailAndPassword(email,password);



         authResultTask.addOnCompleteListener(this , task -> {
             if(task.isSuccessful()){
                 runOnUiThread(()->{
                     Intent intent = new Intent(Login.this , Dashboard.class);
                     startActivity(intent);
                     finish();
                 });
             }else{
                 Log.d(TAG, "handleLogin: failed ");
             }
         });



    }
}