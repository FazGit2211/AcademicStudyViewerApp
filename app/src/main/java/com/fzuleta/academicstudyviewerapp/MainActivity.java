package com.fzuleta.academicstudyviewerapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fzuleta.academicstudyviewerapp.db.DbAdapter;
import com.fzuleta.academicstudyviewerapp.models.Career;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add) {
            startActivity(new Intent(this, CareerActivity.class));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recView);
        MaterialButton btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);


        /*
         *Instance dbAdapter for create a database and get records
         */
        DbAdapter dbAdapter = new DbAdapter(this);
        loadCareers(recyclerView, dbAdapter);
    }

    private void loadCareers(RecyclerView recyclerView, DbAdapter dbAdapter) throws SQLException {
        try {
            dbAdapter.open();
            Cursor cursor = dbAdapter.getAllCareers();
            ArrayList<Career> careers = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    Career career = new Career(cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                    careers.add(career);
                } while (cursor.moveToNext());
                /*
                 * Instance object adapter and set collection
                 * */
                CareerRecViewAdapter adapter = new CareerRecViewAdapter();
                adapter.setCareers(careers);      /*
                 * Set UI recycler view adapter instance
                 * */
                recyclerView.setAdapter(adapter);
                /*
                 * Set LayoutManager to recycler view
                 * */
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            } else {
                Toast.makeText(this, "Empty careers", Toast.LENGTH_LONG).show();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            dbAdapter.close();
        }
    }

}
