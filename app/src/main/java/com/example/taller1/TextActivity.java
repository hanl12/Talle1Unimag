package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TextActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btntransform;
    private EditText edttext;
    private Spinner spntext;

    private final String op1 = "Negrita", op2 = "Itálica", op3 = "Subrayada";
    private String aux = "";
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        btntransform = (Button)findViewById(R.id.btntransform);
        btntransform.setOnClickListener(this);

        edttext = (EditText)findViewById(R.id.edttext);

        spntext = (Spinner)findViewById(R.id.spntext);
        String[] options = {op1,op2,op3};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,options);
        spntext.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btntransform:
                if(!TextUtils.isEmpty(edttext.getText())){
                    String option = spntext.getSelectedItem().toString();
                    editText(option);
                }
                break;
        }
    }

    private void editText(String option) {
        switch (option){
            case op1:
                if(flag == 1){
                    edttext.setText(aux);
                    flag = 0;
                }
                edttext.setTypeface(null, Typeface.BOLD);
                break;

            case op2:
                if(flag == 1){
                    edttext.setText(aux);
                    flag = 0;
                }
                edttext.setTypeface(null, Typeface.ITALIC);
                break;

            case op3:
                String text = edttext.getText().toString();
                aux = text;
                flag = 1;
                SpannableString content = new SpannableString(text);
                content.setSpan(new UnderlineSpan(), 0, text.length(),0);
                edttext.setText(content);
                break;
        }
    }
}