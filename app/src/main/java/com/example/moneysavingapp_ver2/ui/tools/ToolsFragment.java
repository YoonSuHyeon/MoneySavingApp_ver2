package com.example.moneysavingapp_ver2.ui.tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysavingapp_ver2.ChatRoom;
import com.example.moneysavingapp_ver2.ChatRoomAdapter;

import com.example.moneysavingapp_ver2.EnterRoomActivity;
import com.example.moneysavingapp_ver2.FragmentDialog_Room_Create;
import com.example.moneysavingapp_ver2.Message;
import com.example.moneysavingapp_ver2.R;
import com.example.moneysavingapp_ver2.Sign_UpActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ToolsFragment extends Fragment {
    private  String uid,nickname;
    private ArrayList<ChatRoom> roomlist,templist;
    private ToolsViewModel toolsViewModel;
    private RecyclerView recyclerView;
    private ChatRoomAdapter cr_Adapter;
    private Button bt_roomcreat;
    private Button bt_category;
    private DatabaseReference database;
    String category,room_name;

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getActivity().setContentView(R.layout.create_room);

        Spinner spinner = getActivity().findViewById(R.id.spinner);



    }*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        database= FirebaseDatabase.getInstance().getReference(); //주소 연결해서 가져온거 파이어베이스
        roomlist= new ArrayList<ChatRoom>();
        templist = new ArrayList<ChatRoom>();



        Bundle bundle = getArguments();
        uid=bundle.getString("uid");
        nickname=bundle.getString("nickname");
        Log.d("assss",roomlist.toString());

        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_tools, container, false);
        bt_category = root.findViewById(R.id.bt_category);
        bt_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryDialog categoryDialog = new CategoryDialog();
                categoryDialog.show(getActivity().getSupportFragmentManager(),"category_approval");
                categoryDialog.setResult(new CategoryDialog.DialogCategoryResult() {
                    @Override
                    public void finish(final String result) {
                        //카테고리 = result 임
                        templist.clear();
                        database=FirebaseDatabase.getInstance().getReference().child("Chats");
                        ValueEventListener velistener = new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                                    if(noteDataSnapshot.getKey().equals(result)){
                                        for(DataSnapshot noDatas : noteDataSnapshot.getChildren()){
                                            ChatRoom chatRoom = new ChatRoom(noDatas.child("Name").getValue().toString());
                                            templist.add(chatRoom);
                                        }


                                    }
                                    if(templist.size()==0){
                                        Log.d("ss","없다");
                                        cr_Adapter.chatRooms.clear();
                                        cr_Adapter.notifyDataSetChanged();
                                    }else{
                                        cr_Adapter.chatRooms.clear();
                                        cr_Adapter.chatRooms.addAll(templist);
                                        cr_Adapter.notifyDataSetChanged();
                                    }



                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        };
                        database.addListenerForSingleValueEvent(velistener);
                       // cr_Adapter.chatRooms.removeAll();


                        //카테고리 별로 리스트 정렬되게 //
                    }
                });
            }
        });
        bt_roomcreat = (Button) root.findViewById(R.id.bt_roomcreat);
        bt_roomcreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag","hihihihi");

                FragmentDialog_Room_Create dialog_room_create = new FragmentDialog_Room_Create();

                dialog_room_create.show(getActivity().getSupportFragmentManager(),"approval");
                dialog_room_create.setResult(new FragmentDialog_Room_Create.DialogResult() {


                    @Override
                    public void finish(String result,String result1) {
                        //테스트
                        room_name = result;
                        category = result1;

                        if(result!=null&& result1!=null){ //카테고리 ,방이름이 null이 아닐 경우  방을 생성해준다.     방이름 중복 안되게 해야한다.
                            database = FirebaseDatabase.getInstance().getReference();
                            database =database.child("Chats").child(result1).push();
                            database.child("Name").setValue(room_name);
                            database.child("Member").push().setValue(nickname);
                            Message message = new Message("방이 개설되었습니다.",nickname);
                            database.child("Conversation").push().setValue(message);
                            database =FirebaseDatabase.getInstance().getReference();
                            database.child("Users").child(uid).child("myroom").push().setValue(room_name);
                        }
                        //Log.d("아아아",result);
                        //Log.d("이이이이",result1);
                        // result에 dialog에서 보낸값이 저장되어 돌아옵니다.



                    }
                });





            }
        });
       // final TextView textView = root.findViewById(R.id.text_tools);



        //---------------------------------------------------------------------------------------------------
        recyclerView=root.findViewById(R.id.reclv_mychat);




        database = FirebaseDatabase.getInstance().getReference();
        database = database.child("Chats");
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                roomlist.clear();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot da : noteDataSnapshot.getChildren()) {
                                ChatRoom chatRoom = new ChatRoom(da.child("Name").getValue().toString());
                                    roomlist.add(chatRoom);
                                    Log.d("ss", da.child("Name").getValue().toString());
                    }
                }

                cr_Adapter =new ChatRoomAdapter(roomlist);
                cr_Adapter.setOnItemClickListener(new ChatRoomAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, final int pos) {
                        Log.d("chatRoom",cr_Adapter.getItem(pos).getRoomname());

                        database = FirebaseDatabase.getInstance().getReference();



                        ValueEventListener vfListener = new ValueEventListener() { //채팅방에다가 내이름 적는것
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                                    if (noteDataSnapshot.getKey().equals("Chats")) {
                                        // category = noteDataSnapshot.getKey();

                                        for (DataSnapshot ds : noteDataSnapshot.getChildren()) {
                                            for (DataSnapshot ds2 : ds.getChildren()) {

                                                if(cr_Adapter.getItem(pos).getRoomname().equals(ds2.child("Name").getValue().toString())) {
                                                    //category = ds.getKey(); //카테고리
                                                    //rid = ds2.getKey();//rid
                                                    for(DataSnapshot dss :ds2.child("Member").getChildren()){
                                                        if(dss.getValue().toString().equals(nickname)){
                                                            return;
                                                        }

                                                    }
                                                    database.child("Chats").child(ds.getKey()).child(ds2.getKey()).child("Member").push().setValue(nickname);



                                                }
                                            }
                                        }
                                    }
                                    else if(noteDataSnapshot.getKey().equals("Users")){
                                        for (DataSnapshot ds : noteDataSnapshot.getChildren()) {
                                            Log.d("sadgasdg",ds.getKey());
                                            if(uid.equals(ds.getKey())){
                                                for(DataSnapshot dds : ds.child("myroom").getChildren()){
                                                    if(dds.getValue().toString().equals(cr_Adapter.getItem(pos).getRoomname()))
                                                        return;

                                                }
                                                database.child("Users").child(uid).child("myroom").push().setValue(cr_Adapter.getItem(pos).getRoomname());
                                                return;
                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        };
                        database.addListenerForSingleValueEvent(vfListener);

                        Intent intent1 = new Intent(getActivity(), EnterRoomActivity.class);
                        intent1.putExtra("uid",uid);
                        intent1.putExtra("roomname",cr_Adapter.getItem(pos).getRoomname());
                        intent1.putExtra("nickname",nickname);
                        startActivity(intent1);
                    }
                });
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(cr_Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };
        database.addListenerForSingleValueEvent(vListener);


        return root;
    }


}