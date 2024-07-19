package com.example.wallpad_ui_ver_1_1.item;

import com.example.wallpad_ui_ver_1_1.R;

public class RoomVentilationItem {

    private String roomName;
    private int isOn;

    public RoomVentilationItem(String roomName, int isOn) {
        this.isOn = isOn;
        this.roomName = roomName;
    }

    public int getIsOn() {
        return isOn;
    }

    public String getName() {
        return roomName;
    }
}
