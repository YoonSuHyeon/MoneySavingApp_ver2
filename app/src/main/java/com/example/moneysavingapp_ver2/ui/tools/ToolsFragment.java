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

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysavingapp_ver2.ChatRoom;
import com.example.moneysavingapp_ver2.ChatRoomAdapter;

import com.example.moneysavingapp_ver2.FragmentDialog_Room_Create;
import com.example.moneysavingapp_ver2.Message;
import com.example.moneysavingapp_ver2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ToolsFragment extends Fragment {
    private  String uid,nickname;
    private ArrayList<ChatRoom> roomlist;
    private ToolsViewModel toolsViewModel;
    private RecyclerView recyclerView;
    private ChatRoomAdapter cr_Adapter;
    private Button bt_roomcreat;
    private DatabaseReference database;
    String category,roomname;

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getActivity().setContentView(R.layout.create_room);

        Spinner spinner = getActivity().findViewById(R.id.spinner);



    }*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        roomlist= new ArrayList<ChatRoom>();
        database= FirebaseDatabase.getInstance().getReference();

        roomlist.add(new ChatRoom("방"));
        roomlist.add(new ChatRoom("방2"));

        Bundle bundle = getArguments();
        uid=bundle.getString("uid");
        nickname=bundle.getString("nickname");
        Log.d("assss",uid+":"+nickname);

        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_tools, container, false);
        bt_roomcreat = (Button) root.findViewById(R.id.bt_roomcreat);

        bt_roomcreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag","hihihihi");

                FragmentDialog_Room_Create dialog_room_create =new FragmentDialog_Room_Create();

                dialog_room_create.show(getFragmentManager(),"approval");
                dialog_room_create.setDialogResult(new FragmentDialog_Room_Create.OnMyDialogResult() {

                    @Override
                    public void finish(String result,String result1) {
                        Log.d("아아아",result);
                        Log.d("이이이이",result1);
                        // result에 dialog에서 보낸값이 저장되어 돌아옵니다.



                    }

                });

            }
        });
       // final TextView textView = root.findViewById(R.id.text_tools);



        //---------------------------------------------------------------------------------------------------
        recyclerView=root.findViewById(R.id.reclv_mychat);


        cr_Adapter =new ChatRoomAdapter(roomlist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cr_Adapter);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
       //         textView.setText(s);
            }
        });




        return root;
    }
}