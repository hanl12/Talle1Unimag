package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class VoltageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btncalculatevol;
    private EditText edtamp, edtr1,edtr2,edtr3;
    private Spinner spnresis;
    private TextView tvamp5, tvr1o3, tvresults;
    private CheckBox cbpar;

    private int flag = 2;
    private double RES = 0,result = 0, Amp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voltage);

        btncalculatevol = (Button)findViewById(R.id.btncalculatevol);
        btncalculatevol.setOnClickListener(this);

        edtamp = (EditText)findViewById(R.id.edtAmp);
        edtr1 = (EditText)findViewById(R.id.edtr1);
        edtr2 = (EditText)findViewById(R.id.edtr2);
        edtr3 = (EditText)findViewById(R.id.edtr3);

        tvamp5 = (TextView)findViewById(R.id.tvamp5);
        tvr1o3 = (TextView)findViewById(R.id.tvr1o3);
        tvresults = (TextView)findViewById(R.id.tvresultsvol);

        cbpar = (CheckBox)findViewById(R.id.cbpar);
        spnresis = (Spinner)findViewById(R.id.spnresis);
        Integer[] options = {2,3};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,options);
        spnresis.setAdapter(adapter);

        spnresis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        edtr3.setVisibility(view.INVISIBLE);
                        tvamp5.setVisibility(view.INVISIBLE);
                        tvr1o3.setVisibility(view.INVISIBLE);
                        flag = 2;
                        break;

                    case 1:
                        edtr3.setVisibility(view.VISIBLE);
                        tvamp5.setVisibility(view.VISIBLE);
                        tvr1o3.setVisibility(view.VISIBLE);
                        flag = 3;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncalculatevol:
                calculateVoltage();
                tvresults.setVisibility(v.VISIBLE);
                break;
        }
    }

    private void calculateVoltage() {
        RES = 0; Amp = 0;
        validateResis();
        result = Amp * RES;
        double results = Math.round(result*100.0)/100.0;
        tvresults.setText("El resultado del voltaje es: " + results);
    }

    private void validateResis() {
        String r1 = edtr1.getText().toString();
        String r2 = edtr2.getText().toString();
        String r3 = edtr3.getText().toString();
        String A = edtamp.getText().toString();

        if(!TextUtils.isEmpty(r1) && !TextUtils.isEmpty(r2) && !TextUtils.isEmpty(A)){
            float R1 = Float.parseFloat(r1);
            float R2 = Float.parseFloat(r2);
            Amp = Float.parseFloat(A);
            if (flag == 3 && !TextUtils.isEmpty(r3)){
                float R3 = Float.parseFloat(r3);
                if(cbpar.isChecked()){
                    RES = (1/R1) + (1/R2) + (1/R3);
                }
                else{
                    RES = R1 + R2 + R3;
                }
            }
            else if (flag == 2){
                if(cbpar.isChecked()){
                    RES = (1/R1) + (1/R2);
                }
                else{
                    RES = R1 + R2;
                }
            }
            else{
                Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_LONG).show();
        }
    }

}