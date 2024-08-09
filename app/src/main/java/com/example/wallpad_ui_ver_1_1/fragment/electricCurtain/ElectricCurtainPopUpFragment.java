package com.example.wallpad_ui_ver_1_1.fragment.electricCurtain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.adapter.ElectricCurtainInRoomRecyclerViewAdapter;
import com.example.wallpad_ui_ver_1_1.adapter.ElectricCurtainRecyclerViewAdapter;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainRoomItem;

import java.util.ArrayList;

public class ElectricCurtainPopUpFragment extends Fragment {

    private ArrayList<ElectricCurtainRoomItem> list;
    private int idx;

    public ElectricCurtainPopUpFragment(ArrayList<ElectricCurtainRoomItem> list, int idx) {
        this.list = list;
        this.idx = idx;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_electric_curtain_pop_up, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_electric_curtain_in_room);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ElectricCurtainInRoomRecyclerViewAdapter electricCurtainInRoomRecyclerViewAdapter = new ElectricCurtainInRoomRecyclerViewAdapter(list, idx);
        recyclerView.setAdapter(electricCurtainInRoomRecyclerViewAdapter);

        // 리사이클러뷰 아이템 값 업데이트 시 화면 깜빡임 해결 코드
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        // 현재 방 이름 세팅
        EditText innerRoomName = view.findViewById(R.id.tv_inner_room_name);
        innerRoomName.setText(list.get(idx).getRoomName());
        innerRoomName.setEnabled(false);


        // 방 이름 수정 버튼
        ImageView btnEditRoomName = view.findViewById(R.id.btn_edit_room_name);
        btnEditRoomName.setOnClickListener(v -> {
            innerRoomName.setEnabled(true);
            // EditText에 포커스를 주고 키보드를 표시
            innerRoomName.setFocusableInTouchMode(true);  // 터치시 EditText에 포커스 가능하게 설정
            innerRoomName.requestFocus();  // EditText에 포커스 요청
            innerRoomName.setSelection(innerRoomName.getText().length());  // 기존 텍스트 끝에 커서 위치 설정

            // 키보드 표시
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(innerRoomName, InputMethodManager.SHOW_IMPLICIT);
            }

            // 포커스가 변경되었을 때(즉, 다른 곳을 클릭했을 때) 키보드 숨기기
            innerRoomName.setOnFocusChangeListener((v12, hasFocus) -> {
                Log.d("포커스 변경 ?", "포커스 변경 ~~~~~~~~~~~~~");
                if (!hasFocus) {
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(innerRoomName.getWindowToken(), 0);
                    }
                }
            });

        });
        // close 버튼
        ImageView closeBtn = view.findViewById(R.id.btn_close_in_electric_curtain_rv_item);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("closeBtn", "closeBtn");
                // 현재 프래그먼트 창 닫기
                closeFragment();
            }
        });

        // pop up fragment의 가장 바깥쪽 영역 터치했을 때, 창 없애기
        View constOuter = view.findViewById(R.id.const_electric_curtain_pop_up_outer);
        View innerView = view.findViewById(R.id.inner_const_electric_curtain_pop_up);

        constOuter.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // 터치된 좌표가 innerLayout 내부에 있는지 확인
                int[] location = new int[2];
                innerView.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                int width = innerView.getWidth();
                int height = innerView.getHeight();

                if (event.getRawX() < x || event.getRawX() > x + width || event.getRawY() < y || event.getRawY() > y + height) {
                    // innerLayout 외부를 터치한 경우
                    closeFragment();
                    return true;
                }
            }
            return false;
        });

        // 왼쪽 화살표
        ImageView leftArrow = view.findViewById(R.id.btn_left_item);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 현재 index 값이 0보다 큰 경우에만 동작한다.
                if (idx > 0) {
                    // 인덱스를 감소시키고 새로운 프래그먼트를 생성
                    ElectricCurtainPopUpFragment newFragment = new ElectricCurtainPopUpFragment(list, idx - 1);
                    FragmentManager fragmentManager = getParentFragmentManager();

                    // 프래그먼트를 교체하고 이전 스택을 지우기
                    fragmentManager.beginTransaction()
                            .replace(android.R.id.content, newFragment)
                            .commit();
                }
            }
        });

        // 오른쪽 화살표
        ImageView rightArrow = view.findViewById(R.id.btn_right_item);
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 현재 index 값이 list 크기보다 -1 미만인 경우에만 동작한다.
                if (idx < list.size() - 1) {
                    // 인덱스를 증가시키고 새로운 프래그먼트를 생성
                    ElectricCurtainPopUpFragment newFragment = new ElectricCurtainPopUpFragment(list, idx + 1);
                    FragmentManager fragmentManager = getParentFragmentManager();

                    // 프래그먼트를 교체하고 이전 스택을 지우기
                    fragmentManager.beginTransaction()
                            .replace(android.R.id.content, newFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    private void closeFragment() {
        FragmentManager fragmentManager = getParentFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(this).commit();
    }
}