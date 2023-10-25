package com.example.nawalproj.MainPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.nawalproj.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {
    EditText emailEditText;
    EditText passwordEditText;
    Button registerButton;
    EditText fullNameEditText;
    EditText repasswordEditText;
    TextView errorText;
    Switch isadmin;
    EditText adminCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fullNameEditText=findViewById(R.id.etFullName);
        emailEditText=findViewById(R.id.etEmailUp);
        passwordEditText=findViewById(R.id.etPassUp);
        repasswordEditText=findViewById(R.id.etconfirm);
        registerButton=findViewById(R.id.btSignUp);
        isadmin =findViewById(R.id.adminSwitch);
        adminCode =findViewById(R.id.etAdminCode);
        errorText=findViewById(R.id.tverrorText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }
    private void createNewAccount(){
        errorText.setVisibility(View.GONE);
        if(fullNameEditText.getText().toString().equals("")){
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("please enter your full name");
            return;
        }
        if(emailEditText.getText().toString().equals("")){
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("please enter your email");
            return;
        }
        if(passwordEditText.getText().toString().equals("")){
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("please enter your password");
            return;
        }
        if(repasswordEditText.getText().toString().equals("")){
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("please confirm your password");
            return;
        }
        if(!repasswordEditText.getText().toString().equals(passwordEditText.getText().toString())){
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("password doesn't match");
            return;
        }
        if(isadmin.isChecked() && !adminCode.getText().toString().equals("110106")) {
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("admin code is incorrect");
            return;
        }

        final FirebaseAuth mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String name=fullNameEditText.getText().toString();
                            if(isadmin.isChecked()){
                                name = "admin: "+ name;
                            }
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();

                            ((FirebaseUser) user).updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Intent i = new Intent(SignupActivity.this, MainActivity.class);
                                                SignupActivity.this.startActivity(i);
                                            }
                                        }
                                    });
                        } else {

                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            errorText.setVisibility(View.VISIBLE);
                            errorText.setText(task.getException().getMessage());

                        }
                    }
                });
    }
}