package com.example.wallpad_ui_ver_1_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wallpad_ui_ver_1_1.fragment.VentilationFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Fragment의 영역을 적용하기 위해서는 FragmentManager를 먼저 생성해주어야 함
        FragmentManager fragmentManager = getSupportFragmentManager();

        //프래그먼트 Transaction 획득
        //프래그먼트를 올리거나 교체하는 작업을 Transaction이라고 합니다.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.container_ventilation, new VentilationFragment());
        fragmentTransaction.commit();

    }
}