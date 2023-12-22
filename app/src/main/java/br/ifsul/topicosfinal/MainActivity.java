package br.ifsul.topicosfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import br.ifsul.topicosfinal.helpers.Authentication;
import br.ifsul.topicosfinal.helpers.BottomNavigationMenu;
import br.ifsul.topicosfinal.helpers.Keys;
import br.ifsul.topicosfinal.helpers.api.APICaller;
import br.ifsul.topicosfinal.helpers.api.CallbackData;
import br.ifsul.topicosfinal.models.Partido;

public class MainActivity extends AppCompatActivity { // Home/Partidos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Authentication.checkIfLoggedInAndRedirectToStartIfNot(this)) {
            BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
            bottomNavigation.setSelectedItemId(R.id.nav_partidos);
            bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

            ListView partido = findViewById(R.id.partidos_list);
            ArrayAdapter<Partido> partidoAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
            partido.setAdapter(partidoAdapter);

            partido.setOnItemClickListener((adapterView, view, i, l) -> {
                Intent intent = new Intent(getApplicationContext(), PartidoActivity.class);
                intent.putExtra(Keys.PARTIDO_ID, partidoAdapter.getItem(i).getId());
                startActivity(intent);
                finish();
            });

            APICaller apiCaller = new APICaller();
            apiCaller.getAllPartidos(new CallbackData<>() {
                @Override
                public void onSuccess(List<Partido> data) {
                    partidoAdapter.clear();
                    partidoAdapter.addAll(data);
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
}