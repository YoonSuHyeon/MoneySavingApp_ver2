package com.example.moneysavingapp_ver2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.moneysavingapp_ver2.R;

public class FragmentDialog_longClick extends DialogFragment {
    private TextView li_deleteFriend;
    private Fragment fragment;
    private TextView tv_userNickname;
    String item;
    public FragmentDialog_longClick(String item) {
        this.item = item;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 500;
        params.height = 300;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams)params);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view =inflater.inflate(R.layout.friend_longclick,container,false);


        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("deleteFriend_approval");
        tv_userNickname = view.findViewById(R.id.tv_longUserId);
        tv_userNickname.setText(item);
        li_deleteFriend=view.findViewById(R.id.delete_friend);
        li_deleteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //친구삭제 버튼 클릭시 이벤트 처리 코드 작성
            }
        });

        return view;
    }
}
