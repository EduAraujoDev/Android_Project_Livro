package com.livroandroid.android_project_livro.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.livroandroid.android_project_livro.R;

public class BaseActivity extends AppCompatActivity {
    protected void setUpTooBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
}