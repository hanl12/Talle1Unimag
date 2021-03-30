package com.example.taller1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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

    private Button btnsave, btnback;
    private EditText edtname, edtlname, edtemailr, edtpassr;
    private Spinner spnsex;

    private String message = "";
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnsave = (Button)findViewById(R.id.btnsave);
        btnsave.setOnClickListener(this);
        btnback = (Button)findViewById(R.id.btnback3);
        btnback.setOnClickListener(this);


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

            case R.id.btnback3:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
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
                message = "Usuario registrado correctamente";
                flag = 1;
                createAlert(message);
            }
            else{
                message = "La contraseña debe tener mínimo 6 caracteres.";
                createAlert(message);
            }
        }
        else{
            message = "Rellene todos los campos.";
            createAlert(message);
        }
    }

    private void createAlert(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("");
        alert.setMessage(message);
        if(flag == 0){
            alert.setPositiveButton("OK",null);
        }
        else{
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    flag = 0;
                    startActivity(i);
                }
            });
        }
        alert.show();
    }

    @Override
    public void onBackPressed(){}
}