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
import com.dicoding.picodiploma.submissionkeempat3.adapter.TvAdapter;
import com.dicoding.picodiploma.submissionkeempat3.entity.DataTv;
import com.dicoding.picodiploma.submissionkeempat3.model.TvViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    private ProgressBar progressBar;
    private TvAdapter adapter;
    private TvViewModel tvViewModel;
    private RecyclerView rvTv;
    private ArrayList<DataTv> listTv = new ArrayList<>();
    private String language;

    public TvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        language = getResources().getString(R.string.id_bahasa);

        tvViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvViewModel.class);
        tvViewModel.getTv().observe(this, getTv);

        adapter = new TvAdapter(getContext(),listTv);
        adapter.notifyDataSetChanged();

        rvTv = getActivity().findViewById(R.id.rv_tv);
        rvTv.setHasFixedSize(true);
        rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTv.setAdapter(adapter);

        adapter.setOnItemClickCallback(new TvAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(DataTv dataT) {
                showSelectedHero(dataT);
            }
        });

        progressBar = getActivity().findViewById(R.id.progressBar);

        tvViewModel.setTv(language);
        showLoading(true);
    }

    private Observer<ArrayList<DataTv>> getTv = new Observer<ArrayList<DataTv>>() {
        @Override
        public void onChanged(@Nullable ArrayList<DataTv> tv) {
            if (tv != null){
                adapter.setData(tv);
                showLoading(false);
            }
        }
    };

    private void showSelectedHero(DataTv dataT) {
        Intent moveWithObjectIntent = new Intent(this.getContext(), TvActivity.class);
        moveWithObjectIntent.putExtra(TvActivity.EXTRA_TV, dataT);
        startActivity(moveWithObjectIntent);

        Toast.makeText(this.getContext(), "Kamu memilih " + dataT.getJudul(), Toast.LENGTH_SHORT).show();
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
