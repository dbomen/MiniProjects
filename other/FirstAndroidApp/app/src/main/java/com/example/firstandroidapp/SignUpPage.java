package com.example.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpPage extends AppCompatActivity {

    EditText userInputNameSignUp;
    Button mainSignUpButton;
    Button signUpLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userInputNameSignUp = (EditText) findViewById(R.id.userInputNameSignUp);
        mainSignUpButton = (Button) findViewById(R.id.mainSingUpButton);
        signUpLoginButton = (Button) findViewById(R.id.signUpLoginButton);
    }

    public void createAccount(View v) { // onClick listener for creating account

        String name = userInputNameSignUp.getText().toString();

        // we check the name length constraint
        if (name.length() < 3) {

            HelperFunctions.showToast(SignUpPage.this, "NAME HAS TO BE AT LEASE 3 CHARACTERS LONG!");
            return;
        }

        // we try to create the account
        User newUser;
        try {

            // create user object
            newUser = new User(-1, name, 0);

            // insert into database
            DataBaseHelper db = new DataBaseHelper(SignUpPage.this);
            boolean createdStatus = db.addUser(newUser);
            if (!createdStatus)  throw new Exception(); // if it did not create, we throw error

            // show success toast
            HelperFunctions.showToast(SignUpPage.this, "CREATED SUCCESSFULLY");

            // forward to login page
            goToLoginPage(v);

        } catch (Exception e) {

            HelperFunctions.showToast(SignUpPage.this, "ERROR WHILE CREATING ACCOUNT");
        }
    }

    public void goToLoginPage(View v) { // redirects to the login page

        Intent i = new Intent(this, LoginPage.class);
        startActivity(i);
        finish();
    }
}