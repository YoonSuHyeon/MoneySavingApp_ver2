package com.example.moneysavingapp_ver2.ui.gallery;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.moneysavingapp_ver2.R;
import com.google.firebase.database.DatabaseReference;

public class FragmentDialog_findFriend extends DialogFragment {
    private Button btn_friendOK;
    private Button btn_friendCancel;
    private LinearLayout layout_gone;
    private EditText et_findID;
    private TextView finded_username;
    private DatabaseReference database;
    String btn_text;

    private Fragment fragment;
    DialogFindFriendResult result;

    public FragmentDialog_findFriend() {

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
        super.onCreateView(inflater, container, savedInstanceState);
        View view =inflater.inflate(R.layout.find_friend,container,false);

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("find_approval");
        btn_friendOK=view.findViewById(R.id.btn_friendOk);
        btn_friendCancel=view.findViewById(R.id.btn_friendCancel);
        layout_gone=view.findViewById(R.id.layout_gone);
        et_findID=view.findViewById(R.id.et_findID);
        finded_username=view.findViewById(R.id.finded_username);

        btn_friendOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //레이아웃 보이게 해주고  아이디 layout_gone
                //친구찾아서 텍스트뷰로 이름 띄워주기 텍스트뷰 아이디 finded_username
                //찾을 아이디 et_findID
                btn_text=btn_friendOK.getText().toString();
                if(btn_text.equals("OK")){
                    layout_gone.setVisibility(View.VISIBLE);
                    btn_friendOK.setText("친구 추가");

                    //레이아웃 보이게 하고 사용자가 있는지 검색해야함 검색완료후 띄워야함
                }else{
                    //창 닫고 검색된 사용자 이름 전달 그리고 없으면 null로 하고 버튼 이름을 다시OK로 바꿈
                }
            }
        });

        btn_friendCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = (DialogFragment)fragment;
                dialogFragment.dismiss();
            }
        });
        return view;

    }

    public void setResult(DialogFindFriendResult result){
        this.result=result;
    }

    public interface DialogFindFriendResult{
        void finish(String result);
    }
}
