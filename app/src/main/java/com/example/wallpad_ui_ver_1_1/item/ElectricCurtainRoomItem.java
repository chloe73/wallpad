package com.example.wallpad_ui_ver_1_1.item;

import java.util.ArrayList;

public class ElectricCurtainRoomItem {

    private String roomName; // 방 이름
    private int status; // 커튼이 열려 있는지, 닫혀 있는지, 여는 중인지
    // 0 : 닫힘, 1: 열림, 2: 여는 중
    private int value;

    public ArrayList<ElectricCurtainItem> getCurtainList() {
        return curtainList;
    }

    public void setCurtainList(ArrayList<ElectricCurtainItem> curtainList) {
        this.curtainList = curtainList;
    }

    private ArrayList<ElectricCurtainItem> curtainList;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ElectricCurtainRoomItem(String roomName, int status, int value, ArrayList<ElectricCurtainItem> list) {
        this.roomName = roomName;
        this.status = status;
        this.value = value;
        this.curtainList = list;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
