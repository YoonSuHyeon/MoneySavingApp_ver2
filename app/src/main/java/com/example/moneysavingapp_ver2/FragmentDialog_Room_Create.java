package com.example.moneysavingapp_ver2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class FragmentDialog_Room_Create extends DialogFragment {
    Button btn_roomCancel;

    public FragmentDialog_Room_Create() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view =inflater.inflate(R.layout.create_room,container,false);


        btn_roomCancel =view.findViewById(R.id.btn_roomCancel);
        btn_roomCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag","hihizzzzzzzzzzhih");
                dismiss();
            }
        });

        return view;
    }
}
