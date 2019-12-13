package com.example.moneysavingapp_ver2.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.moneysavingapp_ver2.R;

public class FragmentDialog_longClick extends DialogFragment {
    private Button btn_exitRoom;
    private TextView tv_roomName;
    private Fragment fragment;
    String item;

    public FragmentDialog_longClick(String item) {
        this.item = item;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 800;
        params.height = 600;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams)params);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view =inflater.inflate(R.layout.delete_room,container,false);

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("deleteRoom_approval");
        btn_exitRoom = view.findViewById(R.id.exit_room);
        tv_roomName = view.findViewById(R.id.tv_longRoom_name);
        tv_roomName.setText(item);

        return view;
    }
}
