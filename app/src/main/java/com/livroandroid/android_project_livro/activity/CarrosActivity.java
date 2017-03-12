package com.livroandroid.android_project_livro.activity;

import android.os.Bundle;

import com.livroandroid.android_project_livro.R;
import com.livroandroid.android_project_livro.fragments.CarrosFragment;

public class CarrosActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carros);

        setUpToolBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Titulo
        getSupportActionBar().setTitle(getString(getIntent().getIntExtra("tipo", 0)));

        // Acidiona o fragment com o mesmo Bundle (args) da intent
        if (savedInstanceState == null) {
            CarrosFragment fragment = new CarrosFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
        }
    }
}