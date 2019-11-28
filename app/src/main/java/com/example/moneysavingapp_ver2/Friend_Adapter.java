package com.example.moneysavingapp_ver2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Friend_Adapter extends RecyclerView.Adapter<Friend_Adapter.Friend_ViewHolder> {

    private ArrayList<FriendList_item> mData = null;

    public Friend_Adapter(ArrayList<FriendList_item> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public Friend_Adapter.Friend_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.id_item,parent,false);
        return new Friend_Adapter.Friend_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Friend_Adapter.Friend_ViewHolder holder, int position) {
        int itemposition =position;
        holder.textView.setText(mData.get(itemposition).getFriend_name());

    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class Friend_ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public Friend_ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView1);

        }


    }
}
