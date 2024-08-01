package com.example.wallpad_ui_ver_1_1.fragment.electricCurtain;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private SeekBar seekBarLeft;
    private SeekBar seekBarRight;
    private ImageView leftCurtain;
    private ImageView rightCurtain;
    private ConstraintLayout openButton;
    private ConstraintLayout closeButton;
    private ConstraintLayout pauseButton;

    public ElectricCurtainFragment(ArrayList<ElectricCurtainRoomItem> list) {
        this.list = list;
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

        ElectricCurtainRecyclerViewAdapter electricCurtainRecyclerViewAdapter = new ElectricCurtainRecyclerViewAdapter(list);
        recyclerView.setAdapter(electricCurtainRecyclerViewAdapter);

        // 전동 커튼 view 변수

//        seekBarLeft.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                adjustCurtains(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {}
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {}
//        });
//
//        seekBarRight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                adjustCurtains(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {}
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {}
//        });
    }

    private void openCurtains() {
        int progress = 100; // Assuming 100 is fully open
        seekBarLeft.setProgress(progress);
        seekBarRight.setProgress(progress);
        adjustCurtains(progress);
    }

    private void closeCurtains() {
        int progress = 0; // Assuming 0 is fully closed
        seekBarLeft.setProgress(progress);
        seekBarRight.setProgress(progress);
        adjustCurtains(progress);
    }

    private void pauseCurtains() {
        // Pause functionality can be implemented as needed
    }

    private void adjustCurtains(int progress) {
        int maxCurtainWidth = 346; // Initial width of curtains in px
        int newWidth = (int) (maxCurtainWidth * (progress / 100.0));

        ConstraintLayout.LayoutParams layoutParamsLeft = (ConstraintLayout.LayoutParams) leftCurtain.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParamsRight = (ConstraintLayout.LayoutParams) rightCurtain.getLayoutParams();

        layoutParamsLeft.width = newWidth;
        layoutParamsRight.width = newWidth;

        leftCurtain.setLayoutParams(layoutParamsLeft);
        rightCurtain.setLayoutParams(layoutParamsRight);
    }

}