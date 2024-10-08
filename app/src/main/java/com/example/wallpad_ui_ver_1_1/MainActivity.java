package com.example.wallpad_ui_ver_1_1;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.wallpad_ui_ver_1_1.fragment.electricCurtain.ElectricCurtainFragment;
import com.example.wallpad_ui_ver_1_1.fragment.lighting.SmartLightingFragment;
import com.example.wallpad_ui_ver_1_1.fragment.ventilation.DialogFilterChangeFragment;
import com.example.wallpad_ui_ver_1_1.fragment.ventilation.VentilationFragment;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainItem;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainRoomItem;
import com.example.wallpad_ui_ver_1_1.item.SmartLightingItem;
import com.example.wallpad_ui_ver_1_1.item.SmartLightingRoomItem;
import com.example.wallpad_ui_ver_1_1.viewModel.SmartLightingSharedViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SmartLightingSharedViewModel smartLightingSharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        // 전체 화면 모드 설정
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // 상태 표시줄과 내비게이션 바 숨기기
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        setContentView(R.layout.activity_main);

        // Fragment의 영역을 적용하기 위해서는 FragmentManager를 먼저 생성해주어야 함
        FragmentManager fragmentManager = getSupportFragmentManager();

        //프래그먼트 Transaction 획득
        //프래그먼트를 올리거나 교체하는 작업을 Transaction이라고 합니다.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        fragmentTransaction.add(R.id.container_ventilation, new VentilationFragment());
//        fragmentTransaction.commit();
//
//        // 필터교환 버튼 클릭 시
//        ImageView imageViewChangeFilter = findViewById(R.id.img_change_filter);
//        DialogFilterChangeFragment dialogFilterChangeFragment = new DialogFilterChangeFragment();
//        imageViewChangeFilter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialogFilterChangeFragment.show(fragmentManager, "DialogFilterChangeFragment");
//            }
//        });

        // 전동커튼 fragment가 Main Activity 화면에 올라가는 코드
//        fragmentTransaction.add(R.id.container_frag, new ElectricCurtainFragment(getElectricCurtain()));
//        fragmentTransaction.commit();

        // SmartLightingRoomItem 초기 데이터 세팅 & SharedViewModel 초기화
        smartLightingSharedViewModel = new ViewModelProvider(this).get(SmartLightingSharedViewModel.class);
        // 초기 데이터 설정
        smartLightingSharedViewModel.setSmartLightingRooms(getSmartLighting());
        // 스마트조명 fragment
        fragmentTransaction.add(R.id.container_frag, new SmartLightingFragment(smartLightingSharedViewModel));
        fragmentTransaction.commit();
    }

    private ArrayList<SmartLightingRoomItem> getSmartLighting() {
        ArrayList<SmartLightingRoomItem> list = new ArrayList<>();

        ArrayList<SmartLightingItem> a = new ArrayList<>();
        a.add(new SmartLightingItem(true, 99.9));
        a.add(new SmartLightingItem(false, 199.9));
        a.add(new SmartLightingItem(true, 9.9));
        a.add(new SmartLightingItem(false, 99.9));
        a.add(new SmartLightingItem(false, 10.99));
        list.add(new SmartLightingRoomItem("거실", true, 399.9, a));

        a = new ArrayList<>();
        a.add(new SmartLightingItem(false, 99.9));
        a.add(new SmartLightingItem(false, 99.9));
        a.add(new SmartLightingItem(false, 99.9));
        list.add(new SmartLightingRoomItem("방방방",false, 289.7, a));

        a = new ArrayList<>();
        a.add(new SmartLightingItem(true, 49.9));
        a.add(new SmartLightingItem(true, 59.9));
        list.add(new SmartLightingRoomItem("방방방방", true, 122.3, a));

        a = new ArrayList<>();
        a.add(new SmartLightingItem(true, 9.9));
        a.add(new SmartLightingItem(true, 9.9));
        list.add(new SmartLightingRoomItem("방3", true, 19.3, a));

        a = new ArrayList<>();
        a.add(new SmartLightingItem(false, 19.9));
        a.add(new SmartLightingItem(false, 29.9));
        list.add(new SmartLightingRoomItem("방4", false, 56.3, a));

        return list;
    }

    private ArrayList<ElectricCurtainRoomItem> getElectricCurtain() {
        ArrayList<ElectricCurtainRoomItem> list = new ArrayList<>();


        ArrayList<ElectricCurtainItem> a = new ArrayList<>();
        a.add(new ElectricCurtainItem(1,10));
        a.add(new ElectricCurtainItem(1,10));
        a.add(new ElectricCurtainItem(1,10));

        // 0 : 닫힘, 1: 열림
        list.add(new ElectricCurtainRoomItem("거실", 1, 10, a));

        a = new ArrayList<>();
        a.add(new ElectricCurtainItem(0,100));
        a.add(new ElectricCurtainItem(0,100));

        list.add(new ElectricCurtainRoomItem("안방", 0, 100, a));
        a = new ArrayList<>();
        a.add(new ElectricCurtainItem(1,10));
        list.add(new ElectricCurtainRoomItem("침실2", 1, 10, a));
        a = new ArrayList<>();
        a.add(new ElectricCurtainItem(0,100));
        list.add(new ElectricCurtainRoomItem("침실3", 0,100, a));

        return list;
    }
}