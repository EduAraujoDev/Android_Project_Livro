package com.livroandroid.android_project_livro.activity.prefs;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.livroandroid.android_project_livro.R;
import com.livroandroid.android_project_livro.activity.BaseActivity;

public class ConfiguracoesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        setUpToolBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Adiciona o fragment de configurações
        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, new PrefsFragment());
            ft.commit();
        }
    }

    // Fragment que carrega o layout com as configurações
    public static class PrefsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Carrega as configurações
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}