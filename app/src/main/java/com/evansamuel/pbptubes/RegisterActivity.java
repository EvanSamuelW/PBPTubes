package com.evansamuel.pbptubes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    public static final String TAG1 = "TAG";
    TextInputEditText email,nama,alamat,telepon,username,password;
    Button register;
    Button backLogin;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    String userID;
    AppPreferencesManager preferencesManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferencesManager = new AppPreferencesManager(this);
        if (preferencesManager.getDarkModeState()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        email = findViewById(R.id.edtEmailRegister);
        password = findViewById(R.id.edtPasswordRegister);
        register = findViewById(R.id.btnRegisterUser);
        nama = findViewById(R.id.edtName);
        alamat = findViewById(R.id.edtAddress);
        telepon = findViewById(R.id.edtHand);
        username = findViewById(R.id.edtUsername);
        backLogin = findViewById(R.id.buttonBackLogin);

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, ActivityLogin.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Fusername = username.getText().toString();
                final String Fnama = nama.getText().toString();
                final String Ftelp = telepon.getText().toString();
                final String Falamat = alamat.getText().toString();
                final String Femail = email.getText().toString().trim();


                if (nama.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(alamat.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (telepon.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Telepon tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (username.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Email Invalid", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Password Invalid", Toast.LENGTH_SHORT).show();
                } else if (!(email.getText().toString().isEmpty() && password.getText().toString().isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                userID = firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("users").document(userID);
                                Map<String,Object> user = new HashMap<>();
                                user.put("fName", Fnama);
                                user.put("alamat", Falamat);
                                user.put("email", Femail);
                                user.put("telp", Ftelp);
                                user.put("username", Fusername);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess : user Profile is created for"+ userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure : "+ e.toString());
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(),ActivityLogin.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}