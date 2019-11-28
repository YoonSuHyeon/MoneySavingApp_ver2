package com.example.moneysavingapp_ver2;

import java.util.ArrayList;

public class ChatRoom {

    String roomname;
    ArrayList<String> roomuser;

    public ChatRoom(String roomname, ArrayList<String> roomuser) {
        this.roomname = roomname;
        this.roomuser = roomuser;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public ArrayList<String> getRoomuser() {
        return roomuser;
    }

    public void setRoomuser(ArrayList<String> roomuser) {
        this.roomuser = roomuser;
    }

}
