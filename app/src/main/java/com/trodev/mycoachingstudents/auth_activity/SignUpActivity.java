package com.trodev.mycoachingstudents.auth_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trodev.mycoachingstudents.MainActivity;
import com.trodev.mycoachingstudents.R;
import com.trodev.mycoachingstudents.models.User;
import com.trodev.mycoachingstudents.models.UserStatus;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import papaya.in.sendmail.SendMail;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signin;
    private MaterialButton signup;
    private EditText username, emailET, passwordET, numberET;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;
    String Status;
    String usersname, email, password, number, image = String.valueOf(0);

    /*new user registration from trovato*/
    public static final String EMAIL ="trovatoltd@gmail.com";
    public static final String PASSWORD ="hecjigwtioticugv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        /*hide title*/
        getSupportActionBar().hide();

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(this);
        signin = findViewById(R.id.signin);
        signin.setOnClickListener(this);

        /*auth profile from firebase*/
        mAuth = FirebaseAuth.getInstance();

        /*init widget views*/
        signup = findViewById(R.id.signup);
        signup.setOnClickListener(this);

        username = findViewById(R.id.username);
        emailET = findViewById(R.id.emailEt);
        numberET = findViewById(R.id.numberEt);
        passwordET = findViewById(R.id.passwordEt);
        progressBar = findViewById(R.id.progressBar);

        Status = "active";
    }

    // go to SignIn form with clicking
    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        if (itemId == R.id.signin) {
            startActivity(new Intent(SignUpActivity.this, SigninActivity.class));
            Toast.makeText(SignUpActivity.this, "SignIn First", Toast.LENGTH_SHORT).show();
            finish();
        } else if (itemId == R.id.signup) {
            registarUser();

        }

    }

    private void registarUser() {

        usersname = username.getText().toString().trim();
        email = emailET.getText().toString().trim();
        password = passwordET.getText().toString().trim();
        number = numberET.getText().toString().trim();

        if (usersname.isEmpty()) {
            username.setError("Name is required");
            username.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            emailET.setError("E-mail is required");
            emailET.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError("Please provide valid email!");
            emailET.requestFocus();
            return;
        }

        if (password.length() <= 8) {
            passwordET.setError("Minimum password length should be 8 character");
            passwordET.requestFocus();
            return;
        }

        if (number.isEmpty()) {
            numberET.setError("Mobile number is required");
            numberET.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        progressBar.setVisibility(View.VISIBLE);

        SendMail mail = new SendMail(EMAIL, PASSWORD,
                "trodevit@gmail.com",
                "New User Registration",
                "Assalamualaikum Dear Admin !" + "\n"
                        + usersname + "is register our apps" +"\n"
                        + "User Email is " + email);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(usersname,  email, password, number, image);

                            FirebaseDatabase.getInstance().getReference("Registered_User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                                save_to_user_list();
                                                mail.execute();
                                                finish();

                                            } else {

                                                progressBar.setVisibility(View.VISIBLE);
                                                Toast.makeText(SignUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });

                        }
                    }
                });

    }

    private void save_to_user_list() {

        databaseReference = FirebaseDatabase
                .getInstance()
                .getReference("User_Status");

        String status, name, number, pass;

        status = Status;
        name = username.getText().toString().trim();
        number = numberET.getText().toString().trim();
        pass = passwordET.getText().toString().trim();


        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        String date = currentDate.format(calForDate.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(calForTime.getTime());


        /*set data on user_status*/
        UserStatus statusModel = new UserStatus(status, name, number, pass, date, time, FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(statusModel);

/*    String Key = databaseReference.push().getKey();
        if (Key != null) {

            StatusModel statusModel = new StatusModel(Key, status, name, number, user_token, packages, date, time, FirebaseAuth.getInstance().getCurrentUser().getUid());

            *//*these data save on new uid and also user id*//*
         *//*these data save on user id*//*
            databaseReference.child(Key).setValue(statusModel);


            Toast.makeText(this, "user activation successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
        }*/


    }
}