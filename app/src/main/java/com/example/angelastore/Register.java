package com.example.angelastore;

import android.content.Intent;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.graphics.Color;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
    private CheckBox checkBox;
    private TextInputEditText name;
    private TextInputEditText email;
    private TextInputEditText phone;
    private TextInputEditText senha;
    private TextInputEditText confirm;
    private Button btnRegistrar;
    private Button btnGoLogin;
    private Button buttonVoltar;
    private TextView errorMessageRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        checkBox = findViewById(R.id.checkBox);
        name = findViewById(R.id.etNome);
        email = findViewById(R.id.etEmail);
        phone = findViewById(R.id.etTelefone);
        senha = findViewById(R.id.etSenha);
        confirm = findViewById(R.id.etConfirm);
        btnRegistrar = findViewById(R.id.btnCadastrar);
        btnGoLogin = findViewById(R.id.btnGoCadastro);
        buttonVoltar = findViewById(R.id.btnVoltar);
        errorMessageRegister = findViewById(R.id.registerErrorMessage);//

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });
        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
    private void registrarUsuario() {
        String nomeStr = name.getText().toString().trim();
        String emailStr = email.getText().toString().trim();
        String phoneStr = phone.getText().toString().trim();
        String senhaStr = senha.getText().toString().trim();
        String confirmStr = confirm.getText().toString().trim();

        if (nomeStr.isEmpty()) {
            errorMessageRegister.setText("Nome vazio");
            return;
        }
        if (emailStr.isEmpty()) {
            errorMessageRegister.setText("Email vazio");
            return;
        }
        if (phoneStr.isEmpty()) {
            errorMessageRegister.setText("Nome vazio");
            return;
        }
        if (senhaStr.isEmpty()) {
            errorMessageRegister.setText("Senha vazia");
            return;
        }
        if (!senhaStr.equals(confirmStr)) {
            errorMessageRegister.setText("Senhas não estão iguais");
            return;
        }
        if (!checkBox.isChecked()) {
            errorMessageRegister.setText("Você precisa aceitar os termos");
            return;
        }
        User user = new User(nomeStr, emailStr, phoneStr, senhaStr, confirmStr);
        Call<ApiEnvelope<UserRegisterData>> call = RetrofitClient.getInstance().getApi().register(user);

        call.enqueue(new Callback<ApiEnvelope<UserRegisterData>>(){
            @Override
            public void onResponse(Call<ApiEnvelope<UserRegisterData>> call, Response<ApiEnvelope<UserRegisterData>> response) {
                if (response.body() != null) {
                    ApiEnvelope<UserRegisterData> env = response.body();

                    if (env.getType().equals("success")) {
                        errorMessageRegister.setTextColor(Color.parseColor("#2ECC71"));
                        errorMessageRegister.setText(env.getMessage() + ". Confirme o email e depois faça o login");
                    }else{
                        errorMessageRegister.setText(env.getMessage());
                        return;
                    }
                    UserRegisterData data = env.getData();

                    Intent intent = new Intent(Register.this, Login.class);
                    intent.putExtra("EmailUserRecent", emailStr);
                    intent.putExtra("SenhaUserRecent", senhaStr);
                    startActivity(intent);
                    finish();
                }
            }
            public void onFailure (Call<ApiEnvelope<UserRegisterData>> call, Throwable t){
                errorMessageRegister.setText("Falha na conexão" + t.getMessage());
            }
        });
    }
}