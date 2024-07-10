package com.trodev.mycoachingstudents.auth_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.trodev.mycoachingstudents.MainActivity;
import com.trodev.mycoachingstudents.R;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signup;
    private EditText emailET, passwordET;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private MaterialButton signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        /*hide action bar*/
        getSupportActionBar().hide();

        /*when user login this account then auto sign in*/
        isUserSigninauto();

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(this);

        signin= findViewById(R.id.signin);
        signin.setOnClickListener(this);

        /*edit_text*/
        emailET = findViewById(R.id.emailEt);
        passwordET = findViewById(R.id.passwordEt);

        progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void isUserSigninauto() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(SigninActivity.this, MainActivity.class));
            Toast.makeText(this, "SignIn Successful", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // got to SignUp form with clicking
    @Override
    public void onClick(View v) {
        int itemId = v.getId();

        if (itemId == R.id.signup) {
            startActivity(new Intent(SigninActivity.this, SignUpActivity.class));
            Toast.makeText(SigninActivity.this, "Registration First", Toast.LENGTH_SHORT).show();
            finish();
        } else if (itemId == R.id.signin) {
            userLogin();
        }
    }
    private void userLogin() {

        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();

        if (email.isEmpty()) {
            emailET.setError("Email is required");
            emailET.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passwordET.setError("Password must be 8 character");
            passwordET.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        //firebase Database
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            startActivity(new Intent(SigninActivity.this, MainActivity.class));
                            Toast.makeText(SigninActivity.this, "SignIn Successful", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            finish();

                        } else {
                            Toast.makeText(SigninActivity.this, "SignIn Unsuccessful", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }
}