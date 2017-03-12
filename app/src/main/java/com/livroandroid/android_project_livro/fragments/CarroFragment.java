package com.livroandroid.android_project_livro.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.livroandroid.android_project_livro.R;
import com.livroandroid.android_project_livro.domain.Carro;
import com.squareup.picasso.Picasso;

public class CarroFragment extends BaseFragment {
    private Carro carro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carro, container, false);
        carro = getArguments().getParcelable("carro");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Atualiza a view do fragment com os dados do carro
        setTextString(R.id.tDesc, carro.desc);
        final ImageView imgView = (ImageView) getView().findViewById(R.id.img);
        Picasso.with(getContext()).load(carro.urlFoto).fit().into(imgView);
    }
}