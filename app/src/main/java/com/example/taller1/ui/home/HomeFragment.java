package com.example.taller1.ui.home;

import android.content.Context;
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

import com.example.taller1.HomeActivity;
import com.example.taller1.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private Button btngeometry;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        btngeometry = (Button)root.findViewById(R.id.btngeometry);
        btngeometry.setOnClickListener(this);

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
                Toast.makeText(getActivity(), "Ingrese los datos.", Toast.LENGTH_LONG).show();
                break;
        }
    }

}