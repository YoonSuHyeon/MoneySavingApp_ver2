package com.example.moneysavingapp_ver2.ui.tools;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
       // final TextView textView = root.findViewById(R.id.text_tools);
        recyclerView=root.findViewById(R.id.reclv_mychat);
        bt_roomcreat=root.findViewById(R.id.bt_roomcreat);

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