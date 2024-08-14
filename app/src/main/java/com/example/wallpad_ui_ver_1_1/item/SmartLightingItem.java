package com.example.wallpad_ui_ver_1_1.item;

public class SmartLightingItem {
    private boolean isOn;
    private double wat;

    public SmartLightingItem(boolean isOn, double wat) {
        this.isOn = isOn;
        this.wat = wat;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public double getWat() {
        return wat;
    }

    public void setWat(float wat) {
        this.wat = wat;
    }
}
