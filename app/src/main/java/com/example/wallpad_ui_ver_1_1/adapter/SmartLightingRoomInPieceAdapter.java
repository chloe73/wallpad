package com.example.wallpad_ui_ver_1_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.item.SmartLightingItem;
import com.example.wallpad_ui_ver_1_1.item.SmartLightingRoomItem;

import java.util.ArrayList;

public class SmartLightingRoomInPieceAdapter extends RecyclerView.Adapter<SmartLightingRoomInPieceAdapter.ViewHolder> {

    private SmartLightingRoomItem item;

    public SmartLightingRoomInPieceAdapter(SmartLightingRoomItem item) {
        this.item = item;
    }

    @NonNull
    @Override
    public SmartLightingRoomInPieceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_smart_lighting_room_in_piece, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartLightingRoomInPieceAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return item.getLightingList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lightingName;
        Switch aSwitch;
        TextView wat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void onBind(int position) {

        }
    }
}
