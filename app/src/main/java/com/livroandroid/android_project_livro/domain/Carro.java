package com.livroandroid.android_project_livro.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Carro implements Parcelable{
    public long id;
    public String tipo;
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;
    public String urlVideo;
    public String latitude;
    public String longitude;

    @Override
    public String toString() {
        return "Carro{" + "nome='" + nome + '\'' + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(tipo);
        dest.writeString(nome);
        dest.writeString(desc);
        dest.writeString(urlFoto);
        dest.writeString(urlInfo);
        dest.writeString(urlVideo);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }

    public void readFromParcel(Parcel parcel) {
        // Lê os dados na mesma ordem em que foram escritos
        this.id = parcel.readLong();
        this.tipo = parcel.readString();
        this.nome = parcel.readString();
        this.desc = parcel.readString();
        this.urlFoto = parcel.readString();
        this.urlInfo = parcel.readString();
        this.urlVideo = parcel.readString();
        this.latitude = parcel.readString();
        this.longitude = parcel.readString();
    }

    public static final Creator<Carro> CREATOR = new Creator<Carro>() {
        @Override
        public Carro createFromParcel(Parcel p) {
            Carro c = new Carro();
            c.readFromParcel(p);
            return c;
        }

        @Override
        public Carro[] newArray(int size) {
            return new Carro[size];
        }
    };




}