package com.example.wallpad_ui_ver_1_1.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wallpad_ui_ver_1_1.item.SmartLightingRoomItem;

public class SmartLightingSharedViewModel extends ViewModel {

    // MutableLiveData를 사용하여 데이터를 변경 가능하도록 함
    private final MutableLiveData<SmartLightingRoomItem> smartLightingRoomItem = new MutableLiveData<>();

    // 데이터를 업데이트할 수 있는 메서드
    public void setSmartLightingRoomItem(SmartLightingRoomItem item) {
        smartLightingRoomItem.setValue(item);
    }

    // 데이터를 관찰할 수 있는 메서드
    public LiveData<SmartLightingRoomItem> getSmartLightingRoomItem() {
        return smartLightingRoomItem;
    }

    // 방 이름을 업데이트하는 메서드
    public void updateRoomName(String newRoomName) {
        SmartLightingRoomItem currentItem = smartLightingRoomItem.getValue();
        if (currentItem != null) {
            currentItem.setRoomName(newRoomName);
            smartLightingRoomItem.setValue(currentItem); // 변경된 데이터를 다시 설정
        }
    }

    // 방의 전원 상태를 업데이트하는 메서드
    public void updateRoomOnState(boolean isOn) {
        SmartLightingRoomItem currentItem = smartLightingRoomItem.getValue();
        if (currentItem != null) {
            currentItem.setOn(isOn);
            smartLightingRoomItem.setValue(currentItem); // 변경된 데이터를 다시 설정
        }
    }

}
