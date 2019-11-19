package com.dicoding.picodiploma.submissionkeempat3.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.dicoding.picodiploma.submissionkeempat3.db.DatabaseContract.FavMovieColumns.TABLE_MOVIE;
import static com.dicoding.picodiploma.submissionkeempat3.db.DatabaseContract.FavTvColumns.TABLE_TV;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "db_netflux";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_MOVIE,
            DatabaseContract.FavMovieColumns._ID,
            DatabaseContract.FavMovieColumns.TITLE,
            DatabaseContract.FavMovieColumns.DATE,
            DatabaseContract.FavMovieColumns.PHOTO,
            DatabaseContract.FavMovieColumns.OVERVIEW,
            DatabaseContract.FavMovieColumns.LANGUAGE,
            DatabaseContract.FavMovieColumns.POPULARITY,
            DatabaseContract.FavMovieColumns.RATE
    );

    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_TV,
            DatabaseContract.FavTvColumns._ID,
            DatabaseContract.FavTvColumns.TITLE,
            DatabaseContract.FavTvColumns.DATE,
            DatabaseContract.FavTvColumns.PHOTO,
            DatabaseContract.FavTvColumns.OVERVIEW,
            DatabaseContract.FavTvColumns.LANGUAGE,
            DatabaseContract.FavTvColumns.POPULARITY,
            DatabaseContract.FavTvColumns.RATE
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TV);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }
}
