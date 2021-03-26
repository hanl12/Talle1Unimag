package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnlogin, btnsignup;
    private EditText edtuser, edtpass;
    private CheckBox cbremb, cbtac;
    private TextView tvforgot;
    private ArrayList<String> Users = new ArrayList<String>();
    private ArrayList<String> Passwords = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Default users and passwords
        Users.add("admin"); Users.add("user"); Users.add("user2");
        Passwords.add("admin"); Passwords.add("user"); Passwords.add("user2");

//        Buttons
        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);
        btnsignup = (Button)findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(this);
        tvforgot = (TextView)findViewById(R.id.tvforgot);
        tvforgot.setOnClickListener(this);

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin:
                if(validateEmpty()){
                    if(validateUser()){

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Datos incorrectos.", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Ingrese los datos.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public boolean validateEmpty(){
        if (TextUtils.isEmpty(edtuser.getText().toString()) || TextUtils.isDigitsOnly(edtuser.getText().toString())){
            return false;
        }

        else{
            if(TextUtils.isEmpty(edtpass.getText().toString()) || TextUtils.isDigitsOnly(edtpass.getText().toString())){
                return false;
            } else {
               return true;
            }
        }
    }

    public boolean validateUser(){
        for(String user : Users){
            if(edtuser.getText().toString().equals(user)){
                int index = Users.indexOf(user);

                if (edtpass.getText().toString().equals(Passwords.get(index))){
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public ArrayList getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<String> users) {
        Users = users;
    }
    public ArrayList getPasswords() {
        return Passwords;
    }

    public void setPasswords(ArrayList<String> passwords) {
        Passwords = passwords;
    }
}