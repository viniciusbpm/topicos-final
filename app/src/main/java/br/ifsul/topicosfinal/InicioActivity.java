package br.ifsul.topicosfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button login = findViewById(R.id.login_botao_inicio);
        login.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

        Button cadastro = findViewById(R.id.cadastrar_botao_inicio);
        cadastro.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
            finish();
        });

    }
}