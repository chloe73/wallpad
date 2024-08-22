package com.example.wallpad_ui_ver_1_1.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wallpad_ui_ver_1_1.item.SmartLightingRoomItem;

import java.util.ArrayList;

public class SmartLightingSharedViewModel extends ViewModel {

    // MutableLiveData를 사용하여 데이터를 변경 가능하도록 함
    private final MutableLiveData<ArrayList<SmartLightingRoomItem>> smartLightingRooms = new MutableLiveData<>();

    public void setSmartLightingRooms(ArrayList<SmartLightingRoomItem> rooms) {
        smartLightingRooms.setValue(rooms);
    }

    public LiveData<ArrayList<SmartLightingRoomItem>> getSmartLightingRooms() {
        return smartLightingRooms;
    }

}
