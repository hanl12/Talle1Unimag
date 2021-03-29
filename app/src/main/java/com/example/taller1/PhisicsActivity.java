package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhisicsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnvelocity, btnforce, btnvoltage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phisics);

        btnvelocity = (Button)findViewById(R.id.btnvelocity);
        btnvelocity.setOnClickListener(this);

        btnforce = (Button)findViewById(R.id.btnforce);
        btnforce.setOnClickListener(this);

        btnvoltage = (Button)findViewById(R.id.btnvoltage);
        btnvoltage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnvelocity:
                Intent i1 = new Intent(getApplicationContext(),VelocityActivity.class);
                startActivity(i1);
                break;

            case R.id.btnforce:
                Intent i2 = new Intent(getApplicationContext(),ForceActivity.class);
                startActivity(i2);
                break;

            case R.id.btnvoltage:
                Intent i3 = new Intent(getApplicationContext(),VoltageActivity.class);
                startActivity(i3);
                break;
        }
    }
}