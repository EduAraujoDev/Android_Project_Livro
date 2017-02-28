package com.livroandroid.android_project_livro.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.livroandroid.android_project_livro.R;

public class CarrosFragment extends BaseFragment {
    private int tipo;

    // Método para instanciar esse fragment pelo tipo
    public static CarrosFragment newInstance(int tipo) {
        Bundle args = new Bundle();
        args.putInt("tipo", tipo);
        CarrosFragment f = new CarrosFragment();
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            // Lê o tipo dos argumentos
            this.tipo = getArguments().getInt("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carros, container, false);

        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("Carros " + getString(tipo));

        return view;
    }
}