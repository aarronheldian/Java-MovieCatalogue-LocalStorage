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
import com.dicoding.picodiploma.submissionkeempat3.entity.DataTv;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private Context context;
    private ArrayList<DataTv> listTv;

    public TvAdapter(Context context, ArrayList<DataTv> listTv) {
        this.context = context;
        this.listTv = listTv;
    }

    private TvAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(TvAdapter.OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ArrayList<DataTv> getListTv(){
        return listTv;
    }

    public void setData(ArrayList<DataTv> items) {
        this.listTv = new ArrayList<>();
        this.listTv.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new TvViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.TvViewHolder holder, int position) {
        holder.bind(listTv.get(position));
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder {
        TextView textViewJudul;
        TextView textViewTanggal;
        TextView textViewOverview;
        TextView textViewRating;
        ImageView imgPhoto;

        TvViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewJudul = itemView.findViewById(R.id.tv_judul);
            textViewOverview = itemView.findViewById(R.id.tv_deskripsi);
            textViewTanggal = itemView.findViewById(R.id.tv_tanggal);
            textViewRating = itemView.findViewById(R.id.tv_rating);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }

        void bind(DataTv dataT) {
            Glide.with(itemView)
                    .load(dataT.getPhoto())
                    .into(imgPhoto);

            textViewJudul.setText(dataT.getJudul());
            textViewOverview.setText(dataT.getOverview());
            textViewTanggal.setText(dataT.getTanggal());
            textViewRating.setText(dataT.getRating());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(listTv.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(DataTv dataT);
    }
}
