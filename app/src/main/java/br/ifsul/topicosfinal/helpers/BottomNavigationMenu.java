package br.ifsul.topicosfinal.helpers;

import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import br.ifsul.topicosfinal.ConfigActivity;
import br.ifsul.topicosfinal.MainActivity;
import br.ifsul.topicosfinal.R;
import br.ifsul.topicosfinal.helpers.exceptions.BottomNavigationMenuException;

public class BottomNavigationMenu {

    private static Class<?> onItemSelected(MenuItem item) throws BottomNavigationMenuException {
        int id = item.getItemId();

        if(id == R.id.nav_partidos)
            return MainActivity.class;
        else if(id == R.id.nav_config)
            return ConfigActivity.class;
        else
            throw new BottomNavigationMenuException("ERROR");
    }

    public static boolean listener(AppCompatActivity activity, MenuItem item) {
        try {
            Class<?> newActivity = BottomNavigationMenu.onItemSelected(item);
            activity.startActivity(new Intent(activity.getApplicationContext(), newActivity));
            activity.finish();
            return true;
        } catch (BottomNavigationMenuException e) {
            return false;
        }
    }

}
