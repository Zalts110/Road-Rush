package com.example.task1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class scoreAdapter extends RecyclerView.Adapter<scoreAdapter.ScoreViewHolder>
{
    private Context context;
    private ScoreList scores;
    private MapCallback mapCallback;



    public scoreAdapter(Context context, ScoreList scores,MapCallback mapCallback) {
        this.context = context;
        this.scores = scores;
        this.mapCallback = mapCallback;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score, parent, false);
        ScoreViewHolder scoreViewHolder = new ScoreViewHolder(view);
        return scoreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: " + scores.getScores().get(position));
        Score score = getItem(position);
        holder.highscore_score.setText(scores.getScores().get(position).getScore() + "");
        holder.score_layout.setOnClickListener(v -> scoreClick(scores.getScores().get(position).getLatitude(),scores.getScores().get(position).getLongtitude()));

    }

    private void scoreClick(double latitude, double longtitude) {
        Log.d("TAG","clickcheck" + mapCallback);
        if (mapCallback != null) {
            mapCallback.clickOnRecord( latitude, longtitude);
        }
    }

    @Override
    public int getItemCount() {
        return this.scores == null ? 0 : scores.getScores().size();
    }

    private Score getItem(int position) {
        return this.scores.getScores().get(position);
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {

        private ShapeableImageView thropy_icon;
        private MaterialTextView highscore_score;
        private MaterialTextView highscore_title;
        private RelativeLayout score_layout;


        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            highscore_title = itemView.findViewById(R.id.highscore_title);
            thropy_icon = itemView.findViewById(R.id.thropy_icon);
            highscore_score = itemView.findViewById(R.id.highscore_score);
            score_layout = itemView.findViewById(R.id.score_layout);
        }

    }
}
