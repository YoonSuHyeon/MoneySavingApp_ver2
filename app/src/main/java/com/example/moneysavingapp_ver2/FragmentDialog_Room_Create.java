package com.example.moneysavingapp_ver2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.moneysavingapp_ver2.ui.tools.ToolsFragment;

public class FragmentDialog_Room_Create extends DialogFragment {
    Button btn_roomCancel;
    Button btn_roomOk;
    EditText et_roomname,et_spinnerResult;
    Spinner sp_spinner;

    private Fragment fragment;
    DialogResult result;
    public FragmentDialog_Room_Create() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 1000;
        params.height = 800;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams)params);
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view =inflater.inflate(R.layout.create_room,container,false);
        super.setCancelable(false);
        //Window window = view.getWindow();
        //window.setLayout(300,200);

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("approval");
        btn_roomOk = view.findViewById(R.id.btn_roomOk);
        et_spinnerResult=view.findViewById(R.id.et_spinner_result);
        et_roomname = view.findViewById(R.id.room_name);
        sp_spinner = view.findViewById(R.id.spinner);
        sp_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_spinnerResult.setText(sp_spinner.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_roomOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fragment != null){
                    if(result != null){
                        result.finish(et_roomname.getText().toString(),sp_spinner.getSelectedItem().toString());

                        Log.d("spinner",sp_spinner.getSelectedItem().toString());

                        DialogFragment dialogFragment = (DialogFragment)fragment;
                        dialogFragment.dismiss();
                    }
                }
            }
        });
        btn_roomCancel =view.findViewById(R.id.btn_roomCancel);
        btn_roomCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = (DialogFragment)fragment;
                dialogFragment.dismiss();
            }
        });

        return view;
    }
    public void setResult(DialogResult result){
        this.result=result;
    }

    public interface DialogResult{
        void finish(String result,String result2);
    }
}
