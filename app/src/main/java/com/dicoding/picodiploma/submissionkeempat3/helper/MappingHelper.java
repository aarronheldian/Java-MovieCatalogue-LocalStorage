package com.dicoding.picodiploma.submissionkeempat3.helper;

import android.database.Cursor;

import com.dicoding.picodiploma.submissionkeempat3.db.DatabaseContract;
import com.dicoding.picodiploma.submissionkeempat3.entity.FavoriteMovie;
import com.dicoding.picodiploma.submissionkeempat3.entity.FavoriteTv;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<FavoriteMovie> mapCursorToArrayListM(Cursor notesCursor) {
        ArrayList<FavoriteMovie> notesList = new ArrayList<>();
        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns._ID));
            String judul = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.TITLE));
            String popularity = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.POPULARITY));
            String tanggal = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.DATE));
            String overview = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.OVERVIEW));
            String photo = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.PHOTO));
            String rating = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.RATE));
            String language = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.LANGUAGE));
            notesList.add(new FavoriteMovie(id, judul, popularity, tanggal, overview, photo, rating, language));
        }
        return notesList;
    }

    public static ArrayList<FavoriteTv> mapCursorToArrayListT(Cursor notesCursor) {
        ArrayList<FavoriteTv> notesList = new ArrayList<>();
        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns._ID));
            String judul = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.TITLE));
            String popularity = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.POPULARITY));
            String tanggal = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.DATE));
            String overview = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.OVERVIEW));
            String photo = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.PHOTO));
            String rating = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.RATE));
            String language = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.LANGUAGE));
            notesList.add(new FavoriteTv(id, judul, popularity, tanggal, overview, photo, rating, language));
        }
        return notesList;
    }
}
