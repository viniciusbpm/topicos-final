package br.ifsul.topicosfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.ifsul.topicosfinal.helpers.BottomNavigationMenu;
import br.ifsul.topicosfinal.helpers.Keys;
import br.ifsul.topicosfinal.helpers.api.APICaller;
import br.ifsul.topicosfinal.helpers.api.CallbackData;
import br.ifsul.topicosfinal.models.Partido;

public class PartidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        TextView nome = findViewById(R.id.nome_partido);
        TextView siglaPartido = findViewById(R.id.partido_sigla);

        final int partidoId = getIntent().getIntExtra(Keys.PARTIDO_ID, 0);
        Partido partido = new Partido();

        APICaller apiCaller = new APICaller();
        apiCaller.getPartido(partidoId, new CallbackData<>() {
            @Override
            public void onSuccess(Partido data) {
                partido.set(data);
                nome.setText("Nome: " + partido.getNome());
                siglaPartido.setText("Sigla: " + partido.getSigla());
            }

            @Override
            public void onUnsuccess(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });

        Button deputados = findViewById(R.id.listar_deputados_botao);
        deputados.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DeputadosActivity.class);
            intent.putExtra(Keys.PARTIDO_ID, partidoId);
            intent.putExtra(Keys.PARTIDO_SIGLA, partido.getSigla());
            startActivity(intent);
            finish();
        });

    }
}