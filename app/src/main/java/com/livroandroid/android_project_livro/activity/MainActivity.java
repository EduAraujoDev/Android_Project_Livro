package com.livroandroid.android_project_livro.activity;

import android.os.Bundle;

import com.livroandroid.android_project_livro.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTooBar();
    }
}
