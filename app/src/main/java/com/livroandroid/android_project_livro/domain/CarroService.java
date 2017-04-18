package com.livroandroid.android_project_livro.domain;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.livroandroid.android_project_livro.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import livroandroid.lib.utils.FileUtils;
import livroandroid.lib.utils.HttpHelper;
import livroandroid.lib.utils.IOUtils;

public class CarroService {
    private static final boolean LOG_ON = true;
    private static final String TAG = "CarroService";

    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";

    public static List<Carro> getCarros(Context context, int tipo, boolean refresh) throws IOException {
        List<Carro> carros = !refresh ? getCarrosFromBanco(context, tipo) : null;

        if (carros != null && carros.size() > 0) {
            // Econtrou o arquivo
            return carros;
        }

        // Se não encontrar, busca no web service
        carros = getCarrosFromWebService(context, tipo);

        return carros;
    }

    private static List<Carro> getCarrosFromBanco(Context context, int tipo) throws IOException{
        CarroDB db = new CarroDB(context);

        try {
            String tipoString = getTipo(tipo);
            List<Carro> carros = db.findAllByTipo(tipoString);
            Log.d(TAG, "Retornando " + carros.size() + " carros [" + tipoString + "] do banco");
            return carros;
        } finally {
            db.close();
        }
    }

    private static List<Carro> getCarrosFromWebService(Context context, int tipo) throws IOException {
        String tipoString = getTipo(tipo);
        String url = URL.replace("{tipo}", tipoString);

        Log.d(TAG, "URL: " + url);

        HttpHelper http = new HttpHelper();

        String json = http.doGet(url);
        List<Carro> carros = parserJSON(context, json);

        salvarCarros(context, tipo, carros);
        return carros;
    }

    // Salva os carros no banco de dados
    private static void salvarCarros(Context context, int tipo, List<Carro> carros) {
        String tipoString = getTipo(tipo);
        CarroDB db = new CarroDB(context);

        try {
            // Deleta os carros antigos pelo tipo para limpar o banco
            db.deleteCarrosByTipo(tipoString);

            // Salva todos os carros
            for (Carro c : carros) {
                c.tipo = tipoString;
                Log.d(TAG,"Salvando o carro " + c.nome);
                db.save(c);
            }
        } finally {
            db.close();
        }
    }

    private static List<Carro> getCarrosFromArquivo(Context context, int tipo) {
        String tipoString = getTipo(tipo);
        String fileName = String.format("carros_%s.json",tipoString);
        List<Carro> carros = null;

        Log.d(TAG,"Abrindo arquivo: " + fileName);

        try {
            // Lê o arquivo da memória interna
            String json = FileUtils.readFile(context,fileName,"UTF-8");
            if(json == null) {
                Log.d(TAG,"Arquivo "+fileName+" não encontrado.");
                return null;
            }

            carros = parserJSON(context, json);
            Log.d(TAG,"Retornando carros do arquivo "+fileName+".");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return carros;
    }

    private static void salvarArquivoMemoriaInterna(Context context, String url, String json) {
        String fileName = url.substring(url.lastIndexOf("/")+1);
        File file = FileUtils.getFile(context, fileName);
        IOUtils.writeString(file, json);
        Log.d(TAG, "Arquivo salvo: " + file);
    }

    private static String getTipo(int tipo) {
        if (tipo == R.string.classicos) {
            return "classicos";
        } else if (tipo == R.string.esportivos) {
            return "esportivos";
        }
        return "luxo";
    }

    // Faz o parser do JSON e cria a lista de carros
    private static List<Carro> parserJSON(Context context, String json) throws IOException {
        List<Carro> carros = new ArrayList<Carro>();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject obj = root.getJSONObject("carros");
            JSONArray jsonCarros = obj.getJSONArray("carro");
            // Insere cada carro na lista
            for (int i = 0; i < jsonCarros.length(); i++) {
                JSONObject jsonCarro = jsonCarros.getJSONObject(i);
                Carro c = new Carro();
                // Lê as informações de cada carro
                c.nome = jsonCarro.optString("nome");
                c.desc = jsonCarro.optString("desc");
                c.urlFoto = jsonCarro.optString("url_foto");
                c.urlInfo = jsonCarro.optString("url_info");
                c.urlVideo = jsonCarro.optString("url_video");
                c.latitude = jsonCarro.optString("latitude");
                c.longitude = jsonCarro.optString("longitude");
                if (LOG_ON) {
                    Log.d(TAG, "Carro " + c.nome + " > " + c.urlFoto);
                }
                carros.add(c);
            }
            if (LOG_ON) {
                Log.d(TAG, carros.size() + " encontrados.");
            }
        } catch (JSONException e) {
            throw new IOException(e.getMessage(), e);
        }
        return carros;
    }
}