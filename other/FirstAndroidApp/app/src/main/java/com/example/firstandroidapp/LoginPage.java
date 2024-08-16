package com.example.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginPage extends AppCompatActivity {

    EditText userInputNameLogin;
    Button mainLoginButton;
    Button loginSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userInputNameLogin = (EditText) findViewById(R.id.userInputNameLogin);
        mainLoginButton = (Button) findViewById(R.id.mainLoginButton);
        loginSignUpButton = (Button) findViewById(R.id.loginSignUpButton);
    }

    public void loginAction(View v) { // logs the user in

        String loginUserName = userInputNameLogin.getText().toString();

        // we check if the user exists and log him in
        try {

            // we get the user data from the db
            DataBaseHelper db = new DataBaseHelper(LoginPage.this);
            User user = db.getUserByName(loginUserName);

            if (user == null) { // if user does not exist

                HelperFunctions.showToast(LoginPage.this, String.format("USER [%s] DOES NOT EXIST!", loginUserName));
                throw new Exception();
            }

            // we show success toast
            HelperFunctions.showToast(LoginPage.this, "SUCCESS!");

            // we redirect to main page (we log the user in)
            Intent i = new Intent(LoginPage.this, MainActivity.class);
            i.putExtra("user", user);
            startActivity(i);
            finish();

        } catch (Exception e) {

            HelperFunctions.showToast(LoginPage.this, "TRY AGAIN!");
        }
    }

    public void goToSignUpPage(View v) { // redirects to the signUp page

        Intent i = new Intent(this, SignUpPage.class);
        startActivity(i);
        finish(); // we call the finish method so that we end this activity
                  // Also the user cant go back with the back button
    }
}