package com.livroandroid.android_project_livro.activity;

import android.os.Bundle;

import com.livroandroid.android_project_livro.R;
import com.livroandroid.android_project_livro.fragments.CarrosFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTooBar();

        setupNavDrawer();

        // Inicializa o layout principal com o fragment dos carros
        replaceFragment(new CarrosFragment());
    }
}
