package com.sarigulsoftware.gezenti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout usernameTIL,emailTIL,passwordTIL,passwordConfirmTIL;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    Query query = reference.orderByChild("username");
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usernameTIL = findViewById(R.id.usernameTextInputSignUp);
        emailTIL = findViewById(R.id.emailTextInputSignUp);
        passwordTIL = findViewById(R.id.passwordTextInputSignUp);
        passwordConfirmTIL = findViewById(R.id.passwordTextInputConfirmSignUp);
    }

    public void signUpMethod(View view) {
        String username = usernameTIL.getEditText().getText().toString();
        String email = emailTIL.getEditText().getText().toString();
        String password = passwordTIL.getEditText().getText().toString();
        String passwordConfirm = passwordConfirmTIL.getEditText().getText().toString();

        if(Utility.isEmpty(username,email,password,passwordConfirm)){
            Toast.makeText(getApplicationContext(),"Lütfen tüm alanları doldurunuz.",Toast.LENGTH_LONG).show();
        }else if((!password.equals(passwordConfirm)) || password.length()<8){
            Toast.makeText(getApplicationContext(),"Parolalar minimum 8 karakter ve aynı olmalıdır.",Toast.LENGTH_LONG).show();
        }else{
            query.equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        Toast.makeText(getApplicationContext(), "Bu kullanıcı adı daha önce alınmış!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    String uid = auth.getCurrentUser().getUid();
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("userEmail", email);
                                    map.put("username",username);
                                    map.put("userUid", uid);

                                    reference.child(uid).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(getApplicationContext(), "Kayıt Başarılı!", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                                                finish();
                                            }else{
                                                Toast.makeText(getApplicationContext(),"Kayıt sırasında hata oluştu!",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }else{
                                    Toast.makeText(getApplicationContext(),"Kayıt oluşturulurken hata oluştu!",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),"İşlem kesildi!",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}