package com.example.moneysavingapp_ver2;

import android.content.Context;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Friend_Adapter extends RecyclerView.Adapter<Friend_Adapter.Friend_ViewHolder> {
    private OnItemLongClickListener mListener = null;
    private ArrayList<FriendList_item> mData = null;

    public interface OnItemLongClickListener{
        void onItemLongClick(View v,int pos);
    }

    public void setOnLongClickListener(OnItemLongClickListener listener){
        this.mListener = listener;
    }
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
    public FriendList_item getItem(int pos){
        return mData.get(pos);
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class Friend_ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public Friend_ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView1);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        if(mListener !=null){
                            mData.get(pos).getFriend_name();
                            mListener.onItemLongClick(v,pos);
                        }
                    }
                    return true;
                }
            });
        }


    }
}
