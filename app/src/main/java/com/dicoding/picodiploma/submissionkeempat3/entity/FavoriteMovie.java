package com.dicoding.picodiploma.submissionkeempat3.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteMovie implements Parcelable {

    private int id;
    private String judul;
    private String popularity;
    private String tanggal;
    private String overview;
    private String photo;
    private String rating;
    private String language;

    public FavoriteMovie(int id, String judul, String popularity, String tanggal, String overview, String photo, String rating, String language) {
        this.id = id;
        this.judul = judul;
        this.popularity = popularity;
        this.tanggal = tanggal;
        this.overview = overview;
        this.photo = photo;
        this.rating = rating;
        this.language = language;
    }

    public FavoriteMovie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.popularity);
        dest.writeString(this.tanggal);
        dest.writeString(this.overview);
        dest.writeString(this.photo);
        dest.writeString(this.rating);
        dest.writeString(this.language);
    }

    protected FavoriteMovie(Parcel in) {
        this.id = in.readInt();
        this.judul = in.readString();
        this.popularity = in.readString();
        this.tanggal = in.readString();
        this.overview = in.readString();
        this.photo = in.readString();
        this.rating = in.readString();
        this.language = in.readString();
    }

    public static final Parcelable.Creator<FavoriteMovie> CREATOR = new Parcelable.Creator<FavoriteMovie>() {
        @Override
        public FavoriteMovie createFromParcel(Parcel source) {
            return new FavoriteMovie(source);
        }

        @Override
        public FavoriteMovie[] newArray(int size) {
            return new FavoriteMovie[size];
        }
    };
}
