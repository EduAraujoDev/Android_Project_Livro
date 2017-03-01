package com.livroandroid.android_project_livro.activity;

import android.os.Bundle;

import com.livroandroid.android_project_livro.R;
import com.livroandroid.android_project_livro.domain.Carro;
import com.livroandroid.android_project_livro.fragments.CarroFragment;

import org.parceler.Parcels;

public class CarroActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        // Configura a Toolbar como a action bar
        setUpToolBar();

        // Título da toolbar e botão up navigation
        Carro c = Parcels.unwrap(getIntent().getParcelableExtra("carro"));;
        getSupportActionBar().setTitle(c.nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null) {
            // Cria o fragment com o mesmo Bundle (args) da intent
            CarroFragment frag = new CarroFragment();
            frag.setArguments(getIntent().getExtras());

            // Adiciona o fragment no layout
            getSupportFragmentManager().beginTransaction().add(R.id.CarroFragment,frag).commit();
        }
    }
}