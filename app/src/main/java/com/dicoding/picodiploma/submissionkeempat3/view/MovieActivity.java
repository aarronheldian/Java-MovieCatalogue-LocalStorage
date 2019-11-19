package com.dicoding.picodiploma.submissionkeempat3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.picodiploma.submissionkeempat3.R;
import com.dicoding.picodiploma.submissionkeempat3.db.MovieHelper;
import com.dicoding.picodiploma.submissionkeempat3.entity.DataMovie;
import com.dicoding.picodiploma.submissionkeempat3.entity.FavoriteMovie;

import static android.provider.BaseColumns._ID;
import static com.dicoding.picodiploma.submissionkeempat3.db.DatabaseContract.FavMovieColumns.DATE;
import static com.dicoding.picodiploma.submissionkeempat3.db.DatabaseContract.FavMovieColumns.LANGUAGE;
import static com.dicoding.picodiploma.submissionkeempat3.db.DatabaseContract.FavMovieColumns.OVERVIEW;
import static com.dicoding.picodiploma.submissionkeempat3.db.DatabaseContract.FavMovieColumns.PHOTO;
import static com.dicoding.picodiploma.submissionkeempat3.db.DatabaseContract.FavMovieColumns.POPULARITY;
import static com.dicoding.picodiploma.submissionkeempat3.db.DatabaseContract.FavMovieColumns.RATE;
import static com.dicoding.picodiploma.submissionkeempat3.db.DatabaseContract.FavMovieColumns.TITLE;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvJudul;
    TextView tvPopularity;
    TextView tvTanggal;
    TextView tvOverview;
    ImageView imgCover;
    TextView tvRating;
    TextView tvLanguage;

    Button btnSubmit;

    private boolean isEdit = false;
    private FavoriteMovie favoriteMovie;
    private int position;
    private MovieHelper noteHelper;

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_POSITION = "extra_position";
    public static final String EXTRA_MOVIE_FAVORITE = "extra_film_favorite";
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
        setContentView(R.layout.activity_movie);

        tvJudul = findViewById(R.id.tv_judul);
        tvPopularity = findViewById(R.id.tv_popularity);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvOverview = findViewById(R.id.tv_deskripsi);
        imgCover = findViewById(R.id.img_photo);
        tvRating = findViewById(R.id.tv_rating);
        tvLanguage = findViewById(R.id.tv_language);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(this);

        DataMovie dataM = getIntent().getParcelableExtra(EXTRA_MOVIE);
        assert dataM != null;
        tvJudul.setText(dataM.getJudul());
        tvPopularity.setText(dataM.getPopularity());
        tvTanggal.setText(dataM.getTanggal());
        tvOverview.setText(dataM.getOverview());

        Glide.with(this)
                .load(dataM.getPhoto())
                .apply(new RequestOptions().override(450, 650))
                .into(imgCover);

        tvRating.setText(dataM.getRating());
        tvLanguage.setText(dataM.getLanguage());

        noteHelper = MovieHelper.getINSTANCE(getApplicationContext());
        noteHelper.open();
        favoriteMovie = getIntent().getParcelableExtra(EXTRA_MOVIE_FAVORITE);

        if (favoriteMovie != null){
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
            isEdit = true;
            btnSubmit.setVisibility(View.GONE);
        }else{
            favoriteMovie = new FavoriteMovie();
        }

        if (savedInstanceState != null){
            DataMovie dataMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);

            tvJudul.setText(dataMovie.getJudul());
            tvPopularity.setText(dataMovie.getPopularity());
            tvTanggal.setText(dataMovie.getTanggal());
            tvOverview.setText(dataMovie.getOverview());

            Glide.with(this)
                    .load(dataMovie.getPhoto())
                    .apply(new RequestOptions().override(450, 650))
                    .into(imgCover);

            tvRating.setText(dataMovie.getRating());
            tvLanguage.setText(dataMovie.getLanguage());

            btnSubmit.setVisibility(View.VISIBLE);
        }else{
            final Handler handler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            DataMovie dataMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);

                            tvJudul.setText(dataMovie.getJudul());
                            tvPopularity.setText(dataMovie.getPopularity());
                            tvTanggal.setText(dataMovie.getTanggal());
                            tvOverview.setText(dataMovie.getOverview());

                            Glide.with(MovieActivity.this)
                                    .load(dataMovie.getPhoto())
                                    .apply(new RequestOptions().override(450, 650))
                                    .into(imgCover);

                            tvRating.setText(dataMovie.getRating());
                            tvLanguage.setText(dataMovie.getLanguage());

                            btnSubmit.setVisibility(View.VISIBLE);

                            if (getSupportActionBar() != null){
                                getSupportActionBar().setTitle(dataMovie.getJudul());
                            }
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_submit){
            DataMovie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
            String title = tvJudul.getText().toString().trim();
            String popularity = tvPopularity.getText().toString().trim();
            String tanggal = tvTanggal.getText().toString().trim();
            String overview = tvOverview.getText().toString().trim();
            String imgCover = movie.getPhoto();
            String rating = tvRating.getText().toString().trim();
            String language = tvLanguage.getText().toString().trim();
            int id = movie.getId();

            favoriteMovie.setJudul(title);
            favoriteMovie.setPopularity(popularity);
            favoriteMovie.setTanggal(tanggal);
            favoriteMovie.setOverview(overview);
            favoriteMovie.setPhoto(imgCover);
            favoriteMovie.setRating(rating);
            favoriteMovie.setLanguage(language);
            favoriteMovie.setId(id);

            Intent intent = new Intent();
            intent.putExtra(EXTRA_MOVIE_FAVORITE, favoriteMovie);
            intent.putExtra( EXTRA_POSITION, position);

            ContentValues values = new ContentValues();
            values.put(TITLE, title);
            values.put(PHOTO, imgCover);
            values.put(OVERVIEW, overview);
            values.put(LANGUAGE, language);
            values.put(POPULARITY, popularity);
            values.put(RATE, rating);
            values.put(DATE, tanggal);
            values.put(_ID, id);


            if (!isEdit){
                long result = noteHelper.insert(values);
                if (result > 0){
                    favoriteMovie.setId((int) result);
                    setResult(RESULT_ADD, intent);
                    Toast.makeText(MovieActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }else{
                Toast.makeText(MovieActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
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
