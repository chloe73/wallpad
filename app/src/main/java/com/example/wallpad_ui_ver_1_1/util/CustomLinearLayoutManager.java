package com.example.wallpad_ui_ver_1_1.util;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;

public class CustomLinearLayoutManager extends LinearLayoutManager {

    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        // 스크롤 가능 여부에 따라 스크롤을 허용하거나 비활성화
        return isScrollEnabled && super.canScrollVertically();
    }
}
