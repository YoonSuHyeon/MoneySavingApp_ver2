package com.example.moneysavingapp_ver2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EnterRoomActivity extends AppCompatActivity {
    private  String uid,roomname,nickname,category,rid;
    private DatabaseReference database;
    private TextView tv_roomname;
    private EditText et_message;
    private Button  bt_message;
    private RecyclerView reclv_room;
    private FirebaseRecyclerAdapter<Message,MessageViewHolder> mFirebaseAdapter;





    @Override
    protected void onStop() {

        super.onStop();
        mFirebaseAdapter.stopListening();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_room);
        tv_roomname=findViewById(R.id.tv_roomname);
        bt_message =findViewById(R.id.bt_message);
        et_message=findViewById(R.id.et_message);
        reclv_room=findViewById(R.id.reclv_room);




        uid=getIntent().getStringExtra("uid");
        roomname=getIntent().getStringExtra("roomname");
        nickname=getIntent().getStringExtra("nickname");
        tv_roomname.setText(roomname);

        database =FirebaseDatabase.getInstance().getReference();
        database.child("Chats");


        ValueEventListener vListener = new ValueEventListener() { //  database 주소 가져오기
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    if(noteDataSnapshot.getKey().equals("Chats")){
                       // category = noteDataSnapshot.getKey();

                        for(DataSnapshot ds :noteDataSnapshot.getChildren()){
                          for(DataSnapshot ds2 : ds.getChildren()){
                              if(roomname.equals(ds2.child("Name").getValue().toString())){
                                 category =ds.getKey();
                                 rid =ds2.getKey();

                                  Query query =  database.child("Chats").child(category).child(rid).child("Conversation");
                                  FirebaseRecyclerOptions<Message> options = new FirebaseRecyclerOptions.Builder<Message>().setQuery(query,Message.class).build();
                                  mFirebaseAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(options) {

                                      protected void onBindViewHolder( MessageViewHolder messageViewHolder, int position,  Message model) {
                                          messageViewHolder.textView2.setText(model.getSender());
                                          messageViewHolder.textView.setText(model.getMessage());
                                      }

                                      @NonNull

                                      public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int a) {
                                          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);
                                          return new MessageViewHolder(view);
                                      }


                                  };
                                  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(EnterRoomActivity.this);
                                  reclv_room.setLayoutManager(mLayoutManager);
                                  reclv_room.setItemAnimator(new DefaultItemAnimator());
                                  reclv_room.setAdapter(mFirebaseAdapter);

                                  Log.d("zxzcvzxcv", String.valueOf(mFirebaseAdapter.getItemCount()));
                                  mFirebaseAdapter.startListening();
                                  return;
                              }
                          }
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        database.addListenerForSingleValueEvent(vListener);






        bt_message.setOnClickListener(new View.OnClickListener() { //전송하기
            @Override
            public void onClick(View v) {
                Log.d("ddd",category + "    " +rid);
                Message message = new Message(et_message.getText().toString(),nickname);
                database =FirebaseDatabase.getInstance().getReference();
                database.child("Chats").child(category).child(rid).child("Conversation").push().setValue(message);
                et_message.setText("");
            }
        });
        Log.d("zxcvzx",uid);
        Log.d("qwieu",roomname);
    }
    static class MessageViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;

         MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.tv_message);
            this.textView2 = itemView.findViewById(R.id.tv_nickname);
        }


    }
}
