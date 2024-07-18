package com.example.wallpad_ui_ver_1_1;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

     private final int divHeight;

     public RecyclerViewDecoration(int divHeight){
         this.divHeight = divHeight;
     }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();

        // 상하 설정
        if(position == 0 || position == 1){
            outRect.top = 10;
            outRect.bottom = 10;
        }
        else {
            outRect.bottom = 10;
        }
    }

}
