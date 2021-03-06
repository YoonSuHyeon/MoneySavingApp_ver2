package com.example.moneysavingapp_ver2.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.moneysavingapp_ver2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentDialog_longClick extends DialogFragment {
    private Button li_deleteFriend;
    Fragment fragment;
    private TextView tv_userNickname;
    String item,uid;
    private DatabaseReference database;
    public FragmentDialog_longClick(String item,String uid) {
        this.item = item;
        this.uid =uid;
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
        final View view =inflater.inflate(R.layout.friend_longclick,container,false);


        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("deleteFriend_approval");
        tv_userNickname = view.findViewById(R.id.tv_longUserId);
        tv_userNickname.setText(item);
        li_deleteFriend=view.findViewById(R.id.delete_friend);
        li_deleteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //친구삭제 버튼 클릭시 이벤트 처리 코드 작성

                database = FirebaseDatabase.getInstance().getReference();
                ValueEventListener vfListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot noteDataSnapshot : dataSnapshot.child("Users").child(uid).child("Friend").getChildren()){

                            if(noteDataSnapshot.getValue().toString().equals(item)){
                                Log.d("asdfasdzxc",noteDataSnapshot.getKey());
                                database.child("Users").child(uid).child("Friend").child(noteDataSnapshot.getKey()).removeValue();
                                break;
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };

                dismiss();
                database.addListenerForSingleValueEvent(vfListener);

            }
        });

        return view;
    }
}
