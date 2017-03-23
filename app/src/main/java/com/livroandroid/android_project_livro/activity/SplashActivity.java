package com.livroandroid.android_project_livro.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.livroandroid.android_project_livro.R;
import com.livroandroid.android_project_livro.utils.PermissionUtils;

import livroandroid.lib.utils.AlertUtils;

import static java.security.AccessController.getContext;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Lista de permissões necessárias.
        String permissions[] = new String [] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        };

        // Valida lista de permissões.
        boolean ok = PermissionUtils.validate(this, 0, permissions);

        if (ok) {
            // Tudo OK, pode entrar.
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                // Negou a permissão. Mostra alerta e fecha.
                AlertUtils.alert(this, R.string.app_name, R.string.msg_alerta_permissao,R.string.ok, new Runnable() {
                    @Override
                    public void run() {
                        // Negou permissão. Sai do app.
                        finish();
                    }
                });
                return;
            }
        }

        // Permissões concedidas, pode entrar.
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}