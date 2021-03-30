package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btncalculatefor,btnback;
    private EditText edtmass, edtaceleration;
    private TextView tvresults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force);

        btncalculatefor = (Button)findViewById(R.id.btncalculatefor);
        btncalculatefor.setOnClickListener(this);

        btnback = (Button)findViewById(R.id.btnback9);
        btnback.setOnClickListener(this);

        edtmass = (EditText)findViewById(R.id.edtmass);
        edtaceleration = (EditText)findViewById(R.id.edtaceleration);

        tvresults = (TextView)findViewById(R.id.tvresultsfor);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncalculatefor:
                calculateForce();
                tvresults.setVisibility(v.VISIBLE);
                break;

            case R.id.btnback9:
                Intent i4 = new Intent(getApplicationContext(),PhisicsActivity.class);
                startActivity(i4);
                break;
        }
    }

    private void calculateForce() {
        String mass = edtmass.getText().toString();
        String acceleration = edtaceleration.getText().toString();

        if(!TextUtils.isEmpty(mass) && !TextUtils.isEmpty(acceleration)){
            float m = Float.parseFloat(mass);
            float a = Float.parseFloat(acceleration);

            double result = m * a;
            double results = Math.round(result*100.0)/100.0;
            tvresults.setText("El resultado de la fuerza es: " + results + " N");
        }
    }
    @Override
    public void onBackPressed(){}
}