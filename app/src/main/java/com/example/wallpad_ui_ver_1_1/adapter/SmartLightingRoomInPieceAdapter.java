package com.example.wallpad_ui_ver_1_1.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.item.SmartLightingItem;
import com.example.wallpad_ui_ver_1_1.item.SmartLightingRoomItem;
import com.example.wallpad_ui_ver_1_1.viewModel.SmartLightingSharedViewModel;

import java.util.ArrayList;

public class SmartLightingRoomInPieceAdapter extends RecyclerView.Adapter<SmartLightingRoomInPieceAdapter.ViewHolder> {

    private SmartLightingSharedViewModel smartLightingSharedViewModel;
    private ArrayList<SmartLightingItem> list;
    private int idx;

    public SmartLightingRoomInPieceAdapter(SmartLightingRoomItem item, int idx, SmartLightingSharedViewModel smartLightingSharedViewModel) {
        this.list = item.getLightingList();
        this.smartLightingSharedViewModel = smartLightingSharedViewModel;
        this.idx = idx;
    }

    @NonNull
    @Override
    public SmartLightingRoomInPieceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_smart_lighting_room_in_piece, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartLightingRoomInPieceAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(list.get(position), position);
        smartLightingSharedViewModel.getSmartLightingRooms().observe((LifecycleOwner) holder.itemView.getContext(), new Observer<ArrayList<SmartLightingRoomItem>>() {
            @Override
            public void onChanged(ArrayList<SmartLightingRoomItem> updatedList) {
                // 업데이트된 roomName을 설정
//                holder.roomName.setText(updatedList.get(position).getRoomName());
                holder.aSwitch.setChecked(smartLightingSharedViewModel.getSmartLightingRooms().getValue().get(idx).getLightingList().get(position).isOn());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lightingName;
        Switch aSwitch;
        TextView wat;
        ImageView lightingImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lightingName = itemView.findViewById(R.id.tv_smart_lighting_room_in_piece_lighting_name);
            aSwitch = itemView.findViewById(R.id.switch_smart_lighting_room_in_piece);
            wat = itemView.findViewById(R.id.tv_wat_value_in_smart_lighting_room_in_piece);
            lightingImg = itemView.findViewById(R.id.img_lighting);
        }

        void onBind(SmartLightingItem item, int position) {
            String name = "조명 " + (position + 1);
            lightingName.setText(name);
            aSwitch.setChecked(item.isOn());
            wat.setText(item.getWat() + "");

            if (item.isOn()) {
//                aSwitch.setChecked(true);
                lightingImg.setBackgroundResource(R.drawable.img_lighting_on);
            } else {
//                aSwitch.setChecked(false);
                lightingImg.setBackgroundResource(R.drawable.img_lighting_off);
            }

            aSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
                item.setOn(b);
                Log.d("각 방 내 스마트 조명 낱개 on off 상태 변화", b + "");
                // notifyItemChanged를 안전한 시점에 호출
                itemView.post(() -> {
                    notifyItemChanged(position);
                });
            });
        }
    }
}
