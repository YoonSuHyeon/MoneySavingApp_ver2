package com.example.moneysavingapp_ver2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatViewHolder>{
    private OnItemLongClickListener mListener2 = null;
    private OnItemClickListener mListener = null;
    private  ArrayList<ChatRoom> chatRooms;

    public interface OnItemClickListener{
        void onItemClick (View v, int pos);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View v,int pos);
    }


    public void setOnLongClickListener(OnItemLongClickListener listener){
        this.mListener2 = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
    public class  ChatViewHolder extends RecyclerView.ViewHolder {
        public TextView roomname;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            this.roomname=(TextView) itemView.findViewById(R.id.roomname);

            /*itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        if(mListener2 !=null){
                            chatRooms.get(pos).getRoomname();
                            mListener2.onItemLongClick(v,pos);
                        }
                    }
                    return true;
                }
            });*/

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
    public void onBindViewHolder(@NonNull ChatViewHolder holder, final int position) {
        holder.roomname.setText(chatRooms.get(position).roomname);
        holder.roomname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = position;

                if(pos != RecyclerView.NO_POSITION){
                    if(mListener2 !=null){
                        chatRooms.get(pos).getRoomname();
                        mListener2.onItemLongClick(v,pos);
                    }
                }
                return true;

            }
        });
        holder.roomname.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                if(position != RecyclerView.NO_POSITION){
                    if(mListener != null){
                        mListener.onItemClick(v,position);
                    }
                }
                //Log.d("chatRoom",chatRooms.get(position).roomname);
            }
        });
    }
    public ChatRoom getItem(int pos){
        return chatRooms.get(pos);
    }


    @Override
    public int getItemCount() {
        return chatRooms.size();
    }
}
