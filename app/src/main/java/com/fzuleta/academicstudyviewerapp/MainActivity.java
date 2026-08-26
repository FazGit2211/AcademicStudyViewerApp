package com.fzuleta.academicstudyviewerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.fzuleta.academicstudyviewerapp.models.Career;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerView = findViewById(R.id.recView);
        /*
         *Will be Get from database
         */
        ArrayList<Career> carrers = new ArrayList<>();
        carrers.add(new Career("APU", 3));
        carrers.add(new Career("Lic. Informática", 5));
        carrers.add(new Career("Ingenieria en Sistemas", 5));
        carrers.add(new Career("Lic. Sistemas", 5));
        /*
         * Instance object adapter and set collection
         * */
        CareerRecViewAdapter adapter = new CareerRecViewAdapter();
        adapter.setCareers(carrers);
        /*
         * Set UI recycler view adapter instance
         * */
        //recyclerView.setAdapter(adapter);
        /*
         * Set LayoutManager to recycler view
         * */
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemPrefs){
            startActivity(new Intent(this, PrefsActivity.class));
            return true;
        }
        return false;
    }
}
