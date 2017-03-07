package com.livroandroid.android_project_livro.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livroandroid.android_project_livro.R;
import com.livroandroid.android_project_livro.activity.CarroActivity;
import com.livroandroid.android_project_livro.adapter.CarroAdapter;
import com.livroandroid.android_project_livro.domain.Carro;
import com.livroandroid.android_project_livro.domain.CarroService;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.List;

public class CarrosFragment extends BaseFragment {
    private int tipo;
    protected RecyclerView recyclerView;
    private List<Carro> carros;

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

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskCarros();
    }

    private void taskCarros() {
        // Busca os carros: dispara task
        startTask("carros", new GetCarrosTask(), R.id.progress);
    }

    // Task para buscar os carros
    private class GetCarrosTask implements TaskListener<List<Carro>> {

        @Override
        public List<Carro> execute() throws Exception {
            // Busca os carros em backgroud (Thread)
            return CarroService.getCarros(getContext(), tipo);
        }

        @Override
        public void updateView(List<Carro> carros) {
            if (carros != null) {
                // Salva a lista de carros no atributo de classe
                CarrosFragment.this.carros = carros;
                // Atualiza a view na UI Thread
                recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));
            }
        }

        @Override
        public void onError(Exception exception) {
            // Qualquer exceção lançada no método execute vai criar aqui.
            alert("Ocorreu algum erro ao buscar os dados.");
        }

        @Override
        public void onCancelled(String cod) {

        }
    }

    private CarroAdapter.CarroOnClickListener onClickCarro() {
        return new CarroAdapter.CarroOnClickListener() {
            @Override
            public void onClickCarro(View view, int idx) {
                Carro c = carros.get(idx);
                Intent intent = new Intent(getContext(), CarroActivity.class);
                intent.putExtra("carro", Parcels.wrap(c));
                startActivity(intent);
            }
        };
    }
}