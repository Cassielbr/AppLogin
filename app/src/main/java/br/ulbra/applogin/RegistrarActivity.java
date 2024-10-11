package br.ulbra.applogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {
    EditText edNome, edUser, edPas1, edPas2;
    Button btSalvar, btVoltar;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        db = new DBHelper(this);
        edNome = findViewById(R.id.edNome);
        edUser = findViewById(R.id.edUser);
        edPas1 = findViewById(R.id.edPass1);
        edPas2 = findViewById(R.id.edPass2);
        btSalvar = findViewById(R.id.btnSalvar);
        btVoltar = findViewById(R.id.btnVolta);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edUser.getText().toString();
                String pas1 = edPas1.getText().toString();
                String pas2 = edPas2.getText().toString();

                if (userName.isEmpty()) {
                    Toast.makeText(RegistrarActivity.this, "Insira o LOGIN DO USUÁRIO", Toast.LENGTH_SHORT).show();
                } else if (pas1.isEmpty() || pas2.isEmpty()) {
                    Toast.makeText(RegistrarActivity.this, "Insira a SENHA DO USUÁRIO", Toast.LENGTH_SHORT).show();
                } else if (!pas1.equals(pas2)) {
                    Toast.makeText(RegistrarActivity.this, "As senhas não correspondem", Toast.LENGTH_SHORT).show();
                } else {
                    long res = db.criarUtilizador(userName, pas1);
                    if (res > 0) {
                        Toast.makeText(RegistrarActivity.this, "Registro OK", Toast.LENGTH_SHORT).show();
                        finish(); // Volta para a tela anterior
                    } else {
                        Toast.makeText(RegistrarActivity.this, "Erro ao registrar!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Volta para a tela anterior
            }
        });
    }
}
