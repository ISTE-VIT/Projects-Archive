package com.example.jokesapitester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result.append(current);
                    data = reader.read();
                }
                return result.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }

        }
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            EditText setup=findViewById(R.id.text1);
            EditText punchline=findViewById(R.id.texto2);
            try {
                JSONObject jsonObject=new JSONObject(s);
                String message=jsonObject.getString("setup");
                setup.setText(message);
                String message2=jsonObject.getString("punchline");
                punchline.setText(message2);




            } catch (Exception e) {
                e.printStackTrace();

                Toast.makeText(getApplicationContext(),"could not get api", Toast.LENGTH_SHORT).show();
            }
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void press(View view){
        DownloadTask task = new DownloadTask();
        //task.execute("https://official-joke-api.appspot.com/random_joke");
        task.execute("https://nova-joke-api.netlify.app/.netlify/functions/index/random_joke");
        InputMethodManager mgr= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}