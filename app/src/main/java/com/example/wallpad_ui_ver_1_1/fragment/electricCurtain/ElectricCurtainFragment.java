package com.example.wallpad_ui_ver_1_1.fragment.electricCurtain;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.adapter.ElectricCurtainRecyclerViewAdapter;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainRoomItem;

import java.util.ArrayList;

public class ElectricCurtainFragment extends Fragment {

    private ArrayList<ElectricCurtainRoomItem> list;
    private Context context;

    private ConstraintLayout totalRoomOpenButton;
    private ConstraintLayout totalRoomCloseButton;

    public ElectricCurtainFragment(ArrayList<ElectricCurtainRoomItem> list) {
        this.list = list;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ElectricCurtainFragment", "onPause 호출됨");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("ElectricCurtainFragment", "onResume 호출됨");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_electric_curtain, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // RecyclerView Adapter 연결
        RecyclerView recyclerView = view.findViewById(R.id.rv_electric_curtain);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        // 리사이클러뷰 아이템 값 업데이트 시 화면 깜빡임 해결 코드
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        ElectricCurtainRecyclerViewAdapter electricCurtainRecyclerViewAdapter = new ElectricCurtainRecyclerViewAdapter(list);
        recyclerView.setAdapter(electricCurtainRecyclerViewAdapter);

        totalRoomOpenButton = view.findViewById(R.id.whole_room_open_container);
        totalRoomCloseButton = view.findViewById(R.id.whole_room_close_container);

        totalRoomOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                electricCurtainRecyclerViewAdapter.onOpenClick(recyclerView);
            }
        });

        totalRoomCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                electricCurtainRecyclerViewAdapter.onCloseClick(recyclerView);
            }
        });
    }

}