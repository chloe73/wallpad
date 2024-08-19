package com.example.wallpad_ui_ver_1_1.fragment.lighting;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.adapter.SmartLightingRoomAdapter;
import com.example.wallpad_ui_ver_1_1.item.SmartLightingRoomItem;

import java.util.ArrayList;

public class SmartLightingFragment extends Fragment {

    private ArrayList<SmartLightingRoomItem> list;

    public SmartLightingFragment(ArrayList<SmartLightingRoomItem> smartLighting) {
        this.list = smartLighting;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_smart_lighting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 각 방 스마트 조명 recyclerview 연결
        RecyclerView smartLightingRoomRecyclerView = view.findViewById(R.id.rv_smart_lighting_room);
        // 리사이클러뷰 레이아웃을 가로 스크롤 가능하게 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        smartLightingRoomRecyclerView.setLayoutManager(layoutManager);

        // 리사이클러뷰 아이템 값 업데이트 시 화면 깜빡임 해결 코드
        RecyclerView.ItemAnimator animator = smartLightingRoomRecyclerView.getItemAnimator();
        if(animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        SmartLightingRoomAdapter smartLightingRoomAdapter = new SmartLightingRoomAdapter(list, this::onItemSelected);
        smartLightingRoomRecyclerView.setAdapter(smartLightingRoomAdapter);

        loadSmartLightingRoomFragment(0);

    }

    private void onItemSelected(int position) {
        // 사용자가 선택한 아이템의 리스트를 가져와 Fragment를 업데이트
        loadSmartLightingRoomFragment(position);
    }

    private void loadSmartLightingRoomFragment(int idx) {

        // 각 방 내 스마트조명 리스트 fragment 올림
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_smart_lighting_room, new SmartLightingRoomFragment(list,idx));
        fragmentTransaction.commit();
    }
}