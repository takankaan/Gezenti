package com.sarigulsoftware.gezenti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void toSignUpMethod(View view) {
        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }
}