package com.example.moneysavingapp_ver2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatViewHolder>{

    private  ArrayList<ChatRoom> chatRooms;
    public class  ChatViewHolder extends RecyclerView.ViewHolder {
        public TextView roomname;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            roomname=(TextView) itemView.findViewById(R.id.roomname);
        }
    }

    public ChatRoomAdapter(ArrayList<ChatRoom> chatRooms){
        this.chatRooms=chatRooms;
    }
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_item,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.roomname.setText(chatRooms.get(position).roomname);
        holder.roomname.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }



    @Override
    public int getItemCount() {
        return chatRooms.size();
    }
}
