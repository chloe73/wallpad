package com.example.wallpad_ui_ver_1_1.item;

public class ElectricCurtain {
    private int status; // 커튼이 열려 있는지, 닫혀 있는지, 여는 중인지
    // 0 : 닫힘, 1: 열림, 2: 여는 중
    private int value;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ElectricCurtain(int status, int value) {
        this.status = status;
        this.value = value;
    }
}
