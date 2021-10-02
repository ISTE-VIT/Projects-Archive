package com.prasoon.tvshows;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prasoon.tvshows.adapters.TVShowAdapter;
import com.prasoon.tvshows.models.TVShow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.movieRecyclerView) RecyclerView movieRecyclerView;
    @BindView(R.id.nointernet) ConstraintLayout noInternet;
    @BindView(R.id.titleText) TextView titleText;
    private final List<TVShow> tvShows = new ArrayList<>();
    private TVShowAdapter adapter;
    private AlertDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showLoadingDialog();

        setRecyclerView();

        getTvShowsData();
    }

    // Function to show loading dialog.
    private void showLoadingDialog(){
        View dialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, null);
        loadingDialog = new AlertDialog.Builder(MainActivity.this)
                .setView(dialogView)
                .setCancelable(false)
                .create();
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    // Function to set Recycler View for use.
    private void setRecyclerView(){
        adapter = new TVShowAdapter(tvShows,this);
        movieRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieRecyclerView.setAdapter(adapter);
    }

    // Function for animating recycler view items
    private void recyclerViewAnimation(){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(movieRecyclerView.getContext(), R.anim.layout_fall_down);
        movieRecyclerView.setLayoutAnimation(layoutAnimationController);
        movieRecyclerView.getAdapter().notifyDataSetChanged();
        movieRecyclerView.scheduleLayoutAnimation();
    }

    // Function to get Data from the API.
    private void getTvShowsData(){
        loadingDialog.show();
        String url = "https://api.themoviedb.org/3/tv/popular?api_key=52a18783ed514602a5facb15a0177e61";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray movieArray = object.getJSONArray("results");
                        for(int i=0;i<movieArray.length();i++){

                            JSONObject show = movieArray.getJSONObject(i);

                            String backdrop_path = "https://image.tmdb.org/t/p/w500" + show.getString("backdrop_path");
                            Log.i("TVSHOWDATA", backdrop_path);

                            String first_air_date = show.getString("first_air_date");
                            Log.i("TVSHOWDATA", first_air_date);

                            String name = show.getString("name");
                            Log.i("TVSHOWDATA", name);

                            String original_name = show.getString("original_name");
                            Log.i("TVSHOWDATA", original_name);

                            String overview = show.getString("overview");
                            Log.i("TVSHOWDATA", overview);

                            double popularity = show.getDouble("popularity");
                            Log.i("TVSHOWDATA", String.valueOf(popularity));

                            String poster_path = "https://image.tmdb.org/t/p/w500" + show.getString("poster_path");
                            Log.i("TVSHOWDATA", poster_path);

                            double vote_average = show.getDouble("vote_average");
                            Log.i("TVSHOWDATA", String.valueOf(vote_average));

                            int vote_count = show.getInt("vote_count");
                            Log.i("TVSHOWDATA", String.valueOf(vote_count));

                            TVShow tvShow = new TVShow(backdrop_path,first_air_date,name,original_name,overview,popularity,poster_path,vote_average,vote_count);
                            tvShows.add(tvShow);
                        }
                        adapter.notifyDataSetChanged();
                        recyclerViewAnimation();
                        loadingDialog.dismiss();
                    } catch (JSONException e) {
                        noInternet.setVisibility(View.VISIBLE);
                        titleText.setText("Cannot Fetch Data");
                        loadingDialog.dismiss();
                    }
                },
                error -> {
                    titleText.setText("Cannot Fetch Data");
                    noInternet.setVisibility(View.VISIBLE);
                    loadingDialog.dismiss();
                }
        );

        queue.add(request);
    }

}