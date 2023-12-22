package br.ifsul.topicosfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.List;

import br.ifsul.topicosfinal.helpers.BottomNavigationMenu;
import br.ifsul.topicosfinal.helpers.Keys;
import br.ifsul.topicosfinal.helpers.api.APICaller;
import br.ifsul.topicosfinal.helpers.api.CallbackData;
import br.ifsul.topicosfinal.models.Deputado;

public class DeputadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputados);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        ListView deputados = findViewById(R.id.deputados_list);
        ArrayAdapter<Deputado> deputadosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        deputados.setAdapter(deputadosAdapter);

        final int partidoId = getIntent().getIntExtra(Keys.PARTIDO_ID, 0);

        deputados.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), DeputadoActivity.class);
            intent.putExtra(Keys.DEPUTADO_ID, deputadosAdapter.getItem(i).getId());
            intent.putExtra(Keys.PARTIDO_ID, partidoId);
            startActivity(intent);
            finish();
        });

        APICaller apiCaller = new APICaller();
        apiCaller.getDeputadosByPartido(partidoId, new CallbackData<>() {
            @Override
            public void onSuccess(List<Deputado> data) {
                deputadosAdapter.clear();
                deputadosAdapter.addAll(data);
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