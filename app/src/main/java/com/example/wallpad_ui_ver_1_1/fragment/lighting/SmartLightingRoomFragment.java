package com.example.wallpad_ui_ver_1_1.fragment.lighting;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.adapter.SmartLightingRoomInPieceAdapter;
import com.example.wallpad_ui_ver_1_1.item.SmartLightingRoomItem;
import com.example.wallpad_ui_ver_1_1.viewModel.SmartLightingSharedViewModel;

import java.util.ArrayList;

public class SmartLightingRoomFragment extends Fragment {

    private int idx;
    private ArrayList<SmartLightingRoomItem> list;
    private SmartLightingSharedViewModel smartLightingSharedViewModel;
    private SmartLightingRoomItem item;

    public SmartLightingRoomFragment(ArrayList<SmartLightingRoomItem> list, int idx, SmartLightingSharedViewModel smartLightingSharedViewModel) {
        this.idx = idx;
        this.list = list;
        this.item = list.get(idx);
        this.smartLightingSharedViewModel = smartLightingSharedViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_smart_lighting_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 방 이름 세팅
        EditText roomName = view.findViewById(R.id.tv_room_name_in_smart_lighting_fragment);
        roomName.setText(item.getRoomName());

        // 각 방 내부 스마트 조명 recyclerview 연결
        RecyclerView smartLightingRoomInPieceRecyclerView = view.findViewById(R.id.rv_smart_lighting_room_in_piece);
        smartLightingRoomInPieceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // adapter 연결
        SmartLightingRoomInPieceAdapter smartLightingRoomInPieceAdapter = new SmartLightingRoomInPieceAdapter(item);
        smartLightingRoomInPieceRecyclerView.setAdapter(smartLightingRoomInPieceAdapter);

        // 리사이클러뷰 아이템 값 업데이트 시 화면 깜빡임 해결 코드
        RecyclerView.ItemAnimator animator = smartLightingRoomInPieceRecyclerView.getItemAnimator();
        if(animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        // 방 이름 수정 버튼 구현
        ImageView btnEditRoomName = view.findViewById(R.id.img_edit_room_name_in_smart_lighting_room_fragment);
        btnEditRoomName.setOnClickListener(view1 -> {
            roomName.requestFocus();  // EditText에 포커스 요청
            roomName.setSelection(roomName.getText().length());  // 기존 텍스트 끝에 커서 위치 설정

            // 키보드 표시
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(roomName, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        // 엔터 키 이벤트 처리
        roomName.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                // 현재 입력된 텍스트 가져오기
                String newName = roomName.getText().toString();

                // 방 이름 수정
                item.setRoomName(newName);
                list.get(idx).setRoomName(newName);
                smartLightingSharedViewModel.setSmartLightingRooms(list);
                Log.d("SmartLightingRoomFragment에서 사용자가 방 이름 바꿈!", ""+smartLightingSharedViewModel.getSmartLightingRooms().getValue().get(idx).getRoomName());

                // 엔터 키 입력 시 키보드 숨기기 -> 방 이름 수정 완료 처리
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {

                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return true; // 이벤트 소비, 엔터 키 처리 안 함
            }
            return false;
        });
    }
}