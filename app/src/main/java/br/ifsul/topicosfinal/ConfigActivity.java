package br.ifsul.topicosfinal;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.ifsul.topicosfinal.helpers.Authentication;
import br.ifsul.topicosfinal.helpers.BottomNavigationMenu;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setSelectedItemId(R.id.nav_config);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        Button logout = findViewById(R.id.logout_botao);
        logout.setOnClickListener(view -> Authentication.logout(this));

    }
}