package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin, btnSignUp;
    private ImageButton btnSeePass;
    private EditText edtUser, edtPass;
    private CheckBox cbRemember, cbTac;
    private TextView tvForgot, tvIda;
    public static ArrayList<Users> UsersA = new ArrayList<Users>();

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Default users
        CreateUser("Admin", "Admin", "ad@min.com", "admin",'M');
        CreateUser("User", "User", "us@er.com", "user",'M');
        CreateUser("Test", "Test", "tes@t.com", "test",'F');

//        Buttons
        btnLogin = (Button)findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(this);
        btnSignUp = (Button)findViewById(R.id.btnsignup);
        btnSignUp.setOnClickListener(this);
        btnSeePass = (ImageButton)findViewById(R.id.btnSeePass);
        btnSeePass.setOnClickListener(this);


        tvForgot = (TextView)findViewById(R.id.tvforgot);
        tvForgot.setOnClickListener(this);
        tvIda = (TextView)findViewById(R.id.tvida);

//        CheckBoxs
        cbRemember = (CheckBox)findViewById(R.id.cbremb);
        cbTac = (CheckBox)findViewById(R.id.cbtac);
        cbTac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btnLogin.setEnabled(true);
                }
                else{
                    btnLogin.setEnabled(false);
                }
            }
        });

//        EditTexts
        edtUser = (EditText)findViewById(R.id.edtuser);
        edtPass = (EditText)findViewById(R.id.edtpass);

        uploadPreferences();

    }

    private void uploadPreferences() {
        SharedPreferences preferences = getSharedPreferences("credencials", Context.MODE_PRIVATE);
        String userG = preferences.getString("user", "");
        String passG = preferences.getString("pass", "");

        edtUser.setText(userG);
        edtPass.setText(passG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin:
                if(validateEmpty()){
                    if(validateUser()){
                        tvIda.setVisibility(v.INVISIBLE);
                        if(cbRemember.isChecked()){
                            String userS = edtUser.getText().toString();
                            String passS = edtPass.getText().toString();
                            savePreferences(userS,passS);
                        }
                        else{
                            savePreferences("","");
                        }
                        Intent i2 = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(i2);
                    }
                    else{
                        tvIda.setText("DATOS INVÁLIOS");
                        tvIda.setVisibility(v.VISIBLE);
                    }
                }
                else{
                    tvIda.setText("INGRESE SUS DATOS");
                    tvIda.setVisibility(v.VISIBLE);
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

            case R.id.btnSeePass:
                if(flag == 0){
                    edtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                    flag = 1;
                }
                else{
                    edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    flag = 0;
                }
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
        if (TextUtils.isEmpty(edtUser.getText().toString())){
            return false;
        }
        else{
            if(TextUtils.isEmpty(edtPass.getText().toString())){
                return false;
            } else {
               return true;
            }
        }
    }

    public boolean validateUser(){
        for(Users user : UsersA){
            if(edtUser.getText().toString().equals(user.getEmail().toString())){

                if (edtPass.getText().toString().equals(user.getPassword().toString())){
                    return true;
                }
                break;
            }
        }
        return false;
    }
    @Override
    public void onBackPressed(){}
}
