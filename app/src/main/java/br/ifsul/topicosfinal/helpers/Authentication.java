package br.ifsul.topicosfinal.helpers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import br.ifsul.topicosfinal.LoginActivity;
import br.ifsul.topicosfinal.MainActivity;
import br.ifsul.topicosfinal.InicioActivity;

public class Authentication {

    public static void login(AppCompatActivity originActivity, String email, String password) {
        Context context = originActivity.getApplicationContext();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Log.i("USER", FirebaseAuth.getInstance().getCurrentUser().toString());
                        originActivity.startActivity(new Intent(context, MainActivity.class));
                        originActivity.finish();
                    } else {
                        Exception exception = task.getException();
                        Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public static void logout(AppCompatActivity originActivity) {
        FirebaseAuth.getInstance().signOut();
        originActivity.startActivity(new Intent(originActivity.getApplicationContext(), InicioActivity.class));
        originActivity.finish();
    }

    public static boolean checkIfLoggedInAndRedirectToStartIfNot(AppCompatActivity originActivity) {
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            originActivity.startActivity(new Intent(originActivity.getApplicationContext(), InicioActivity.class));
            originActivity.finish();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkIfAlreadyLoggedInAndRedirectToContentIfTrue(AppCompatActivity originActivity) {
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            return false;
        } else {
            originActivity.startActivity(new Intent(originActivity.getApplicationContext(), MainActivity.class));
            originActivity.finish();
            return true;
        }
    }

    public static void register(AppCompatActivity originActivity, String email, String password) {
            Context context = originActivity.getApplicationContext();

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {
                            Toast.makeText(context, "Usuário cadastrado", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context, LoginActivity.class);
                            originActivity.startActivity(intent);
                            originActivity.finish();
                        } else {
                            Exception exception = task.getException();
                            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

    }

}
