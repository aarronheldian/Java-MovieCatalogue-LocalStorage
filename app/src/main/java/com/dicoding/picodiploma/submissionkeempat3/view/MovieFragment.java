package com.dicoding.picodiploma.submissionkeempat3.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.picodiploma.submissionkeempat3.R;
import com.dicoding.picodiploma.submissionkeempat3.adapter.MovieAdapter;
import com.dicoding.picodiploma.submissionkeempat3.entity.DataMovie;
import com.dicoding.picodiploma.submissionkeempat3.model.MovieViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ProgressBar progressBar;
    private MovieAdapter adapter;
    private MovieViewModel movieViewModel;
    private RecyclerView rvMovie;
    private ArrayList<DataMovie> listMovie = new ArrayList<>();
    private String language;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        language = getResources().getString(R.string.id_bahasa);

        movieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
        movieViewModel.getMovie().observe(this, getMovie);

        adapter = new MovieAdapter(getContext(),listMovie);
        adapter.notifyDataSetChanged();

        rvMovie = getActivity().findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(adapter);

        adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(DataMovie datam) {
                showSelectedHero(datam);
            }
        });

        progressBar = getActivity().findViewById(R.id.progressBar);

        movieViewModel.setMovie(language);
        showLoading(true);
    }

    private Observer<ArrayList<DataMovie>> getMovie = new Observer<ArrayList<DataMovie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<DataMovie> movies) {
            if (movies != null){
                adapter.setData(movies);
                showLoading(false);
            }
        }
    };

    private void showSelectedHero(DataMovie dataM) {
        Intent moveWithObjectIntent = new Intent(this.getContext(), MovieActivity.class);
        moveWithObjectIntent.putExtra(MovieActivity.EXTRA_MOVIE, dataM);
        startActivity(moveWithObjectIntent);

        Toast.makeText(this.getContext(), "Kamu memilih " + dataM.getJudul(), Toast.LENGTH_SHORT).show();
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
