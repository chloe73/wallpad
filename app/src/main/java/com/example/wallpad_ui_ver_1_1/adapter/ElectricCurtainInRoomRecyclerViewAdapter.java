package com.example.wallpad_ui_ver_1_1.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainRoomItem;

import java.util.ArrayList;

public class ElectricCurtainInRoomRecyclerViewAdapter extends RecyclerView.Adapter<ElectricCurtainInRoomRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ElectricCurtainRoomItem> list;

    public ElectricCurtainInRoomRecyclerViewAdapter(ArrayList<ElectricCurtainRoomItem> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public ElectricCurtainInRoomRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ElectricCurtainInRoomRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView innerRoomName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            innerRoomName = itemView.findViewById(R.id.tv_inner_room_name);
        }

        void onBind(ElectricCurtainRoomItem item) {
            innerRoomName.setText(item.getRoomName());

        }
    }
}
