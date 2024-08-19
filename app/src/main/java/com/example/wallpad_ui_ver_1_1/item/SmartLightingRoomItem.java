package com.example.wallpad_ui_ver_1_1.item;

import java.util.ArrayList;

public class SmartLightingRoomItem {
    private String roomName;
    private boolean isOn;
    private double wat;
    private ArrayList<SmartLightingItem> lightingList;

    public SmartLightingRoomItem(String roomName, boolean isOn, double wat, ArrayList<SmartLightingItem> lightingList) {
        this.roomName = roomName;
        this.isOn = isOn;
        this.wat = wat;
        this.lightingList = lightingList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public ArrayList<SmartLightingItem> getLightingList() {
        return lightingList;
    }

    public void setLightingList(ArrayList<SmartLightingItem> lightingList) {
        this.lightingList = lightingList;
    }

    public double getWat() {
        return wat;
    }

    public void setWat(float wat) {
        this.wat = wat;
    }
}
