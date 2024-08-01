package com.example.wallpad_ui_ver_1_1.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.fragment.ventilation.DialogEachRoomSettingFragment;
import com.example.wallpad_ui_ver_1_1.item.RoomVentilationItem;

import java.util.ArrayList;
import java.util.List;

public class VentilationAdapter extends RecyclerView.Adapter<VentilationAdapter.ViewHolder> {

    private List<RoomVentilationItem> items;

    private OnItemClickListener itemClickListener = null;

    public VentilationAdapter(ArrayList<RoomVentilationItem> items) {
        this.items = items;
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ventilation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.onBind(items.get(position));

        // RoomVentilationItem item = items.get(position);
        //holder.textView.setText(item.getName());
        // Log.d("onBindViewHolder !!!", item.getName());
    }

    @Override
    public int getItemCount() {
        Log.d("list size", items.size()+"");
        return items.size();
    }

    public void setItems(List<RoomVentilationItem> items) {
        this.items = items;
        Log.d("adapter class", items.get(0).getName());
        // notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ConstraintLayout ventilationBorderImage; // 각 방의 환기 on off 상태에 따라서 아이템 경계선 다르게 표시
        ImageView vantilationImage; // 환기
        ImageView airStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("viewHolder", "------");
            textView = itemView.findViewById(R.id.room_name);
            ventilationBorderImage = itemView.findViewById(R.id.item_ventilation_background);
            vantilationImage = itemView.findViewById(R.id.room_vantilation_onoff);
            airStatus = itemView.findViewById(R.id.img_each_room_air_status);

            airStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getBindingAdapterPosition();
                    Log.d("Recyclerview item click event", items.get(pos).getName()+"");
                    DialogEachRoomSettingFragment dialogEachRoomSettingFragment = new DialogEachRoomSettingFragment(items.get(pos));

                    // 각 방 설정 dialogfragment 띄우기
                    if (itemView.getContext() instanceof FragmentActivity) {
                        FragmentActivity activity = (FragmentActivity) itemView.getContext();
                        dialogEachRoomSettingFragment.show(activity.getSupportFragmentManager(), "DialogEachRoomSettingFragment");
                    } else {
                        Log.e("ViewHolder", "Context is not an instance of FragmentActivity");
                    }

                    if(pos != RecyclerView.NO_POSITION) {
                        if(itemClickListener != null) {
                            itemClickListener.onItemClick(view, pos);
                        }
                    }
                }
            });
        }

        void onBind(RoomVentilationItem item){
            textView.setText(item.getName());
            // 환기가 꺼져있을 경우에는 환기팬 이미지 off로 변경
            if (item.getIsOn() == 0){
                vantilationImage.setBackgroundResource(R.drawable.ventilation_off);
                ventilationBorderImage.setBackgroundResource(R.drawable.item_ventilation_border_off);
            }

            // 각 방 공기질 상태에 따른 이미지 변경
            switch (item.getAirStatus()) {
                case 1:
                    airStatus.setBackgroundResource(R.drawable.room_border_1);
                    break;
                case 2:
                    airStatus.setBackgroundResource(R.drawable.room_border_2);
                    break;
                case 3:
                    airStatus.setBackgroundResource(R.drawable.room_border_3);
                    break;
                case 4:
                    airStatus.setBackgroundResource(R.drawable.room_border_4);
                    break;
            }

        }
    }
}
