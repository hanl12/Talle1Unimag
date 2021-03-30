package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GeometryActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btncalculate, btnback;
    private EditText edtx1,edtx2,edty1,edty2;
    private Spinner spnoperationg;
    private TextView tvtextresults, tvresults;

    private final String op1 = "Calcular cuadrante", op2 = "Calcular pendiente entre los puntos", op3 = "Calcular distancia entre los puntos";
    private float x1 = 0, x2 = 0, y1 = 0, y2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry);

        btncalculate = (Button)findViewById(R.id.btncalculateg);
        btncalculate.setOnClickListener(this);
        btnback = (Button)findViewById(R.id.btnback4);
        btnback.setOnClickListener(this);


        edtx1 = (EditText)findViewById(R.id.edtx1);
        edtx2 = (EditText)findViewById(R.id.edtx2);
        edty1 = (EditText)findViewById(R.id.edty1);
        edty2 = (EditText)findViewById(R.id.edty2);

        tvtextresults = (TextView)findViewById(R.id.tvtextresults);

        spnoperationg = (Spinner)findViewById(R.id.spnoperationg);
        String[] operation = {op1,op2,op3};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,operation);
        spnoperationg.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncalculateg:
                if(validateFields()){
                    String option = spnoperationg.getSelectedItem().toString();
                    doOperation(option);
                    tvtextresults.setVisibility(v.VISIBLE);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Llene todos los campos.", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnback4:
                Intent i1 = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i1);
                break;
        }
    }

    private boolean validateFields() {
        String s1 = edtx1.getText().toString();
        String s2 = edtx2.getText().toString();
        String s3 = edty1.getText().toString();
        String s4 = edty2.getText().toString();

        if(!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2) && !TextUtils.isEmpty(s3) && !TextUtils.isEmpty(s4)){
            x1 = Float.parseFloat(s1);
            x2 = Float.parseFloat(s2);
            y1 = Float.parseFloat(s3);
            y2 = Float.parseFloat(s4);
            return true;
        }
        return false;
    }

    private void doOperation(String option) {
        switch (option){
            case op1:
                cuadrant();
                break;

            case op2:
                pending();
                break;

            case op3:
                distance();
                break;
        }
    }

    private void distance() {
        double X = x2-x1;
        double Y = y2-y1;

        double resultsP = Math.pow(X,2) + Math.pow(Y,2);
        double results = Math.round(resultsP*100.0)/100.0;;

        tvtextresults.setText("La distancia entre los puntos 1 y 2 es: " + results);
    }

    private void pending() {
        double results = 0;
        if(x2-x1 == 0){ }
        else {
            double resultsP = (y2-y1)/(x2-x1);
            results = Math.round(resultsP*100.0)/100.0;;
        }


        tvtextresults.setText("La pendiente entre los puntos es: " + results);
    }

    private void cuadrant(){
        String p1 = "";
        if(x1 > 0 && x2 > 0){ p1 = "1 está en el primer cuadrante"; }
        else if(x1 > 0 && y1 < 0){ p1 = "1 está en el cuarto cuadrante"; }
        else if(x1 < 0 && y1 < 0){ p1 = "1 está en el tercer cuadrante"; }
        else if(x1 < 0 && y1 > 0){ p1 = "1 está en el segundo cuadrante"; }
        else if (x1 == 0 && y1 == 0){ p1 = "1 está en el eje (0,0)"; }

        String p2 = "";
        if(x2 > 0 && y2 > 0){ p2 = "2 está en el primer cuadrante"; }
        else if(x2 > 0 && y2 < 0){ p2 = "2 está en el cuarto cuadrante"; }
        else if(x2 < 0 && y2 < 0){ p2 = "2 está en el tercer cuadrante"; }
        else if(x2 < 0 && y2 > 0){ p2 = "2 está en el segundo cuadrante"; }
        else if (x2 == 0 && y2 == 0){ p2 = "2 está en el eje (0,0)"; }

        tvtextresults.setText("El punto número " + p1 + " y el punto número " + p2);
    }
    @Override
    public void onBackPressed(){}
}