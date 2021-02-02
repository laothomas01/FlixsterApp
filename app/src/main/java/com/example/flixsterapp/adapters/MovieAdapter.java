package com.example.flixsterapp.adapters;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixsterapp.R;
import com.example.flixsterapp.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    //what is this?
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context,List<Movie> movies)
    {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        Log.d("MovieAdapter","onCreateViewHolder");
        return new ViewHolder(movieView);
    }

    //Involves populating data into the item through the holder
    //sets the view attributes based on the data
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        //Get the data model based on position
        Movie movie = movies.get(position);
        //Bind the movie data into the view holder
        holder.bind(movie);
    }

    //Returns total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
                tvTitle.setText(movie.getTitle());
                tvOverview.setText((movie.getOverview()));
                String imageURL;
                //if phone is in landscape,
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                    imageURL = movie.getBackdropPath();
            }
            else
            {
                imageURL = movie.getPosterPath();
            }
                    Glide.with(context)//pass activity
                    .load(imageURL)//image URL
                   .placeholder(R.drawable.loader)
                            .error(R.drawable.error)
                     .into(ivPoster);

        }
    }
}
