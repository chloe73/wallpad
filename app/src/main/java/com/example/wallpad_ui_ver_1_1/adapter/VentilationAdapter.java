package com.example.wallpad_ui_ver_1_1.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.item.RoomVentilationItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class VentilationAdapter extends RecyclerView.Adapter<VentilationAdapter.ViewHolder> {

    private ArrayList<RoomVentilationItem> items;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ventilation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.onBind(items.get(position));

        RoomVentilationItem item = items.get(position);
        holder.textView.setText(item.getName());
        Log.d("onBindViewHolder !!!", item.getName());
    }

    @Override
    public int getItemCount() {
        Log.d("list size", items.size()+"");
        return items.size();
    }

    public void setItems(ArrayList<RoomVentilationItem> items) {
        this.items = items;
        Log.d("adapter class", items.get(0).getName());
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView vantilationImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("viewHolder", "------");
            textView = itemView.findViewById(R.id.room_name);
            vantilationImage = itemView.findViewById(R.id.room_vantilation_onoff);
        }

        void onBind(RoomVentilationItem item){
            textView.setText(item.getName());
            // 환기가 꺼져있을 경우에는 환기팬 이미지 off로 변경
            if (item.getIsOn() == 0){
                vantilationImage.setBackgroundResource(R.drawable.ventilation_off);
            }
        }
    }
}
