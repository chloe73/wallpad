package com.example.wallpad_ui_ver_1_1.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.item.SmartLightingRoomItem;
import com.example.wallpad_ui_ver_1_1.viewModel.SmartLightingSharedViewModel;

import java.util.ArrayList;

public class SmartLightingRoomAdapter extends RecyclerView.Adapter<SmartLightingRoomAdapter.ViewHolder> {

    private SmartLightingSharedViewModel smartLightingSharedViewModel;
    private ArrayList<SmartLightingRoomItem> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public SmartLightingRoomAdapter(ArrayList<SmartLightingRoomItem> list, OnItemClickListener listener, SmartLightingSharedViewModel smartLightingSharedViewModel) {
        this.list = list;
        this.listener = listener;
        this.smartLightingSharedViewModel = smartLightingSharedViewModel;
    }

    @NonNull
    @Override
    public SmartLightingRoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_smart_lighting_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartLightingRoomAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(list.get(position), position);

        // ViewModel의 데이터를 관찰하여 변경이 감지되면 UI 업데이트
        smartLightingSharedViewModel.getSmartLightingRooms().observe((LifecycleOwner) holder.itemView.getContext(), new Observer<ArrayList<SmartLightingRoomItem>>() {
            @Override
            public void onChanged(ArrayList<SmartLightingRoomItem> updatedList) {
                // 업데이트된 roomName을 설정
                holder.roomName.setText(updatedList.get(position).getRoomName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView roomName;
        TextView wat;
        View background;
        Switch aSwitch;
        ImageView touchZone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roomName = itemView.findViewById(R.id.tv_room_name_in_lighting_room);
            wat = itemView.findViewById(R.id.tv_wat_lighting_room);
            background = itemView.findViewById(R.id.lighting_room_background);
            aSwitch = itemView.findViewById(R.id.switch_smart_lighting_room);
            touchZone = itemView.findViewById(R.id.touch_zone_in_item_smart_lighting_room);
        }

        void onBind(SmartLightingRoomItem item, int position) {
            roomName.setText(smartLightingSharedViewModel.getSmartLightingRooms().getValue().get(position).getRoomName());
            wat.setText(item.getWat()+"");
            aSwitch.setChecked(item.isOn());

            touchZone.setOnClickListener(view -> {
                // 사용자가 이 부분을 터치하면 SmartLightingRoomFragment에 터치한 방 내 조명리스트가 보이게 한다.
                listener.onItemClick(position);
            });

            // Switch 상태가 변경될 때 item의 isOn 값을 업데이트
            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setOn(isChecked);// item의 isOn 값을 변경
                    list.get(position).setOn(isChecked);
                    smartLightingSharedViewModel.setSmartLightingRooms(list);
                    Log.d("switch on off 상태", ""+isChecked);
                    notifyItemChanged(position);
                }
            });

            if(item.isOn())
                background.setBackgroundResource(R.drawable.background_lighting_room_purple);
            else
                background.setBackgroundResource(R.drawable.background_lighting_room);
        }
    }
}
