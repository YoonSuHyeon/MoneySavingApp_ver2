package com.example.moneysavingapp_ver2.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class FragmentDialogRoom_longClick extends DialogFragment {
    Button btn_exitRoom;
    int click_count=0;
    private TextView tv_roomName;
    Fragment fragment;
    String item;
    String uid;
    String nickname;
    private DatabaseReference database;
    public FragmentDialogRoom_longClick(String item,String uid,String nickname) {
        this.item = item;
        this.uid = uid;
        this.nickname =nickname;
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
        btn_exitRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_count++;
                if(click_count==1){
                    Toast.makeText(view.getContext(),"정말로 방을 나가시겠습니까?",Toast.LENGTH_SHORT).show();

                }else{
                    database= FirebaseDatabase.getInstance().getReference();
                    ValueEventListener velistener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot dst : dataSnapshot.getChildren()){
                                if(dst.getKey().equals("Users")){
                                    for(DataSnapshot ddst : dst.child(uid).child("myroom").getChildren()){
                                        database.child("Users").child(uid).child("myroom").child(ddst.getKey()).removeValue();
                                        break;
                                    }
                                }else if(dst.getKey().equals("Chats")){
                                    for(DataSnapshot ggst : dst.getChildren()){
                                        for(DataSnapshot ggst2 : ggst.getChildren()){
                                            if(ggst2.child("Name").getValue().toString().equals(item)){
                                                for(DataSnapshot ggst3 : ggst2.child("Member").getChildren()){
                                                    if(ggst3.getValue().equals(nickname)){
                                                        if(ggst2.child("Member").getChildrenCount()==1.0){
                                                            database.child("Chats").child(ggst.getKey()).child(ggst2.getKey()).removeValue();
                                                        }
                                                        database.child("Chats").child(ggst.getKey()).child(ggst2.getKey()).child("Member").child(ggst3.getKey()).removeValue();
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    dismiss();
                    database.addListenerForSingleValueEvent(velistener);

                    //방 나가기 버튼 눌렀을 시 이벤트 !!
                }

            }
        });


        return view;
    }
}
