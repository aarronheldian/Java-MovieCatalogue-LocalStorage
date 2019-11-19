package com.dicoding.picodiploma.submissionkeempat3.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static final class FavMovieColumns implements BaseColumns {

        static String TABLE_MOVIE = "movie_fav";
        public static String TITLE = "title";
        public static String DATE = "date";
        public static String PHOTO = "photo";
        public static String OVERVIEW = "overview";
        public static String LANGUAGE = "language";
        public static String POPULARITY = "popularity";
        public static String RATE = "rate";
    }

    public static final class FavTvColumns implements BaseColumns {

        static String TABLE_TV = "Tv_fav";
        public static String TITLE = "title";
        public static String DATE = "date";
        public static String PHOTO = "photo";
        public static String OVERVIEW = "overview";
        public static String LANGUAGE = "language";
        public static String POPULARITY = "popularity";
        public static String RATE = "rate";
    }
}
