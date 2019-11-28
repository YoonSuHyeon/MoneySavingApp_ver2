package com.example.moneysavingapp_ver2.ui.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysavingapp_ver2.Notice_Adapter;
import com.example.moneysavingapp_ver2.Notice_Item;
import com.example.moneysavingapp_ver2.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private String uid,nickname;
    private RecyclerView recyclerView;
    private Notice_Adapter adapter;
    private ArrayList<Notice_Item> list = new ArrayList<>();
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView=(RecyclerView)rootView.findViewById(R.id.recycler_view);


        list.add(new Notice_Item("gdgd","gdgd"));
        list.add(new Notice_Item("ㅋㅋㅋ","gdgd"));
        list.add(new Notice_Item("gㅅㅄㅂ","gdgd"));
        adapter = new Notice_Adapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        //homeViewModel =
       //         ViewModelProviders.of(this).get(HomeViewModel.class);
        //View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        //homeViewModel.getText().observe(this, new Observer<String>() {
            //@Override
            //public void onChanged(@Nullable String s) {
        //        textView.setText(s);
           // }
        //});
        return rootView;
    }
}