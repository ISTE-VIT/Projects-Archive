package com.prasoon.tvshows.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.prasoon.tvshows.R;
import com.prasoon.tvshows.models.TVShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.MyViewHolder> {
    private final List<TVShow> shows;
    private final Context context;

    public TVShowAdapter(List<TVShow> shows, Context context) {
        this.shows = shows;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TVShow show = shows.get(position);
        holder.showName.setText(show.getName());

        Glide.with(context)
                .load(Uri.parse(show.getPosterPath()))
                .into(holder.posterImage);

        holder.date.setText(show.getReleaseDate());

        holder.reviewStar.setText(show.getVoteAverage() + "⭐");

        holder.tapMe.setOnClickListener(view -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            bottomSheetDialog.setContentView(R.layout.show_detail);

            ImageView poster = bottomSheetDialog.findViewById(R.id.poster);
            Glide.with(context)
                    .load(Uri.parse(show.getPosterPath()))
                    .into(poster);

            ImageView backdropImage = bottomSheetDialog.findViewById(R.id.backdropImage);
            Glide.with(context)
                    .load(Uri.parse(show.getBackdropPath()))
                    .into(backdropImage);

            TextView name = bottomSheetDialog.findViewById(R.id.name);
            name.setText(show.getName());

            TextView releaseDate = bottomSheetDialog.findViewById(R.id.releaseDate);
            releaseDate.setText(show.getReleaseDate());

            TextView overview = bottomSheetDialog.findViewById(R.id.overview);
            overview.setText(show.getOverview());

            TextView rating = bottomSheetDialog.findViewById(R.id.rating);
            rating.setText(show.getVoteAverage() + " ⭐");

            TextView popularity = bottomSheetDialog.findViewById(R.id.popularity);
            popularity.setText(String.valueOf(show.getPopularity()));

            TextView voteCount = bottomSheetDialog.findViewById(R.id.voteCount);
            voteCount.setText(String.valueOf(show.getVoteCount()));

            MaterialButton share = bottomSheetDialog.findViewById(R.id.share);
            share.setOnClickListener(view1 -> {
                String toSend = "Hey!! Friend, I found an amazing TV Show Called "+show.getName()
                        + " which has an user rating of " + show.getVoteAverage() + ". I would suggest" +
                        "you to watch this TV Show.";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, toSend);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);
            });

            bottomSheetDialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.posterImage) ImageView posterImage;
        @BindView(R.id.showName) TextView showName;
        @BindView(R.id.date) TextView date;
        @BindView(R.id.reviewStar) TextView reviewStar;
        @BindView(R.id.tapMe) MaterialCardView tapMe;
        public MyViewHolder(@NonNull View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
