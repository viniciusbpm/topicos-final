package br.ifsul.topicosfinal;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.ifsul.topicosfinal.helpers.BottomNavigationMenu;
import br.ifsul.topicosfinal.helpers.Keys;
import br.ifsul.topicosfinal.helpers.api.APICaller;
import br.ifsul.topicosfinal.helpers.api.CallbackData;
import br.ifsul.topicosfinal.models.Deputado;

public class DeputadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputado);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        TextView nome = findViewById(R.id.nome_deputado);
        TextView siglaPartido = findViewById(R.id.nome_partido_deputado);
        TextView siglaUf = findViewById(R.id.uf_deputado);
        TextView email = findViewById(R.id.email_deputado);

        final int idDeputado = getIntent().getIntExtra(Keys.DEPUTADO_ID, 0);
        Deputado deputado = new Deputado();

        APICaller apiCaller = new APICaller();
        apiCaller.getDeputado(idDeputado, new CallbackData<>() {
            @Override
            public void onSuccess(Deputado data) {
                deputado.set(data);
                nome.setText("Nome: " + deputado.getUltimoStatus().getNome());
                siglaPartido.setText("Partido: " + deputado.getUltimoStatus().getSiglaPartido());
                siglaUf.setText("UF: " + deputado.getUltimoStatus().getSiglaUf());
                email.setText("E-mail: " + deputado.getUltimoStatus().getEmail());
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
    }
}