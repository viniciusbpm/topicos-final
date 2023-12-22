package br.ifsul.topicosfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import br.ifsul.topicosfinal.helpers.Authentication;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(!Authentication.checkIfAlreadyLoggedInAndRedirectToContentIfTrue(this)) {
            TextInputEditText email = findViewById(R.id.input_email_login);

            TextInputEditText senha = findViewById(R.id.input_senha_login);

            Button loginButton = findViewById(R.id.login_botao);
            loginButton.setOnClickListener(view -> {
                Authentication.login(this, email.getText().toString(), senha.getText().toString());
            });

            Button registrar = findViewById(R.id.pagina_cadastro_botao);
            registrar.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(intent);
                finish();
            });
        }

    }

}