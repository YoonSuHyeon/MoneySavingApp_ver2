package com.example.moneysavingapp_ver2.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.moneysavingapp_ver2.MakePeople;
import com.example.moneysavingapp_ver2.Question;
import com.example.moneysavingapp_ver2.R;

public class SendFragment extends Fragment {
    private TextView ver,question,make;
    private SendViewModel sendViewModel;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        root = inflater.inflate(R.layout.fragment_send, container, false);
//        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        question = root.findViewById(R.id.ask_quetion); //문의하기
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Question.class);
                root.getContext().startActivity(intent);
            }
        });
        ver = root.findViewById(R.id.ask_make);
        ver.setOnClickListener(new View.OnClickListener() { //버전정보
            @Override
            public void onClick(View v) {
                Toast.makeText(root.getContext(),"Ver 1.0입니다.",Toast.LENGTH_SHORT).show();
            }
        });
        make = root.findViewById(R.id.ask_ver);
        make.setOnClickListener(new View.OnClickListener() { //만든사람들
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), MakePeople.class);
                root.getContext().startActivity(intent);
            }
        });

        return root;
    }
}