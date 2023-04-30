package com.example.task1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class scoreAdapter extends RecyclerView.Adapter<scoreAdapter.ScoreViewHolder>
{
    private Context context;
    private ScoreList scores;



    public scoreAdapter(Context context, ScoreList scores) {
        this.context = context;
        this.scores = scores;
    }

//    public void setMovieCallback(MovieCallback movieCallback) {
//        this.movieCallback = movieCallback;
//    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score, parent, false);
        ScoreViewHolder scoreViewHolder = new ScoreViewHolder(view);
        return scoreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
//        Score score = getItem(position);
        Log.d("TAG", "onBindViewHolder: " + scores.getScore().get(position));
        holder.highscore_score.setText(scores.getScore().get(position).getScore() + "");
     //   ImageLoader.getInstance().loadImage(movie.getImage(), holder.movie_IMG_poster);

    }

    @Override
    public int getItemCount() {
        return this.scores == null ? 0 : scores.getScore().size();
    }

    private Score getItem(int position) {
        return this.scores.getScore().get(position);
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {

        private ShapeableImageView thropy_icon;
        private MaterialTextView highscore_score;
        private MaterialTextView highscore_title;


        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            highscore_title = itemView.findViewById(R.id.highscore_title);
            thropy_icon = itemView.findViewById(R.id.thropy_icon);
            highscore_score = itemView.findViewById(R.id.highscore_score);

//            itemView.setOnClickListener(v -> {
//                if (movieCallback != null)
//                    movieCallback.itemClicked(getItem(getAdapterPosition()), getAdapterPosition());
//            });

        }
    }
}
