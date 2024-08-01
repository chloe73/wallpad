package com.example.wallpad_ui_ver_1_1.fragment.ventilation;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.adapter.ViewPagerAdapter;
import com.example.wallpad_ui_ver_1_1.item.RoomVentilationItem;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class VentilationFragment extends Fragment {

    private Context mContext; // Fragment에서 사용할 context
    private View view;

    public VentilationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context; // context를 프래그먼트에 저장
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        this.view = view;
        Switch switchOnOff = view.findViewById(R.id.switch_onoff);

        ArrayList<RoomVentilationItem> list = getFixedItems();
        Log.d("list 정보", list.size()+"");
        Log.i("list item 정보", list.get(0).getName());

        // ViewPager 설정
        ViewPager2 viewPager2 = view.findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(list, mContext);
        viewPager2.setAdapter(viewPagerAdapter);

        // Circle indicator 연결
        CircleIndicator3 circleIndicator3 = view.findViewById(R.id.indicator);
        circleIndicator3.setViewPager(viewPager2);

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

        // 환기모드 이미지 터치하면, 다이얼로그 창 뜸.
        ImageView btnVentilationMode = view.findViewById(R.id.btn_ventilation_mode);
        btnVentilationMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showVentilationModeDialog();
            }
        });

        // 각방환기
        ImageView btnEachRoomVentilation = view.findViewById(R.id.btn_each_room_ventilation);
        btnEachRoomVentilation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEachRoomVentilation();
            }
        });

        // 풍량
        ImageView btnWindPower = view.findViewById(R.id.btn_wind_power);
        btnWindPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWindPowerSetting();
            }
        });
    }

    private void showWindPowerSetting() {
        DialogWindPowerSettingFragment dialogWindPowerSettingFragment = new DialogWindPowerSettingFragment();

        dialogWindPowerSettingFragment.show(getChildFragmentManager(), "DialogWindPowerSettingFragment");


    }

    // 하단 각방환기 버튼 클릭 시
    private void showEachRoomVentilation()  {
        DialogEachRoomVentilationFragment eachRoomVentilationFragment = new DialogEachRoomVentilationFragment();

        // Show the fragment as a dialog
        eachRoomVentilationFragment.show(getChildFragmentManager(), "DialogEachRoomVentilationFragment");
    }

    // 하단 환기모드 버튼 클릭 시
    private void showVentilationModeDialog() {
        // Create an instance of DialogVentilationModeFragment
        DialogVentilationModeFragment dialogFragment = new DialogVentilationModeFragment();

        // Show the dialog
        dialogFragment.show(getChildFragmentManager(), "DialogVentilationModeFragment");
    }

    private ArrayList<RoomVentilationItem> getFixedItems() {
        // 고정된 6개의 아이템을 생성
        ArrayList<RoomVentilationItem> items = new ArrayList<>();
        items.add(new RoomVentilationItem("거실", 1, 4));
        items.add(new RoomVentilationItem("침실",0, 1));
        items.add(new RoomVentilationItem("주방", 1, 2));
        items.add(new RoomVentilationItem("욕실", 0, 1));
        items.add(new RoomVentilationItem("서재", 1, 3));
        items.add(new RoomVentilationItem("다용도실", 0, 1));

        items.add(new RoomVentilationItem("침실2", 1, 2));
        items.add(new RoomVentilationItem("침실3", 0, 1));
        return items;
    }
}