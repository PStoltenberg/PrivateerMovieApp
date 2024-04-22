package com.example.privateermovie;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

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
    }


    void initGui(){
        findViewById(R.id.btn_movies).setOnClickListener(v -> {

        });
        findViewById(R.id.btn_series).setOnClickListener(v -> {

        });
        findViewById(R.id.btn_watchlist).setOnClickListener(v -> {

        });
        findViewById(R.id.btn_search).setOnClickListener(v -> {

        });
        findViewById(R.id.btn_niklas).setOnClickListener(v -> {

        });

    }

    private void fragmentChanger(Class c) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, c, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // Gør, at hvis brugeren trykker på backknappen så ryger man tilbage til den foregående
                .commit();
    }

}