package com.example.taller1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtemail, edtcode;
    private Button btncode, btnvalidate, btnback;
    private TextView tvcode;
    public int index = -1;

    public String code = "XYZ987";
    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

//        EditTexts
        edtcode = (EditText)findViewById(R.id.edtcode);
        edtemail = (EditText)findViewById(R.id.edtemail);
        tvcode = (TextView)findViewById(R.id.tvcode);

//        Buttons
        btncode = (Button)findViewById(R.id.btncode);
        btncode.setOnClickListener(this);
        btnvalidate = (Button)findViewById(R.id.btnvalidate);
        btnvalidate.setOnClickListener(this);
        btnback = (Button)findViewById(R.id.btnback2);
        btnback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncode:
                index = validateUser();
                if(index == -1){
                    message = "El usuario no se encuentra registrado";
                    createAlert(message);
                }
                else{
                    edtcode.setVisibility(v.VISIBLE);
                    btnvalidate.setVisibility(v.VISIBLE);
                    tvcode.setVisibility(v.VISIBLE);
                    message = "Código enviado";
                    createAlert(message);
                    Toast.makeText(getApplicationContext(), "CÓDIGO: XYZ987", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnvalidate:
                if(edtcode.getText().toString().equals(code) && index != -1){
                    Intent i = new Intent(getApplicationContext(),ChangePassActivity.class);
                    i.putExtra("index",index);
                    startActivity(i);
                }
                else {
                    String message = "Código incorrecto";
                    createAlert(message);
                }
                break;

            case R.id.btnback2:
                Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i1);
                break;
        }
    }

    private void createAlert(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Recuperación de contraseña");
        alert.setMessage(message);
        alert.setPositiveButton("OK",null);
        alert.show();
    }


    public Integer validateUser(){

        String email = edtemail.getText().toString();
        ArrayList<Users> Aux = MainActivity.UsersA;

        for(Users user : Aux){
            if(email.equals(user.getEmail().toString())){
                return Aux.indexOf(user);
            }
        }
        return -1;
    }
    @Override
    public void onBackPressed(){}
}