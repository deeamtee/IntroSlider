package info.androidhive.introslider;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import info.androidhive.introslider.Adapter.MoviesAdapter;
import info.androidhive.introslider.Manager.PrefManager;
import info.androidhive.introslider.Model.Movie;
import info.androidhive.introslider.Model.MoviesResponse;
import info.androidhive.introslider.Rest.ApiClient;
import info.androidhive.introslider.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //для дебага,
//        PrefManager prefManager = new PrefManager(getApplicationContext());
//       prefManager.setFirstTimeLaunch(true);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Need to change API KEY from themoviedb.org ", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                //тут можно настроить обработчик response.code();
                Log.i(TAG, "onResponse CODE: " + statusCode);
                List<Movie> movies = response.body().getResults();
                MoviesAdapter adapter = new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext());
                recyclerView.setAdapter(adapter);
                Log.i(TAG, "onResponse: " + response.raw());
                Log.d(TAG,"response.raw().request().url();"+response.raw().request().url());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

}
