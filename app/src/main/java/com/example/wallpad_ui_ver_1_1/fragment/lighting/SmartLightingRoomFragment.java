package com.example.wallpad_ui_ver_1_1.fragment.lighting;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.adapter.SmartLightingRoomInPieceAdapter;
import com.example.wallpad_ui_ver_1_1.item.SmartLightingRoomItem;

import java.util.ArrayList;

public class SmartLightingRoomFragment extends Fragment {

    private SmartLightingRoomItem item;

    public SmartLightingRoomFragment(ArrayList<SmartLightingRoomItem> list, int idx) {
        this.item = list.get(idx);
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

        TextView roomName = view.findViewById(R.id.tv_room_name_in_smart_lighting_fragment);
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
    }
}