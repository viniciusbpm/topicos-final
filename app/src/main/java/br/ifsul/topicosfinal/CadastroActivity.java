package br.ifsul.topicosfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import br.ifsul.topicosfinal.helpers.Authentication;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        TextInputEditText email = findViewById(R.id.input_email_cadastro);

        TextInputEditText senha = findViewById(R.id.input_senha_cadastro);

        Button registrar = findViewById(R.id.cadastrar_botao);
        registrar.setOnClickListener(view -> {
                Authentication.register(this, email.getText().toString(), senha.getText().toString());
        });

        Button botaoLogin = findViewById(R.id.pagina_login_botao);
        botaoLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }
}