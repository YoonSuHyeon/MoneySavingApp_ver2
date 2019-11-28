package com.example.moneysavingapp_ver2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moneysavingapp_ver2.FriendList_item;
import com.example.moneysavingapp_ver2.Friend_Adapter;
import com.example.moneysavingapp_ver2.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    private String uid,nickname;
    private RecyclerView recyclerView;
    private Friend_Adapter adapter;
    private ArrayList<FriendList_item> list = new ArrayList<>();
   // private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_gallery,container,false);
        Bundle bundle = getArguments();
        uid=bundle.getString("uid");
        nickname=bundle.getString("nickname");

        recyclerView=(RecyclerView)rootView.findViewById(R.id.recycler_view);

        list.add(new FriendList_item("백동현"));
        list.add(new FriendList_item("윤수현"));
        list.add(new FriendList_item("최규원"));
        adapter = new Friend_Adapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        /*galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
       // final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
         //       textView.setText(s);
            }
        });*/
        return rootView;
    }
}