package com.fzuleta.academicstudyviewerapp;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.fzuleta.academicstudyviewerapp.db.DbAdapter;
import com.fzuleta.academicstudyviewerapp.models.Career;
import com.google.android.material.button.MaterialButton;

public class CareerActivity extends Activity implements View.OnClickListener {
    private EditText txtName, txtDuration;
    private Button btnNewCareer;
    private DbAdapter dbAdapter;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            Boolean ok = false;
            if (validTxt(ok)) {
                createNewCareer(dbAdapter);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career);
        txtName = findViewById(R.id.editName);
        txtDuration = findViewById(R.id.editDuration);
        btnNewCareer = findViewById(R.id.btnSave);
        /*
         *Instance dbAdapter for create a database and get records
         */
        dbAdapter = new DbAdapter(this);
    }

    private Boolean validTxt(Boolean state) {
        if (txtName.getText().toString().trim().isEmpty() || txtDuration.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Debe completar ambos datos", Toast.LENGTH_LONG).show();
        } else {
            state = true;
        }
        return state;
    }

    private void createNewCareer(DbAdapter dbAdapter) throws SQLException {
        try {
            dbAdapter.open();
            Career career = new Career(txtName.getText().toString(), Integer.parseInt(txtDuration.getText().toString()));
            dbAdapter.insertCareer(career);
            Toast.makeText(this, "Creado correctamente.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            dbAdapter.close();
        }
    }

}
