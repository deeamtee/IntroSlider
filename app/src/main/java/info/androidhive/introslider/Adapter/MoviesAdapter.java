package info.androidhive.introslider.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import info.androidhive.introslider.Model.Movie;
import info.androidhive.introslider.MovieActivity;
import info.androidhive.introslider.R;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private static final String TAG = "MoviesAdapter";
    private List<Movie> movies;
    private int rowLayout;
   static private Context context;


    String baseUrlImage = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        TextView urlImage;

        LinearLayout linearLayout;






        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);

            urlImage = (TextView) v.findViewById(R.id.urlImageMovie);

            linearLayout = (LinearLayout) v.findViewById(R.id.press_linear);
            linearLayout.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, MovieActivity.class);
            intent.putExtra("movieDescription", movieDescription.getText());
            intent.putExtra("movieTitle", movieTitle.getText());

            intent.putExtra("urlImage", urlImage.getText());



            context.startActivity(intent);
            Log.i(TAG, "onClick: YEAH!!!!" );
        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());

        String posterPath = movies.get(position).getPosterPath();
        String urlImage = baseUrlImage + posterPath;
        Log.i(TAG, "onBindViewHolder: " + urlImage);
        holder.urlImage.setText(urlImage);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
