package com.dicoding.picodiploma.submissionkeempat3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicoding.picodiploma.submissionkeempat3.R;
import com.dicoding.picodiploma.submissionkeempat3.entity.DataMovie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<DataMovie> listMovie;

    public MovieAdapter(Context context, ArrayList<DataMovie> listMovie) {
        this.context = context;
        this.listMovie = listMovie;
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ArrayList<DataMovie> getListMovie(){
        return listMovie;
    }

    public void setData(ArrayList<DataMovie> items) {
        this.listMovie = new ArrayList<>();
        this.listMovie.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.bind(listMovie.get(position));
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView textViewJudul;
        TextView textViewTanggal;
        TextView textViewOverview;
        TextView textViewRating;
        ImageView imgPhoto;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewJudul = itemView.findViewById(R.id.tv_judul);
            textViewOverview = itemView.findViewById(R.id.tv_deskripsi);
            textViewTanggal = itemView.findViewById(R.id.tv_tanggal);
            textViewRating = itemView.findViewById(R.id.tv_rating);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }

        void bind(DataMovie dataM) {
            Glide.with(itemView)
                    .load(dataM.getPhoto())
                    .into(imgPhoto);

            textViewJudul.setText(dataM.getJudul());
            textViewOverview.setText(dataM.getOverview());
            textViewTanggal.setText(dataM.getTanggal());
            textViewRating.setText(dataM.getRating());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(listMovie.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(DataMovie dataM);
    }
}
