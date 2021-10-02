package com.alok.weatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherInformation<error> extends AppCompatActivity {
    public String city;
    Context context;
    private TextView textView;
    public TextView temp;
    public TextView description;
    public String url;
    public TextView visib;
    public TextView windSpeed;
    public TextView pressure;
    public TextView humidity;
    public ImageView imageView;
    RequestQueue queue;
    RequestQueue queue1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_information);
//        Finding
        textView = findViewById(R.id.textView3);
        temp = findViewById(R.id.temp);
        description = findViewById(R.id.desc);
        imageView = findViewById(R.id.imageView);
        visib = findViewById(R.id.visibility);
        windSpeed = findViewById(R.id.wind);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);


//        Extracting name
        Bundle extra = getIntent().getExtras();
        city = extra.getString("key");
        textView.setText(city.toString().toUpperCase());
        url = "https://api.openweathermap.org/data/2.5/weather?q=" + city.toLowerCase() + "&appid=cabf35d8abfa40931f2f56c92e0e1e14";

        queue = Volley.newRequestQueue(WeatherInformation.this);
        queue1 = Volley.newRequestQueue(WeatherInformation.this);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String imageUrl = "http://openweathermap.org/img/wn/" + response.getJSONArray("weather").getJSONObject(0).getString("icon") + "@2x.png";
                        Picasso.get().load(imageUrl).into(imageView);
                        description.setText(response.getJSONArray("weather").getJSONObject(0).getString("description").toUpperCase());
                        visib.setText("Visibility : " + response.getString("visibility") + "m");
                        windSpeed.setText("Wind Speed : " + response.getJSONObject("wind").getString("speed") + "m/s");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, error -> {
            Log.d("jsonError", "onCreate Failed!!");
        });
        queue1.add(jsonObjectRequest1);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        temp.setText("Temperature : " + response.getJSONObject("main").getString("temp") + "Kelvin");
                        pressure.setText("Pressure : " + response.getJSONObject("main").getString("pressure") + "hPa");
                        humidity.setText("Humidity : " + response.getJSONObject("main").getString("humidity") + "%");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, error -> {
            Log.d("jsonError", "onCreate Failed!!");
        });
        queue.add(jsonObjectRequest);
    }

    public void backButton(View view) {
        Intent intent = new Intent(WeatherInformation.this, MainActivity.class); //in (from,to)
        startActivity(intent);
    }
}