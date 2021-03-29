package com.example.taller1.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.taller1.GeometryActivity;
import com.example.taller1.HomeActivity;
import com.example.taller1.R;
import com.example.taller1.TextActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private Button btngeometry,btntext,btnphisics;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        btngeometry = (Button)root.findViewById(R.id.btngeometry);
        btngeometry.setOnClickListener(this);

        btntext = (Button)root.findViewById(R.id.btntext);
        btntext.setOnClickListener(this);

        btnphisics = (Button)root.findViewById(R.id.btnphisics);
        btnphisics.setOnClickListener(this);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btngeometry:
                Intent i1 = new Intent(getActivity(), GeometryActivity.class);
                startActivity(i1);
                break;

            case R.id.btnphisics:

                break;

            case R.id.btntext:
                Intent i2 = new Intent(getActivity(), TextActivity.class);
                startActivity(i2);
                break;
        }
    }

}