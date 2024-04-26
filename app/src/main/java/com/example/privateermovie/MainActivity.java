package com.example.privateermovie;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.privateermovie.Models.Movie;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static RequestQueue rq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rq = Volley.newRequestQueue(getApplicationContext());
        initGui();
        fragmentChanger(StartFragment.class);

    }


    void initGui(){
        findViewById(R.id.nav_btnMovie).setOnClickListener(v -> {
            fragmentChanger(MovieFragment.class);
            getMovieByIdAsync(15);



        });
        findViewById(R.id.nav_btnSeries).setOnClickListener(v -> {
            fragmentChanger(SerieFragment.class);

        });
        findViewById(R.id.nav_btnWatchlist).setOnClickListener(v -> {
            fragmentChanger(WatchlistFragment.class);

        });
        findViewById(R.id.topNav_btnSearch).setOnClickListener(v -> {
            fragmentChanger(SearchFragment.class);

        });
        findViewById(R.id.nav_btnniklas).setOnClickListener(v -> {
            fragmentChanger(GameFragment.class);

        });

    }

    private void fragmentChanger(Class c) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, c, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // Name can be null
                .commit();
    }

    public void getMovieByIdAsync(int id) {
        {
            String url = "https://api.themoviedb.org/3/movie/" + id;
            StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
                    Movie movie = new Gson().fromJson(response, Movie.class);
                    Toast.makeText(getApplicationContext(), movie.title, Toast.LENGTH_LONG).show();
            }, error -> Log.e("Volley", error.toString()))
            {
                @Override
                public Map<String, String> getHeaders(){
                    Map<String, String>  params = new HashMap<>();
                    params.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxY2M1NTMyMjdhOTYyZmQ2NDAwYmNhN2FmOGVhMjczZCIsInN1YiI6IjY0MTk3OTRmMzEwMzI1MDA3YzBiMzk1NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Xu8iZI6DAlld0JhUWsDcguoTOMY4_SfgRB57FuJaZOA");
                    return params;
                }
            };
            rq.add(request);
        }
    }



}