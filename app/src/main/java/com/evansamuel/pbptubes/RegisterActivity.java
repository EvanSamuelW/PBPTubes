package com.evansamuel.pbptubes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText email;
    EditText nama;
    EditText alamat;
    EditText telepon;
    EditText username;
    EditText password;
    Button register;
    Button backLogin;
    FirebaseAuth firebaseAuth;
    TextView tvTextAwal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        email = findViewById(R.id.edtEmailRegister);
        password = findViewById(R.id.edtPasswordRegister);
        tvTextAwal = findViewById(R.id.tvTextAwal);
        register = findViewById(R.id.btnRegisterUser);
        nama = findViewById(R.id.edtNama);
        alamat = findViewById(R.id.edtAlamat);
        telepon = findViewById(R.id.edtTelpon);
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
                            if (task.isSuccessful() && Patterns.EMAIL_ADDRESS.matcher((CharSequence) email).matches()) {
                                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, ActivityLogin.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
    private boolean validasi(){
        if(email.getText().toString().equals("")){
            email.setError("Email Harus Diisi");
        }else if(password.getText().toString().equals("")){
            password.setError("Password Harus Diisi");
        }
        return true;
    }
}