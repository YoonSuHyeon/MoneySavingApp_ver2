package com.example.moneysavingapp_ver2;


import android.content.Intent;
import android.os.StrictMode;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Find_ID_PWActivity extends AppCompatActivity {
    Button email,code,btcomplete; //이메일
    EditText etemail,etcode;
    GMailSender gMailSender;
    private DatabaseReference database;
    String checkemail ;
    String dbemail ;
    String dbid ; //ID
    String dbpassword  ; // PASSWORD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__id__pw);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());  //이메일 사용을 위한 준비
        gMailSender = new GMailSender("tngus4753","youn4948!!"); //gmail 아이디 보내는사람
        database= FirebaseDatabase.getInstance().getReference(); //파이어베이스 설정
        email =findViewById(R.id.bt_email);
        code =findViewById(R.id.bt_code);
        etemail=findViewById(R.id.et_email);
        etcode =findViewById(R.id.et_code);
        btcomplete=findViewById(R.id.bt_complete);
        email.setOnClickListener(new View.OnClickListener() { // 이메일로 아이디를 찾기위한 버튼클릭시 하는일
            @Override
            public void onClick(View v) {

                //파이버베이스 접근후 이메일을 가지고있는 datasnapshot 을 찾아서  검사후 비밀번호와 아이디를 뽑아온다.
                DatabaseReference table_user = database.child("Users");
                ValueEventListener vListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean check =false; // 이메일 전송을 했는지 체크
                        for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                            Log.d("TAG1", "datattt : " + noteDataSnapshot);
                            Log.d("TAG1", "dataaaa : " + noteDataSnapshot.getChildren());
                            for (DataSnapshot da : dataSnapshot.getChildren()) {
                                if(check == true){
                                    break;
                                }
                                checkemail = etemail.getText().toString();
                                dbemail = da.child("email").getValue(String.class);

                                if (checkemail.equals(dbemail)) {
                                    try{

                                        check=true;
                                        dbid = da.child("id").getValue(String.class); //ID
                                        dbpassword = da.child("password").getValue(String.class); // PASSWORD
                                        gMailSender.sendMail("인증번호","인증번호는"+gMailSender.getEmailCode(),etemail.getText().toString());
                                        Log.d("TAG1", "d인증번호 : " + gMailSender.getEmailCode());
                                        Toast.makeText(getApplicationContext(), "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();
                                        break;

                                    }catch(SendFailedException e){
                                        Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                                    }catch(MessagingException e){
                                        e.printStackTrace();
                                        Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }

                                }
                                else{
                                    Toast.makeText(Find_ID_PWActivity.this, "이메일로 등록된 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                    }
                    @Override
                    public void onCancelled (@NonNull DatabaseError databaseError){

                    }

                };
                table_user.addListenerForSingleValueEvent(vListener);


            }
        });

        code.setOnClickListener(new View.OnClickListener() { //인증확인하는 버튼 클릭시
            @Override
            public void onClick(View v) {
                if(gMailSender.getEmailCode().equals(etcode.getText().toString())){ //이메일로보낸 인증코드와 입력한 인증코드가 같다면 아이디와 비밀번호를 보내준다.
                    try{
                        gMailSender.sendMail("사용자의 회원정보","사용자 iD="+dbid+"사용자 Password="+dbpassword,etemail.getText().toString());
                        Toast.makeText(Find_ID_PWActivity.this, "이메일로 아이디와 비밀번호가 전송되었습니다.", Toast.LENGTH_SHORT).show();

                    }catch(SendFailedException e){
                        Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    }catch(MessagingException e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
                else{
                    Toast.makeText(Find_ID_PWActivity.this, "인증번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btcomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent completeintent = new Intent(Find_ID_PWActivity.this,LoginActivity.class);
                startActivity(completeintent);
            }
        });
    }
}

