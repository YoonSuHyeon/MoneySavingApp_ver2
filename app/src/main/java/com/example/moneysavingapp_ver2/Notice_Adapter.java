package com.example.moneysavingapp_ver2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Notice_Adapter extends RecyclerView.Adapter<Notice_Adapter.Notice_ViewHolder> {
    private ArrayList<Notice_Item> mData = null;

    public Notice_Adapter(ArrayList<Notice_Item> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public Notice_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_home,parent,false);
        return new Notice_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notice_Adapter.Notice_ViewHolder holder, int position) {
        int itemposition =position;
        holder.textView.setText(mData.get(itemposition).getTitle());
        holder.textView2.setText(mData.get(itemposition).getContent());
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class Notice_ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;

        public Notice_ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.tv_notice);
            this.textView2 = itemView.findViewById(R.id.tv_content);
        }


    }
}
