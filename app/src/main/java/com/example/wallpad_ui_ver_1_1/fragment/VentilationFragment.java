package com.example.wallpad_ui_ver_1_1.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.adapter.VentilationAdapter;
import com.example.wallpad_ui_ver_1_1.adapter.ViewPagerAdapter;
import com.example.wallpad_ui_ver_1_1.item.RoomVentilationItem;

import java.sql.Array;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class VentilationFragment extends Fragment {

    private Context mContext; // Fragment에서 사용할 context

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

        fragment.setArguments(args);
        return fragment;
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

        items.add(new RoomVentilationItem("침실2", 1));
        items.add(new RoomVentilationItem("침실3", 0));
        return items;
    }
}