package com.dicoding.picodiploma.submissionkeempat3.view;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.picodiploma.submissionkeempat3.R;
import com.dicoding.picodiploma.submissionkeempat3.adapter.FavoriteMovieAdapter;
import com.dicoding.picodiploma.submissionkeempat3.adapter.FavoriteTvAdapter;
import com.dicoding.picodiploma.submissionkeempat3.db.MovieHelper;
import com.dicoding.picodiploma.submissionkeempat3.db.TvHelper;
import com.dicoding.picodiploma.submissionkeempat3.entity.FavoriteMovie;
import com.dicoding.picodiploma.submissionkeempat3.entity.FavoriteTv;
import com.dicoding.picodiploma.submissionkeempat3.helper.MappingHelper;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFavFragment extends Fragment implements LoadTvCallback {

    private ProgressBar progressBar;
    private RecyclerView rvMovie;
    private FavoriteTvAdapter adapter;
    private TvHelper noteHelper;
    private static final String EXTRA_STATE = "EXTRA_STATE";

    public TvFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tv_fav, container, false);

        progressBar = rootView.findViewById(R.id.progressBar);
        rvMovie = rootView.findViewById(R.id.rv_movie);
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovie.setHasFixedSize(true);
        adapter = new FavoriteTvAdapter(getActivity());
        rvMovie.setAdapter(adapter);

        noteHelper = TvHelper.getInstance(getContext());
        noteHelper.open();

        new TvFavFragment.LoadTvsAsync(noteHelper, this).execute();

        if (savedInstanceState == null){
            new TvFavFragment.LoadTvsAsync(noteHelper, this).execute();
        }else{
            ArrayList<FavoriteTv> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null){
                adapter.setListNotes(list);
            }
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListNotes());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        noteHelper.close();
    }

    private void showSnackbarMessage(String message){
        Snackbar.make(rvMovie, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void preExecute() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void postExecute(ArrayList<FavoriteTv> Favorites) {
        progressBar.setVisibility(View.INVISIBLE);
        if (Favorites.size() > 0){
            adapter.setListNotes(Favorites);
        }else{
            adapter.setListNotes(new ArrayList<FavoriteTv>());
            showSnackbarMessage("Tidak Ada Data Saat Ini");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovie.setHasFixedSize(true);
        adapter = new FavoriteTvAdapter(getActivity());
        rvMovie.setAdapter(adapter);

        noteHelper = TvHelper.getInstance(getContext());
        noteHelper.open();

        new TvFavFragment.LoadTvsAsync(noteHelper, this).execute();

    }

    private static class LoadTvsAsync extends AsyncTask<Void, Void, ArrayList<FavoriteTv>> {

        private final WeakReference<TvHelper> weakMovieHelper;
        private final WeakReference<LoadTvCallback> weakCallback;

        private LoadTvsAsync(TvHelper TvHelper, LoadTvCallback callback){
            weakMovieHelper = new WeakReference<>(TvHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<FavoriteTv> doInBackground(Void... voids) {
            Cursor dataCursor = weakMovieHelper.get().queryAll();
            return MappingHelper.mapCursorToArrayListT(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<FavoriteTv> modelFavorites) {
            super.onPostExecute(modelFavorites);

            weakCallback.get().postExecute(modelFavorites);
        }
    }
}

interface LoadTvCallback{
    void preExecute();
    void postExecute(ArrayList<FavoriteTv> Favorites);
}
