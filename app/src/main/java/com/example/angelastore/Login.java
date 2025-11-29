package com.example.angelastore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    private Button btnGoCadastro;
    private Button btnSenha; // Não vai ser usado
    private Button btnEntrar;
    private Button btnVoltar;
    private TextInputEditText etEmail;
    private TextInputEditText etSenha;
    private TextView errorMessageLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnVoltar = findViewById(R.id.btnVoltar);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnGoCadastro = findViewById(R.id.btnGoCadastro);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        errorMessageLogin = findViewById(R.id.loginErrorMessage);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
                  public void onClick(View v){
                onBackPressed();
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logarUsuario();
            }
        });
        btnGoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String emailIntent = intent.getStringExtra("EmailUserRecent");
        String senhaIntent = intent.getStringExtra("SenhaUserRecent");

        if(emailIntent != null && senhaIntent != null){
            etEmail.setText(emailIntent);
            etSenha.setText(senhaIntent);
        }
    }
    private void logarUsuario() {
        String emailStr = etEmail.getText().toString().trim();
        String senhaStr = etSenha.getText().toString().trim();

        if (emailStr.isEmpty()) {
            errorMessageLogin.setText("Email vazio my friend");
            return;
        }
        if (senhaStr.isEmpty()){
            errorMessageLogin.setText("Senha vazia my friend");
            return;
        }
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
    }
}