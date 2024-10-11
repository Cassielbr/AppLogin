package br.ulbra.applogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText edLogin, edPass;
    Button btLogin, btnVolta;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);
        edLogin = findViewById(R.id.edLogin);
        edPass = findViewById(R.id.edPass);
        btLogin = findViewById(R.id.btnLogin);
        btnVolta = findViewById(R.id.btnVolta);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edLogin.getText().toString();
                String password = edPass.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Usuário não inserido, tente novamente", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Senha não inserida, tente novamente", Toast.LENGTH_SHORT).show();
                } else {
                    String res = db.validarLogin(username, password);
                    if (res.equals("OK")) {
                        Toast.makeText(LoginActivity.this, "Login OK!", Toast.LENGTH_SHORT).show();
                        // Redirecionar para a HomeActivity
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("USERNAME", username); // Passando o nome de usuário
                        startActivity(intent);
                        finish(); // Finaliza a tela de login
                    } else {
                        Toast.makeText(LoginActivity.this, "Login ou Senha incorretos!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Volta para a tela anterior
            }
        });
    }
}
