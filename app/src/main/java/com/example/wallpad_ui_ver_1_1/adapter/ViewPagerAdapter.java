package com.example.wallpad_ui_ver_1_1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.SpacesItemDecoration;
import com.example.wallpad_ui_ver_1_1.item.RoomVentilationItem;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private ArrayList<RecyclerView> recyclerViewArrayList;
    private ArrayList<RoomVentilationItem> items;
    private Context mContext;

    public ViewPagerAdapter(ArrayList<RoomVentilationItem> list, Context mContext){
        this.items = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager, parent, false);
        return new ViewHolder(view);
    }

    // ViewHolder에 데이터를 바인딩해주는 함수
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<RoomVentilationItem> pageItems = getPageItems(position);

        // 여기에 VentilationAdapter 연결 - RecyclerViewAdapter 연결
        // GridLayoutManager 설정 (3개의 열로 구성)
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        holder.recyclerView.setLayoutManager(layoutManager);

        VentilationAdapter ventilationAdapter = new VentilationAdapter();
        holder.recyclerView.setAdapter(ventilationAdapter);

        // 여기서 recyclerView item decoration 간격 설정
        holder.recyclerView.addItemDecoration(new SpacesItemDecoration(8));

        Log.d("list 정보", items.size()+"");
        Log.i("list item 정보", items.get(0).getName());
        ventilationAdapter.setItems(pageItems);
        ventilationAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // 아이템의 개수에 따라 페이지 수를 결정
        return (items.size() + 5) / 6; // 6개 단위로 페이지 나누기
    }

    private List<RoomVentilationItem> getPageItems(int page) {
        int start = page * 6;
        int end = Math.min(start + 6, items.size());
        return items.subList(start, end);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }

}
