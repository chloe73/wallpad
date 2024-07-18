package com.example.wallpad_ui_ver_1_1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.wallpad_ui_ver_1_1.adapter.VentilationAdapter;
import com.example.wallpad_ui_ver_1_1.item.RoomVentilationItem;

import java.util.ArrayList;
import java.util.List;

public class VentilationFragment extends Fragment {

    private Context mContext; // Fragment에서 사용할 context

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public VentilationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context; // context를 프래그먼트에 저장
    }

    public static VentilationFragment newInstance(String param1, String param2) {
        VentilationFragment fragment = new VentilationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\\


        return inflater.inflate(R.layout.fragment_ventilation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Switch switchOnOff = view.findViewById(R.id.switch_onoff);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        // GridLayoutManager 설정 (3개의 열로 구성)
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(layoutManager);

        // Adapter 설정
        VentilationAdapter ventilationAdapter = new VentilationAdapter();
        recyclerView.setAdapter(ventilationAdapter);

        ArrayList<RoomVentilationItem> list = getFixedItems();
        Log.d("list 정보", list.size()+"");
        Log.i("list item 정보", list.get(0).getName());
        ventilationAdapter.setItems(list);
        ventilationAdapter.notifyDataSetChanged();

        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getContext(), "스위치가 켜졌습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "스위치가 꺼졌습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ArrayList<RoomVentilationItem> getFixedItems() {
        // 고정된 6개의 아이템을 생성
        ArrayList<RoomVentilationItem> items = new ArrayList<>();
        items.add(new RoomVentilationItem("거실", 1));
        items.add(new RoomVentilationItem("침실",0));
        items.add(new RoomVentilationItem("주방", 1));
        items.add(new RoomVentilationItem("욕실", 0));
        items.add(new RoomVentilationItem("서재", 1));
        items.add(new RoomVentilationItem("다용도실", 0));
        return items;
    }
}