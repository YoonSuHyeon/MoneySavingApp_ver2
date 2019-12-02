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
    Button btn_roomOk;
    EditText room_name;
    OnMyDialogResult mDialogResult;
    private Fragment fragment;
    static  FragmentDialog_Room_Create newInstance(String category,String roomname){
        FragmentDialog_Room_Create f =new FragmentDialog_Room_Create();

        Bundle args = new Bundle();
        args.putString("category",category);
        args.putString("roomname",roomname);
        f.setArguments(args);
        return  f;
    }
    public FragmentDialog_Room_Create() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 800;
        params.height = 800;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams)params);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view =inflater.inflate(R.layout.create_room,container,false);

        btn_roomOk =view.findViewById(R.id.btn_roomOk);
        btn_roomCancel =view.findViewById(R.id.btn_roomCancel);
        room_name=view.findViewById(R.id.room_name);



        btn_roomOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mDialogResult != null ){

                    mDialogResult.finish(room_name.getText().toString(),"카테고리");

                }

               dismiss();



            }
        });

        btn_roomCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag","hihizzzzzzzzzzhih");
                dismiss();
            }
        });
        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("approval");


        return view;
    }
    public void setDialogResult(OnMyDialogResult dialogResult){

        mDialogResult = dialogResult;

    }



    public interface OnMyDialogResult{

        void finish(String result,String result1);

    }




}

