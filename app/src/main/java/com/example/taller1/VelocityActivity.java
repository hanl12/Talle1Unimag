package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VelocityActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btncalculatevel;
    private EditText edtdistance, edttime;
    private TextView tvresults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocity);

        btncalculatevel = (Button)findViewById(R.id.btncalculatevel);
        btncalculatevel.setOnClickListener(this);

        edtdistance = (EditText)findViewById(R.id.edtdistance);
        edttime = (EditText)findViewById(R.id.edttime);

        tvresults = (TextView)findViewById(R.id.tvresultsvel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncalculatevel:
                calculateVelocity();
                tvresults.setVisibility(v.VISIBLE);
                break;
        }
    }

    private void calculateVelocity() {
        String distance = edtdistance.getText().toString();
        String time = edttime.getText().toString();

        if(!TextUtils.isEmpty(distance) && !TextUtils.isEmpty(time)){
            float d = Float.parseFloat(distance);
            float t = Float.parseFloat(time);

            double result = d/t;
            double results = Math.round(result*100.0)/100.0;
            tvresults.setText("El resultado de la velocidad es: " + results + " km/h");
        }

    }
}