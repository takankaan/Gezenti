package com.sarigulsoftware.gezenti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private TextInputLayout emailTIL,passwordTIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTIL = findViewById(R.id.emailTextInputLogin);
        passwordTIL = findViewById(R.id.passwordTextInputLogin);
        if(auth.getCurrentUser() != null)
        {
            Intent explorePage = new Intent(LoginActivity.this, ExploreActivity.class);
            startActivity(explorePage);
            finish();
        }
    }

    public void toSignUpMethod(View view) {
        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }
    public void signInMethod(View view) {
        String email = emailTIL.getEditText().getText().toString();
        String password = passwordTIL.getEditText().getText().toString();
        if(!Utility.isEmpty(email,password)) {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(LoginActivity.this, "GİRİŞ BAŞARILI!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, ExploreActivity.class));
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }else
            Toast.makeText(LoginActivity.this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_LONG).show();
    }
}