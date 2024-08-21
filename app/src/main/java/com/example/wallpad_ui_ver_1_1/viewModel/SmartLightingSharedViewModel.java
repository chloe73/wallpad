package com.example.wallpad_ui_ver_1_1.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SmartLightingSharedViewModel extends ViewModel {

    private final MutableLiveData<String> roomName = new MutableLiveData<>();

    public void setRoomName(String name) {
        roomName.setValue(name);
    }

    public LiveData<String> getRoomName() {
        return roomName;
    }

}
