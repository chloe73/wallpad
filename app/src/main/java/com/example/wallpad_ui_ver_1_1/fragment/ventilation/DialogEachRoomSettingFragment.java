package com.example.wallpad_ui_ver_1_1.fragment.ventilation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.item.RoomVentilationItem;

public class DialogEachRoomSettingFragment extends DialogFragment {

    private RoomVentilationItem item;

    public DialogEachRoomSettingFragment(RoomVentilationItem item) {
        this.item = item;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Inflate the custom dialog layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_dialog_each_room_setting, null);

        ImageView btnClose = dialogView.findViewById(R.id.btn_close_each_room_setting_dialog);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        TextView textViewRoomName = dialogView.findViewById(R.id.tv_room_name);
        textViewRoomName.setText(item.getName());

        Switch switchIsOn = dialogView.findViewById(R.id.switch_each_room_is_on_off);
        if(item.getIsOn() == 1){
            switchIsOn.setChecked(true);
        }
        else {
            switchIsOn.setChecked(false);
        }

        TextView textViewAirStatus = dialogView.findViewById(R.id.tv_room_air_status_value);
        ImageView imageViewAirStatus = dialogView.findViewById(R.id.img_air_status);
        switch (item.getAirStatus()) {
            case 1:
                textViewAirStatus.setText("매우좋음");
                imageViewAirStatus.setBackgroundResource(R.drawable.img_air_status_1);
                break;
            case 2:
                textViewAirStatus.setText("보통");
                imageViewAirStatus.setBackgroundResource(R.drawable.img_air_status_2);
                break;
            case 3:
                textViewAirStatus.setText("나쁨");
                imageViewAirStatus.setBackgroundResource(R.drawable.img_air_status_3);
                break;
            case 4:
                textViewAirStatus.setText("매우나쁨");
                imageViewAirStatus.setBackgroundResource(R.drawable.img_air_status_4);
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);

        Dialog dialog = builder.create();

        // 시스템 바 숨기기 설정 추가
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        return dialog;
    }
}