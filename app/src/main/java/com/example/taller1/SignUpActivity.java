package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.stream.Stream;

import static android.text.TextUtils.*;
import static java.util.stream.Stream.*;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnsave;
    private EditText edtname, edtlname, edtemailr, edtpassr;
    private Spinner spnsex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnsave = (Button)findViewById(R.id.btnsave);
        btnsave.setOnClickListener(this);

        edtname = (EditText)findViewById(R.id.edtname);
        edtlname = (EditText)findViewById(R.id.edtlname);
        edtemailr = (EditText)findViewById(R.id.edtemailr);
        edtpassr = (EditText)findViewById(R.id.edtpassr);

        spnsex = (Spinner)findViewById(R.id.spnsex);
        Character[] sex = {'M','F','O'};
        ArrayAdapter<Character> adapter = new ArrayAdapter<Character>(this, android.R.layout.simple_spinner_item,sex);
        spnsex.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsave:
                validateFields();
                break;
        }
    }

    private void validateFields() {
        String name = edtlname.getText().toString();
        String lname = edtlname.getText().toString();
        String email = edtemailr.getText().toString();
        String pass = edtpassr.getText().toString();
        char sex = (char)spnsex.getSelectedItem();

        if(!isEmpty(name) && !isEmpty(lname) && !isEmpty(email) && !isEmpty(pass)){
            if(pass.length() >= 6){
                MainActivity.CreateUser(name,lname,email,pass,sex);
                Toast.makeText(getApplicationContext(), "Usuario registrado correctamente.", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "La contraseña debe tener mínimo 6 caracteres.", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Rellene todos los campos.", Toast.LENGTH_LONG).show();
        }

    }
}