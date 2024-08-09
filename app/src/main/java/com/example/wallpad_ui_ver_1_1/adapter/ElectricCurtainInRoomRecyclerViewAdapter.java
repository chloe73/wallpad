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
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainItem;
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
        holder.onBind(item, item.getCurtainList().get(position), position);
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

            curtainName = itemView.findViewById(R.id.tv_curtain_name);
            seekBarLeft = itemView.findViewById(R.id.seekBar_left_in_room);
            seekBarRight = itemView.findViewById(R.id.seekBar_right_in_room);

            leftCurtain2 = itemView.findViewById(R.id.img_left_curtain_item2_in_room);
            leftCurtain3 = itemView.findViewById(R.id.img_left_curtain_item3_in_room);
            leftCurtain4 = itemView.findViewById(R.id.img_left_curtain_item4_in_room);
            leftCurtain5 = itemView.findViewById(R.id.img_left_curtain_item5_in_room);
            leftCurtain6 = itemView.findViewById(R.id.img_left_curtain_item6_in_room);
            leftCurtain7 = itemView.findViewById(R.id.img_left_curtain_item7_in_room);
            leftCurtain8 = itemView.findViewById(R.id.img_left_curtain_item8_in_room);

            rightCurtain2 = itemView.findViewById(R.id.img_right_curtain_item2_in_room);
            rightCurtain3 = itemView.findViewById(R.id.img_right_curtain_item3_in_room);
            rightCurtain4 = itemView.findViewById(R.id.img_right_curtain_item4_in_room);
            rightCurtain5 = itemView.findViewById(R.id.img_right_curtain_item5_in_room);
            rightCurtain6 = itemView.findViewById(R.id.img_right_curtain_item6_in_room);
            rightCurtain7 = itemView.findViewById(R.id.img_right_curtain_item7_in_room);
            rightCurtain8 = itemView.findViewById(R.id.img_right_curtain_item8_in_room);

            leftCurtainImgList = new ArrayList<>();
            leftCurtainImgList.add(leftCurtain8);
            leftCurtainImgList.add(leftCurtain7);
            leftCurtainImgList.add(leftCurtain6);
            leftCurtainImgList.add(leftCurtain5);
            leftCurtainImgList.add(leftCurtain4);
            leftCurtainImgList.add(leftCurtain3);
            leftCurtainImgList.add(leftCurtain2);

            rightCurtainImgList = new ArrayList<>();
            rightCurtainImgList.add(rightCurtain8);
            rightCurtainImgList.add(rightCurtain7);
            rightCurtainImgList.add(rightCurtain6);
            rightCurtainImgList.add(rightCurtain5);
            rightCurtainImgList.add(rightCurtain4);
            rightCurtainImgList.add(rightCurtain3);
            rightCurtainImgList.add(rightCurtain2);

            // seekbar 터치 비활성화 처리
            seekBarLeft.setEnabled(false);
            seekBarRight.setEnabled(false);
        }

        void onBind(ElectricCurtainRoomItem item, ElectricCurtainItem inItem, int position) {
            String name = item.getRoomName()+" 커튼 " + (position+1);
            curtainName.setText(name);

            seekBarLeft.setProgress(item.getCurtainList().get(position).getValue());
            seekBarRight.setProgress(item.getCurtainList().get(position).getValue());

            // 열려 있는 경우,
            if(inItem.getStatus() == 1) {
                // leftCurtain 투명도 처리
                for (ImageView img : leftCurtainImgList) {
                    img.setAlpha(0f);
                }

                for (ImageView img : rightCurtainImgList) {
                    img.setAlpha(0f);
                }
            }
            // 닫혀 있는 경우,
            else if(inItem.getStatus() == 0) {
                // leftCurtain 투명도 처리
                for (ImageView img : leftCurtainImgList) {
                    img.setAlpha(1f);
                }

                for (ImageView img : rightCurtainImgList) {
                    img.setAlpha(1f);
                }
            }
        }
    }
}
