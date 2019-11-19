package com.dicoding.picodiploma.submissionkeempat3.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class DataMovie implements Parcelable {

    private int id;
    private String judul;
    private String popularity;
    private String tanggal;
    private String overview;
    private String photo;
    private String rating;
    private String language;
    private String URL = "https://image.tmdb.org/t/p/w780";

    public DataMovie(int id, String judul, String popularity, String tanggal, String overview, String photo, String rating, String language, String URL) {
        this.id = id;
        this.judul = judul;
        this.popularity = popularity;
        this.tanggal = tanggal;
        this.overview = overview;
        this.photo = photo;
        this.rating = rating;
        this.language = language;
        this.URL = URL;
    }

    public DataMovie(JSONObject object) {
        try {
            int id = object.getInt("id");
            String original_title = object.getString("original_title");
            String overview = object.getString("overview");
            String vote_average = object.getString("vote_average");
            String popularity = object.getString("popularity");
            String language = object.getString("original_language");
            String release_date = object.getString("release_date");
            String poster_path = URL + object.getString("poster_path");

            this.id = id;
            this.judul = original_title;
            this.overview = overview;
            this.rating = vote_average;
            this.popularity = popularity;
            this.language = language;
            this.tanggal = release_date;
            this.photo = poster_path;

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
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
        dest.writeString(this.URL);
    }

    protected DataMovie(Parcel in) {
        this.id = in.readInt();
        this.judul = in.readString();
        this.popularity = in.readString();
        this.tanggal = in.readString();
        this.overview = in.readString();
        this.photo = in.readString();
        this.rating = in.readString();
        this.language = in.readString();
        this.URL = in.readString();
    }

    public static final Parcelable.Creator<DataMovie> CREATOR = new Parcelable.Creator<DataMovie>() {
        @Override
        public DataMovie createFromParcel(Parcel source) {
            return new DataMovie(source);
        }

        @Override
        public DataMovie[] newArray(int size) {
            return new DataMovie[size];
        }
    };
}
