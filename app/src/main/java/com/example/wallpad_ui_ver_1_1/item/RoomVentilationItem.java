package com.example.wallpad_ui_ver_1_1.item;

import com.example.wallpad_ui_ver_1_1.R;

public class RoomVentilationItem {

    private String roomName; // 방 이름
    private int isOn; // 환기 on off 상태
    private int airStatus; // 각 방 공기질 상태

    public RoomVentilationItem(String roomName, int isOn, int airStatus) {
        this.isOn = isOn;
        this.roomName = roomName;
        this.airStatus = airStatus;
    }

    public int getAirStatus() {
        return airStatus;
    }

    public int getIsOn() {
        return isOn;
    }

    public String getName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setIsOn(int isOn) {
        this.isOn = isOn;
    }

    public void setAirStatus(int airStatus) {
        this.airStatus = airStatus;
    }
}
