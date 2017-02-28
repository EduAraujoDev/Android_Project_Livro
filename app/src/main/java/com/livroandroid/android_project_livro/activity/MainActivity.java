package com.livroandroid.android_project_livro.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.livroandroid.android_project_livro.R;
import com.livroandroid.android_project_livro.fragments.AboutDialog;
import com.livroandroid.android_project_livro.fragments.CarrosFragment;
import com.livroandroid.android_project_livro.fragments.CarrosTabFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTooBar();

        setupNavDrawer();

        // Inicializa o layout principal com o fragment dos carros
        replaceFragment(new CarrosTabFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            AboutDialog.showAbout(getSupportFragmentManager());
        }

        return super.onOptionsItemSelected(item);
    }
}
