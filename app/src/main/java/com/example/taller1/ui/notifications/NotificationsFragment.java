package com.example.taller1.ui.notifications;

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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.taller1.MainActivity;
import com.example.taller1.R;

public class NotificationsFragment extends Fragment implements View.OnClickListener {

    private NotificationsViewModel notificationsViewModel;

    private Button btnlogout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        btnlogout = (Button)root.findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(this);

        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnlogout){

            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
            alert.setTitle("");
            alert.setMessage("Cerrando sesión");
            alert.show();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }

            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
        }
    }
}