package com.dicoding.picodiploma.submissionkeempat3.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.picodiploma.submissionkeempat3.R;
import com.dicoding.picodiploma.submissionkeempat3.db.MovieHelper;
import com.dicoding.picodiploma.submissionkeempat3.db.TvHelper;
import com.dicoding.picodiploma.submissionkeempat3.entity.FavoriteMovie;
import com.dicoding.picodiploma.submissionkeempat3.entity.FavoriteTv;

public class FavTvActivity extends AppCompatActivity {

    TextView tvJudul;
    TextView tvPopularity;
    TextView tvTanggal;
    TextView tvOverview;
    ImageView imgCover;
    TextView tvRating;
    TextView tvLanguage;
    Button btnSubmit;

    private boolean isEdit = true;
    private FavoriteTv favoriteMovie;
    private int position;
    private TvHelper noteHelper;

    public static final String EXTRA_POSITION = "extra_position";
    public static final String EXTRA_TV_FAVORITE = "extra_tv_favorite";
    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 101;
    public static final int REQUEST_UPDATE = 200;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private final int ALERT_DIALOG_CLOSE = 10;
    private final int ALERT_DIALOG_DELETE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        tvJudul = findViewById(R.id.tv_judul);
        tvPopularity = findViewById(R.id.tv_popularity);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvOverview = findViewById(R.id.tv_deskripsi);
        imgCover = findViewById(R.id.img_photo);
        tvRating = findViewById(R.id.tv_rating);
        tvLanguage = findViewById(R.id.tv_language);
        btnSubmit = findViewById(R.id.btn_submit);

        position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        btnSubmit.setVisibility(View.GONE);


        favoriteMovie = getIntent().getParcelableExtra(EXTRA_TV_FAVORITE);
        assert favoriteMovie != null;
        tvJudul.setText(favoriteMovie.getJudul());
        tvPopularity.setText(favoriteMovie.getPopularity());
        tvTanggal.setText(favoriteMovie.getTanggal());
        tvOverview.setText(favoriteMovie.getOverview());

        Glide.with(this)
                .load(favoriteMovie.getPhoto())
                .apply(new RequestOptions().override(450, 650))
                .into(imgCover);

        tvRating.setText(favoriteMovie.getRating());
        tvLanguage.setText(favoriteMovie.getLanguage());

        noteHelper = TvHelper.getInstance(getApplicationContext());
        noteHelper.open();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isEdit) {
            getMenuInflater().inflate(R.menu.menu_form, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialog(int type) {
        String dialogTitle, dialogMessage;

        dialogMessage = getResources().getString(R.string.dialog_messgae);
        dialogTitle = getResources().getString(R.string.hapus_message);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        long result = noteHelper.deleteById(String.valueOf(favoriteMovie.getId()));
                        if (result > 0) {
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_POSITION, position);
                            setResult(RESULT_DELETE, intent);
                            finish();
                        } else {
                            Toast.makeText(FavTvActivity.this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}