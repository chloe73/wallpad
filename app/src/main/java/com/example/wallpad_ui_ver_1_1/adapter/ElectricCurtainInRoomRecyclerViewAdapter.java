package com.example.wallpad_ui_ver_1_1.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainRoomItem;

import java.util.ArrayList;

public class ElectricCurtainInRoomRecyclerViewAdapter extends RecyclerView.Adapter<ElectricCurtainInRoomRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ElectricCurtainRoomItem> list;
    private ElectricCurtainRoomItem item;
    private int idx;

    public ElectricCurtainInRoomRecyclerViewAdapter(ArrayList<ElectricCurtainRoomItem> list, int idx) {
        this.list = list;
        this.item = list.get(idx);
        this.idx = idx;
    }
    @NonNull
    @Override
    public ElectricCurtainInRoomRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_electric_curtain_in_curtain,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectricCurtainInRoomRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.onBind(item);
    }

    @Override
    public int getItemCount() {
        return list.get(idx).getCurtainList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView curtainName;
        SeekBar seekBarLeft;
        SeekBar seekBarRight;

        ArrayList<ImageView> leftCurtainImgList;
        ImageView leftCurtain2;
        ImageView leftCurtain3;
        ImageView leftCurtain4;
        ImageView leftCurtain5;
        ImageView leftCurtain6;
        ImageView leftCurtain7;
        ImageView leftCurtain8;

        ArrayList<ImageView> rightCurtainImgList;
        ImageView rightCurtain2;
        ImageView rightCurtain3;
        ImageView rightCurtain4;
        ImageView rightCurtain5;
        ImageView rightCurtain6;
        ImageView rightCurtain7;
        ImageView rightCurtain8;

        ConstraintLayout openButton;
        ConstraintLayout closeButton;
        ConstraintLayout pauseButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("onBindViewHolder !!!!!!!!!!!!", "어댑터 연결 잇츠 오케이 ???????");
            curtainName = itemView.findViewById(R.id.tv_curtain_name);
            seekBarLeft = itemView.findViewById(R.id.seekBar_left);
            seekBarRight = itemView.findViewById(R.id.seekBar_right);
        }

        void onBind(ElectricCurtainRoomItem item) {
            String name = item.getRoomName();
            // 현재 방의 커튼의 총 개수가 1개인 경우
            if(item.getCurtainList().size() == 0) {
                name+=" 01";
            }

            // 현재 방 커튼의 총 개수가 2개 이상인 경우
            else if(item.getCurtainList().size() >= 2) {
                name += " "+item.getCurtainList().size();
            }
            curtainName.setText(name);

        }
    }
}
