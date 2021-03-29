package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnlogin, btnsignup;
    private EditText edtuser, edtpass;
    private CheckBox cbremb, cbtac;
    private TextView tvforgot, tvida;
    public static ArrayList<Users> UsersA = new ArrayList<Users>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Default users
        CreateUser("Admin", "Admin", "ad@min.com", "admin",'M');
        CreateUser("User", "User", "us@er.com", "user",'M');
        CreateUser("Test", "Test", "tes@t.com", "test",'F');

//        Buttons
        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);
        btnsignup = (Button)findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(this);
        tvforgot = (TextView)findViewById(R.id.tvforgot);
        tvforgot.setOnClickListener(this);
        tvida = (TextView)findViewById(R.id.tvida);

//        CheckBoxs
        cbremb = (CheckBox)findViewById(R.id.cbremb);
        cbtac = (CheckBox)findViewById(R.id.cbtac);
        cbtac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btnlogin.setEnabled(true);
                }
                else{
                    btnlogin.setEnabled(false);
                }
            }
        });

//        EditTexts
        edtuser = (EditText)findViewById(R.id.edtuser);
        edtpass = (EditText)findViewById(R.id.edtpass);

        uploadPreferences();

    }

    private void uploadPreferences() {
        SharedPreferences preferences = getSharedPreferences("credencials", Context.MODE_PRIVATE);
        String userG = preferences.getString("user", "");
        String passG = preferences.getString("pass", "");

        edtuser.setText(userG);
        edtpass.setText(passG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin:
                if(validateEmpty()){
                    if(validateUser()){
                        tvida.setVisibility(v.INVISIBLE);
                        if(cbremb.isChecked()){
                            String userS = edtuser.getText().toString();
                            String passS = edtpass.getText().toString();
                            savePreferences(userS,passS);
                        }
                        else{
                            savePreferences("","");
                        }
                        Intent i2 = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(i2);
                    }
                    else{
                        tvida.setVisibility(v.VISIBLE);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Ingrese los datos.", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnsignup:
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
                break;

            case R.id.tvforgot:
                Intent i2 = new Intent(getApplicationContext(), ForgotActivity.class);
                startActivity(i2);
                break;
        }
    }

    private void savePreferences(String usera, String passa) {

        SharedPreferences preferences = getSharedPreferences("credencials", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", usera);
        editor.putString("pass", passa);
        editor.apply();
    }

    public static void CreateUser(String name, String LName, String email, String password, Character sex){
        Users UserAux = new Users(name,LName,email,password,sex);
        UsersA.add(UserAux);
    }

    public boolean validateEmpty(){
        if (TextUtils.isEmpty(edtuser.getText().toString())){
            return false;
        }
        else{
            if(TextUtils.isEmpty(edtpass.getText().toString())){
                return false;
            } else {
               return true;
            }
        }
    }

    public boolean validateUser(){
        for(Users user : UsersA){
            if(edtuser.getText().toString().equals(user.getEmail().toString())){

                if (edtpass.getText().toString().equals(user.getPassword().toString())){
                    return true;
                }
                break;
            }
        }
        return false;
    }
}
