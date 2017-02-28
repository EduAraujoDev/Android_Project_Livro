package com.livroandroid.android_project_livro.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.livroandroid.android_project_livro.R;
import com.livroandroid.android_project_livro.fragments.CarrosFragment;
import com.livroandroid.android_project_livro.fragments.SiteLivroFragment;

public class BaseActivity extends livroandroid.lib.activity.BaseActivity {
    protected DrawerLayout drawerLayout;

    protected void setUpTooBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    // Configura o Nav Drawer
    protected void setupNavDrawer() {
        // Drawer Layout
        final ActionBar actionBar = getSupportActionBar();

        // Ícone do menu do nav drawer
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null && drawerLayout != null) {

            // Atualiza a imagem e textos do header
            setNavViewValues(navigationView, R.string.nav_drawer_username, R.string.nav_drawer_email, R.mipmap.ic_launcher);

            // Trata o evento de clique no menu.
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // Seleciona a linha
                            menuItem.setChecked(true);
                            // Fecha o menu
                            drawerLayout.closeDrawers();
                            // Trata o evento do menu
                            onNavDrawerItemSelected(menuItem);
                            return true;
                        }
                    });
        }
    }

    // Atualiza os dados do header do Navigation Viewpublic
    private void setNavViewValues(NavigationView navView, int nome, int email, int img) {
        View headerView = navView.getHeaderView(0);
        TextView tNome = (TextView) headerView.findViewById(R.id.tUserName);
        TextView tEmail = (TextView) headerView.findViewById(R.id.tUserEmail);
        ImageView imgView = (ImageView) headerView.findViewById(R.id.imgUserPhoto);
        tNome.setText(nome);
        tEmail.setText(email);
        imgView.setImageResource(img);
    }

    // Trata o evento do menu lateral
    private void onNavDrawerItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_item_carros_todos:
                replaceFragment(new CarrosFragment());
                break;
            case R.id.nav_item_carros_classicos:
                replaceFragment(new CarrosFragment());
                break;
            case R.id.nav_item_carros_esportivos:
                replaceFragment(new CarrosFragment());
                break;
            case R.id.nav_item_carros_luxo:
                replaceFragment(new CarrosFragment());
                break;
            case R.id.nav_item_site_livro:
                replaceFragment(new SiteLivroFragment());
                break;
            case R.id.nav_item_settings:
                toast("Clicou em configurações");
                break;
        }
    }

    // Adiciona o fragment ao centro da tela
    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "TAG").commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Trata o clique no botão que abre o menu.
                if (drawerLayout != null) {
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    // Abre o menu lateral
    protected void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    // Fecha o menu lateral
    protected void closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}