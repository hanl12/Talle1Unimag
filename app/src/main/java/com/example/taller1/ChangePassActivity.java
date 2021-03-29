package com.example.taller1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ChangePassActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtnpass, edtcpass;

    private Button btnchange;

    private int index;
    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        edtcpass = (EditText)findViewById(R.id.edtcpass);
        edtnpass = (EditText)findViewById(R.id.edtnpass);

        btnchange = (Button)findViewById(R.id.btnchange);
        btnchange.setOnClickListener(this);

        index = getIntent().getIntExtra("index",-1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnchange:
                if(index == -1){
                    Toast.makeText(getApplicationContext(), "Error inesperado." + index, Toast.LENGTH_LONG).show();
                }
                else{
                    validate();
                }
                break;
        }
    }

    private void validate() {
        String cpass = edtcpass.getText().toString();
        String npass = edtnpass.getText().toString();
        if(TextUtils.isEmpty(cpass) && TextUtils.isEmpty(npass)){
            Toast.makeText(getApplicationContext(), "Introduzca ambos datos.", Toast.LENGTH_LONG).show();
        }else{
            if (npass.equals(cpass)){
                if(npass.length() >= 6){
                    ArrayList<Users> Aux = MainActivity.UsersA;
                    Aux.get(index).setPassword(npass);
                    message = "Contraseña cambiada exitosamente";
                    createAlert(message);
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
                else{
                    message = "Las contraseñas deben tener mínimo 6 caracteres";
                    createAlert(message);
                }
            }
            else{
                message = "Las contraseñas no coinciden";
                createAlert(message);
            }
        }
    }

    private void createAlert(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("");
        alert.setMessage(message);
        alert.setPositiveButton("OK",null);
        alert.show();
    }
}