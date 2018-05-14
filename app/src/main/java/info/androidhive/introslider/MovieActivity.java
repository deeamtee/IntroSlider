package info.androidhive.introslider;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieActivity extends AppCompatActivity {
    String title = "Movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.image_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        String movieDescription = extras.getString("movieDescription");
        String movieTitle = extras.getString("movieTitle");
        String urlImage = extras.getString("urlImage");

        ImageView imageUrlPoster = (ImageView) findViewById(R.id.imageUrlPoster);

        Glide.with(this)
                .load(urlImage)
                .into(imageUrlPoster);

        TextView tvMovie = (TextView) findViewById(R.id.tvMovie);
        tvMovie.setText(movieDescription);

        getSupportActionBar().setTitle(movieTitle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
