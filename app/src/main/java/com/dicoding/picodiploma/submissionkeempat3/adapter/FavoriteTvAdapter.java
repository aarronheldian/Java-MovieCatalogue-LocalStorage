package com.dicoding.picodiploma.submissionkeempat3.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicoding.picodiploma.submissionkeempat3.CustomOnItemClickListener;
import com.dicoding.picodiploma.submissionkeempat3.R;
import com.dicoding.picodiploma.submissionkeempat3.entity.FavoriteTv;
import com.dicoding.picodiploma.submissionkeempat3.view.FavTvActivity;

import java.util.ArrayList;

public class FavoriteTvAdapter extends RecyclerView.Adapter<FavoriteTvAdapter.TvViewHolder> {
    private final ArrayList<FavoriteTv> listNotes = new ArrayList<>();
    private final Activity activity;

    public FavoriteTvAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<FavoriteTv> getListNotes() {
        return listNotes;
    }

    public void setListNotes(ArrayList<FavoriteTv> listNotes) {

        if (listNotes.size() > 0) {
            this.listNotes.clear();
        }
        this.listNotes.addAll(listNotes);

        notifyDataSetChanged();
    }

    public void addItem(FavoriteTv note) {
        this.listNotes.add(note);
        notifyItemInserted(listNotes.size() - 1);
    }

    public void updateItem(int position, FavoriteTv note) {
        this.listNotes.set(position, note);
        notifyItemChanged(position, note);
    }

    public void removeItem(int position) {
        this.listNotes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listNotes.size());
    }

    @NonNull
    @Override
    public FavoriteTvAdapter.TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new FavoriteTvAdapter.TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTvAdapter.TvViewHolder holder, int position) {
        holder.textViewJudul.setText(listNotes.get(position).getJudul());
        holder.textViewTanggal.setText(listNotes.get(position).getTanggal());
        holder.textViewOverview.setText(listNotes.get(position).getOverview());
        holder.textViewRating.setText(listNotes.get(position).getRating());

        Glide.with(holder.itemView.getContext())
                .load(listNotes.get(position).getPhoto())
                .into(holder.imgPhoto);

        holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FavTvActivity.class);
                intent.putExtra(FavTvActivity.EXTRA_POSITION, position);
                intent.putExtra(FavTvActivity.EXTRA_TV_FAVORITE, listNotes.get(position));
                activity.startActivityForResult(intent, FavTvActivity.REQUEST_UPDATE);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder {
        TextView textViewJudul;
        TextView textViewTanggal;
        TextView textViewOverview;
        TextView textViewRating;
        ImageView imgPhoto;
        final CardView cvNote;

        TvViewHolder(View itemView) {
            super(itemView);
            textViewJudul = itemView.findViewById(R.id.tv_judul);
            textViewTanggal = itemView.findViewById(R.id.tv_tanggal);
            textViewOverview = itemView.findViewById(R.id.tv_deskripsi);
            textViewRating = itemView.findViewById(R.id.tv_rating);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            cvNote = itemView.findViewById(R.id.card_view);
        }
    }
}