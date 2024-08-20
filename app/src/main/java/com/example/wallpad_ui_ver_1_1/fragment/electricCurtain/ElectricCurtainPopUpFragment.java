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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.adapter.ElectricCurtainInRoomRecyclerViewAdapter;
import com.example.wallpad_ui_ver_1_1.adapter.ElectricCurtainRecyclerViewAdapter;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainRoomItem;
import com.example.wallpad_ui_ver_1_1.util.CustomLinearLayoutManager;

import java.util.ArrayList;

public class ElectricCurtainPopUpFragment extends Fragment {

    private ArrayList<ElectricCurtainRoomItem> list;
    private int idx;
    private ElectricCurtainInRoomRecyclerViewAdapter electricCurtainInRoomRecyclerViewAdapter;

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

        EditText innerRoomName = view.findViewById(R.id.tv_inner_room_name);

        RecyclerView recyclerView = view.findViewById(R.id.rv_electric_curtain_in_room);
        // CustomLinearLayoutManager 사용
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        electricCurtainInRoomRecyclerViewAdapter = new ElectricCurtainInRoomRecyclerViewAdapter(list, idx);
        recyclerView.setAdapter(electricCurtainInRoomRecyclerViewAdapter);

        // 리사이클러뷰 아이템 값 업데이트 시 화면 깜빡임 해결 코드
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        // 아이템 개수에 따른 스크롤 제어
        if (electricCurtainInRoomRecyclerViewAdapter.getItemCount() <= 2) {
            layoutManager.setScrollEnabled(false); // 스크롤 불가
            Log.d("스크롤 불가 처리", " 스크롤 불가 처리 했잖아!!!!!!!!!!");
        }
        else {
            layoutManager.setScrollEnabled(true); // 스크롤 가능
        }

        // 터치 상태를 추적할 플래그
        boolean[] isKeyboardHidden = {false};

        // 키보드가 활성화되었을 때, 리사이클러뷰 영역 터치해도 키보드 내려가게 하기
        recyclerView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN && innerRoomName.hasFocus()) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(innerRoomName.getWindowToken(), 0);
                }
                innerRoomName.clearFocus();
                innerRoomName.setEnabled(false);
                innerRoomName.setFocusable(false);
                innerRoomName.setFocusableInTouchMode(false);
                isKeyboardHidden[0] = true;
                return true;
            }
            return false;
        });

        // 키보드와 팝업 창을 제어할 리스너 설정
        View.OnTouchListener touchListener = (v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                // 첫 번째 터치: 키보드가 보이는 상태에서 화면을 터치했을 때
                if (innerRoomName.hasFocus() && !isKeyboardHidden[0]) {
                    Rect outRect = new Rect();
                    innerRoomName.getGlobalVisibleRect(outRect);
                    if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(innerRoomName.getWindowToken(), 0);
                        }
                        innerRoomName.clearFocus();
                        innerRoomName.setEnabled(false);
                        innerRoomName.setFocusable(false);
                        innerRoomName.setFocusableInTouchMode(false);
                        Log.d("화면의 다른 부분 터치!", "화면의 다른 부분 터치 !");
                        isKeyboardHidden[0] = true; // 키보드가 숨겨졌음을 기록
                        electricCurtainInRoomRecyclerViewAdapter.setButtonsEnabled(true);
                        return true;
                    }
                }

                // 두 번째 터치:
                // 키보드가 활성화되어 있지 않고 그냥 기본인 상태에서도 -> 화면을 터치하면 창이 닫혀야 함.
                // 키보드가 이미 숨겨진 상태에서 화면을 터치했을 때 팝업을 닫음
                if ( (!innerRoomName.hasFocus() && !isKeyboardHidden[0]) || isKeyboardHidden[0]) {
                    View innerView = view.findViewById(R.id.inner_const_electric_curtain_pop_up);
                    int[] location = new int[2];
                    innerView.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    int width = innerView.getWidth();
                    int height = innerView.getHeight();

                    // innerLayout 외부를 터치한 경우 팝업 닫기
                    if (event.getRawX() < x || event.getRawX() > x + width || event.getRawY() < y || event.getRawY() > y + height) {
                        closeFragment();
                        return true;
                    }
                }
            }
            return false;
        };

        // 리스너를 전체 레이아웃에 적용
        View constOuter = view.findViewById(R.id.const_electric_curtain_pop_up_outer);
        constOuter.setOnTouchListener(touchListener);

        // 현재 방 이름 세팅
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

            electricCurtainInRoomRecyclerViewAdapter.setButtonsEnabled(false);
            Log.d("리사이클러뷰 버튼 비활성화 !", "비활성화 !");

            // 키보드가 표시된 후 다시 터치 상태를 초기화
            isKeyboardHidden[0] = false;

            // Enter 키를 눌렀을 때 EditText 비활성화 처리
            innerRoomName.setOnEditorActionListener((v12, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(innerRoomName.getWindowToken(), 0);
                    }
                    innerRoomName.clearFocus();
                    innerRoomName.setEnabled(false);
                    innerRoomName.setFocusable(false);
                    innerRoomName.setFocusableInTouchMode(false);

                    electricCurtainInRoomRecyclerViewAdapter.setButtonsEnabled(true);
                    Log.d("키보드에서 enter키 누름 !", "키보드에서 enter키 누름 !");
                    //
                    return true;
                }
                return false;
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

                    fragmentManager.popBackStackImmediate();

                    // 프래그먼트를 교체하고 이전 스택을 지우기
                    fragmentManager.beginTransaction()
                            .replace(android.R.id.content, newFragment)
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