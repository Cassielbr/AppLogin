package br.ulbra.applogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Obtendo o nome de usuário passado da LoginActivity
        String username = getIntent().getStringExtra("USERNAME");

        // Verificando se o username é nulo
        if (username == null) {
            username = "Usuário desconhecido"; // Valor padrão caso não tenha passado
        }

        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Bem-vindo ao App, " + username + "!");

        // Mostrando o usuário logado
        TextView userText = findViewById(R.id.userText);
        userText.setText("Você está logado como: " + username);

        // Configurando o botão de logout
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Fecha a HomeActivity
            }
        });
    }
}
