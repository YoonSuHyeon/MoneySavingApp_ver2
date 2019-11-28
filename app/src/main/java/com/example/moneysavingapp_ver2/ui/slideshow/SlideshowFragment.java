package com.example.moneysavingapp_ver2.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.moneysavingapp_ver2.R;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {
    private ArrayList<ChatRoom> roomlist;
    private ArrayList<String> roomuserlist;
    private SlideshowViewModel slideshowViewModel;
    private RecyclerView recyclerView;
    private ChatRoomAdapter cr_Adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        roomlist= new ArrayList<ChatRoom>();
        roomuserlist = new ArrayList<String>();
        roomuserlist.add("윤수현");
        roomlist.add(new ChatRoom("방",roomuserlist));
        roomlist.add(new ChatRoom("방2",roomuserlist));

        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
       // final TextView textView = root.findViewById(R.id.text_slideshow);
        recyclerView=root.findViewById(R.id.reclv_mychat);
        cr_Adapter =new ChatRoomAdapter(roomlist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cr_Adapter);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
           //     textView.setText(s);
            }
        });
        return root;
    }
}