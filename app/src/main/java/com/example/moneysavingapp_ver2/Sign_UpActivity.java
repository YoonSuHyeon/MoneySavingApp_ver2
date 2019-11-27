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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class Sign_UpActivity extends AppCompatActivity {
    private DatabaseReference database;
    private EditText nickname,userid,password,password2,useremail,useremailcode;
    private Button btn_nickname,btn_userid,btn_useremail,btn_useremailcode,btn_join,btn_cancel;
    GMailSender gMailSender;
    int checkuserid=0,checkusernickname=0,checkemail=0,checkcode=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        database= FirebaseDatabase.getInstance().getReference();
        nickname=findViewById(R.id.et_nickname);
        btn_nickname=findViewById(R.id.btn_nickname);
        userid=findViewById(R.id.et_userID);
        btn_userid=findViewById(R.id.btn_userID);
        password=findViewById(R.id.et_Password);
        password2=findViewById(R.id.et_Password2);
        useremail=findViewById(R.id.et_userEmail);
        btn_useremail=findViewById(R.id.btn_userEmail);
        useremailcode=findViewById(R.id.et_userMailcode);
        btn_useremailcode=findViewById(R.id.btn_userEmailcode);
        btn_cancel=findViewById(R.id.btn_cancel);
        btn_join=findViewById(R.id.btn_join);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());  //이메일 사용을 위한 준비
        gMailSender = new GMailSender("tngus4753","youn4948!!"); //gmail 아이디 보내는사람


        btn_nickname.setOnClickListener(new View.OnClickListener() {//닉네임 중복 버튼을 눌렀을때
            @Override
            public void onClick(View view) {
                //닉네임 중복확인
                DatabaseReference table_user = database.child("Users");
                ValueEventListener vListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                            for (DataSnapshot da : dataSnapshot.getChildren()) {
                                String mynickname = nickname.getText().toString();
                                String dbnickname = da.child("nickname").getValue(String.class);
                                if (mynickname.equals(dbnickname) ) {
                                    Toast.makeText(Sign_UpActivity.this, "중복된 닉네임 입니다.", Toast.LENGTH_SHORT).show();
                                    checkusernickname = 1; //1이면 중복된 닉네임이 존재할경우
                                    break;
                                }
                            }
                        }
                        if(nickname.getText().toString().equals("")){
                            Toast.makeText(Sign_UpActivity.this, "닉네임을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        }else if(checkusernickname==0){
                            checkusernickname=2;
                            Toast.makeText(Sign_UpActivity.this, "사용가능한 닉네임 입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled (@NonNull DatabaseError databaseError){

                    }

                };
                table_user.addListenerForSingleValueEvent(vListener);
            }
        });




        btn_userid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ID중복 확인
                DatabaseReference table_user = database.child("Users");
                ValueEventListener vListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                            for (DataSnapshot da : dataSnapshot.getChildren()) {
                                String id = userid.getText().toString();
                                String dbId = da.child("id").getValue(String.class);
                                if (id.equals(dbId)) {
                                    Toast.makeText(Sign_UpActivity.this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                                    checkuserid = 1; //1이면 중복된 아이디가 존재할경우
                                    break;
                                }
                            }
                        }
                        if(userid.getText().toString().equals("")){
                            Toast.makeText(Sign_UpActivity.this, "아이디를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        }else if(checkuserid==0){
                            checkuserid=2;
                            Toast.makeText(Sign_UpActivity.this, "사용가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled (@NonNull DatabaseError databaseError){

                    }

                };
                table_user.addListenerForSingleValueEvent(vListener);
            }
        });



        btn_useremail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //중복된 이메일이 있는지 확인
                DatabaseReference table_user = database.child("Users");
                ValueEventListener vListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                            for (DataSnapshot da : dataSnapshot.getChildren()) {
                                String myemail = useremail.getText().toString();
                                String dbemail = da.child("email").getValue(String.class);
                                if (myemail.equals(dbemail)) {
                                    Toast.makeText(Sign_UpActivity.this, "중복된 이메일입니다.", Toast.LENGTH_SHORT).show();
                                    checkemail = 1; //1이면 중복된 이메일이 존재할경우
                                    break;
                                }
                            }
                        }if(useremail.getText().toString().equals("")){
                            Toast.makeText(Sign_UpActivity.this, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        }else if(checkemail==0){
                            try{
                                checkemail=2;
                                gMailSender.sendMail("인증번호","인증번호는"+gMailSender.getEmailCode(),useremail.getText().toString());
                                Log.d("TAG1", "d인증번호 : " + gMailSender.getEmailCode());
                                Toast.makeText(getApplicationContext(), "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();


                            }catch(SendFailedException e){
                                Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                            }catch(MessagingException e){
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            Toast.makeText(Sign_UpActivity.this, "이메일코드가 전송 되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled (@NonNull DatabaseError databaseError){

                    }

                };
                table_user.addListenerForSingleValueEvent(vListener);
            }
        });

        btn_useremailcode.setOnClickListener(new View.OnClickListener() {//인증코드와 입력한 코드와 동일한지 확인
            @Override
            public void onClick(View view) {
                if(gMailSender.getEmailCode().equals(useremailcode.getText().toString())){
                    Toast.makeText(Sign_UpActivity.this, "인증완료", Toast.LENGTH_SHORT).show();
                    checkcode=2;
                }else{
                    Toast.makeText(Sign_UpActivity.this, "인증코드를 재입력 해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() { //취소 버튼을 눌렀을떄 하는일
            @Override
            public void onClick(View view) {
                Intent cancelintent=new Intent(Sign_UpActivity.this,LoginActivity.class);
                startActivity(cancelintent);
            }
        });

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkusernickname!=2){
                    Toast.makeText(Sign_UpActivity.this, "닉네임 중복확인을 해주세요", Toast.LENGTH_SHORT).show();
                }else if(checkuserid!=2){
                    Toast.makeText(Sign_UpActivity.this, "아이디 중복확인을 해주세요", Toast.LENGTH_SHORT).show();
                }else if(checkemail!=2){
                    Toast.makeText(Sign_UpActivity.this, "이메일 인증을 해주세요", Toast.LENGTH_SHORT).show();
                }else if(checkcode!=2){
                    Toast.makeText(Sign_UpActivity.this, "이메일 인증코드 확인을 해주세요", Toast.LENGTH_SHORT).show();
                } else if(!password.getText().toString().equals(password2.getText().toString()) ){
                    Toast.makeText(Sign_UpActivity.this, "비밀번호가 같지 않습니다.", Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().equals("") ||password2.getText().toString().equals("")||password.getText().toString().length()<8 ||password2.getText().toString().length()<8  ){
                    Toast.makeText(Sign_UpActivity.this, "비밀번호를 8자 이상 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
                else{//파이어베이스에 저장
                    DatabaseReference table_user = database.child("Users");
                    User user = new User(nickname.getText().toString(),userid.getText().toString(),password2.getText().toString(),useremail.getText().toString());
                    database.child("Users").push().setValue(user);
                    Toast.makeText(Sign_UpActivity.this, "회원가입했습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Sign_UpActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}

