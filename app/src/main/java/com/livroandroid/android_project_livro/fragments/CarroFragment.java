package com.livroandroid.android_project_livro.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.livroandroid.android_project_livro.R;
import com.livroandroid.android_project_livro.activity.CarroActivity;
import com.livroandroid.android_project_livro.domain.Carro;
import com.livroandroid.android_project_livro.domain.CarroDB;
import com.livroandroid.android_project_livro.fragments.dialog.EditarCarroDialog;
import com.squareup.picasso.Picasso;

public class CarroFragment extends BaseFragment {
    private Carro carro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carro, container, false);
        carro = getArguments().getParcelable("carro");

        setHasOptionsMenu(true);

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_frag_carro, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            EditarCarroDialog.show(getFragmentManager(), carro, new EditarCarroDialog.Callback(){

                @Override
                public void onCarroUpdated(Carro carro) {
                    toast("Carro [" + carro.nome + "] atualizado");

                    // Salva o carro depois de fechar o dialog
                    CarroDB db = new CarroDB(getContext());
                    db.save(carro);

                    // Atualiza o título com o novo nome
                    CarroActivity a = (CarroActivity) getActivity();
                    a.setTitle(carro.nome);
                }
            });
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            toast("Deletar: " + carro.nome);
            return true;
        } else if (item.getItemId() == R.id.action_share) {
            toast("Compartilhar");
        } else if (item.getItemId() == R.id.action_maps) {
            toast("Mapa");
        } else if (item.getItemId() == R.id.action_video) {
            toast("Vídeo");
        }

        return super.onOptionsItemSelected(item);
    }
}