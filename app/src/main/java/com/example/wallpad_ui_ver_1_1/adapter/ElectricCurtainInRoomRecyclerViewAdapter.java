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
import androidx.core.animation.ValueAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainItem;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainRoomItem;

import java.util.ArrayList;

public class ElectricCurtainInRoomRecyclerViewAdapter extends RecyclerView.Adapter<ElectricCurtainInRoomRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ElectricCurtainRoomItem> list;
    private ElectricCurtainRoomItem item;
    private int idx;
    private OnCurtainControlClickListener listener;

    public ElectricCurtainInRoomRecyclerViewAdapter(ArrayList<ElectricCurtainRoomItem> list, int idx) {
        this.list = list;
        this.item = list.get(idx);
        this.idx = idx;
        this.listener = new OnCurtainControlClickListener() {
            @Override
            public void onOpenClick(SeekBar seekBarLeft, SeekBar seekBarRight, ArrayList<ImageView> leftImgList, ArrayList<ImageView> rightImgList, int idx, ValueAnimator openLeftSeekBarAnimator, ValueAnimator openRightSeekBarAnimator) {
                Log.d("ElectricCurtainInRoomRecyclerViewAdapter : ", "onOpenClick() in room 진입!");

                // 이미 열려있는 경우에는 동작 안하게 함.
                if (seekBarLeft.getProgress() == 10 && seekBarRight.getProgress() == 10) {
                    Log.d("seekBarLeft.getProgress() == 10", "seekBarLeft.getProgress() == 10");
                    return;
                }

                openLeftSeekBarAnimator.setDuration(14003); // Adjust duration as needed
                openRightSeekBarAnimator.setDuration(14003);

                openLeftSeekBarAnimator.addUpdateListener(animation -> {
                    int num = (int) openLeftSeekBarAnimator.getAnimatedValue();
                    seekBarLeft.setProgress(num);
                    updateOpenImageAlpha(leftImgList, num);
                    if(num == 10) {
                        item.getCurtainList().get(idx).setStatus(1); // 열림 상태로 업데이트
                        item.getCurtainList().get(idx).setValue(10);
                        notifyItemChanged(idx);
                    }
                });
                openRightSeekBarAnimator.addUpdateListener(animation -> {
                    int num = (int) openRightSeekBarAnimator.getAnimatedValue();
                    seekBarRight.setProgress(num);
                    updateOpenImageAlpha(rightImgList, num);
                });

                openLeftSeekBarAnimator.start();
                openRightSeekBarAnimator.start();

                item.getCurtainList().get(idx).setStatus(2);
                notifyItemChanged(idx);
            }

            @Override
            public void onCloseClick(SeekBar seekBarLeft, SeekBar seekBarRight, ArrayList<ImageView> leftImgList, ArrayList<ImageView> rightImgList, int idx, ValueAnimator closeLeftSeekBarAnimator, ValueAnimator closeRightSeekBarAnimator) {
                Log.d("ElectricCurtainInRoomRecyclerViewAdapter : ", "onCloseClick() in room 진입!");
                // 이미 닫혀있는 경우에는 동작 안하게 함.
                if (seekBarLeft.getProgress() == 100 && seekBarRight.getProgress() == 100) {
                    Log.d("seekBarLeft.getProgress() == 100", "seekBarLeft.getProgress() == 100");
                    return;
                }

                closeLeftSeekBarAnimator.setDuration(14003); // Adjust duration as needed
                closeRightSeekBarAnimator.setDuration(14003);

                closeLeftSeekBarAnimator.addUpdateListener(animation -> {
                    int num = (int) closeLeftSeekBarAnimator.getAnimatedValue();
                    seekBarLeft.setProgress(num);
                    updateCloseImageAlpha(leftImgList, num);
                    if(num == 100) {
                        item.getCurtainList().get(idx).setStatus(0);
                        item.getCurtainList().get(idx).setValue(100);
                        notifyItemChanged(idx);
                    }
                });

                closeRightSeekBarAnimator.addUpdateListener(animation -> {
                    int num = (int) closeRightSeekBarAnimator.getAnimatedValue();
                    seekBarRight.setProgress(num);
                    updateCloseImageAlpha(rightImgList, num);
                });

                closeLeftSeekBarAnimator.start();
                closeRightSeekBarAnimator.start();

                // 닫는 중인 상태로 변경
                item.getCurtainList().get(idx).setStatus(3);
                notifyItemChanged(idx);
            }

            @Override
            public void onPauseClick(SeekBar seekBarLeft, SeekBar seekBarRight, ArrayList<ImageView> leftCurtainImgList, ArrayList<ImageView> rightCurtainImgList, int idx, ValueAnimator leftSeekBarAnimator, ValueAnimator rightSeekBarAnimator) {
                Log.d("ElectricCurtainInRoomRecyclerViewAdapter : ", "onPauseClick() in room 진입!");
                Log.d("on pause !", ""+item.getCurtainList().get(idx).getStatus());
                // 각 아이템 상태가 '여는 중' or '닫는 중'인 경우에만 동작함.
                if (item.getCurtainList().get(idx).getStatus() == 2 || item.getCurtainList().get(idx).getStatus() == 3) {
                    item.getCurtainList().get(idx).setStatus(4);
                    notifyItemChanged(idx);

                    leftSeekBarAnimator.pause();
                    rightSeekBarAnimator.pause();
                }
            }
        };
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

    // progress value에 따른 커튼 이미지 안 보이게 하기
    private void updateOpenImageAlpha(ArrayList<ImageView> imgList, int progress) {

        float alpha = 0f;

        if (progress >= 10 && progress <= 17) {
            ImageView img = imgList.get(6);
            img.setAlpha(alpha);
        } else if (progress >= 23 && progress <= 30) {
            ImageView img = imgList.get(5);
            img.setAlpha(alpha);
        } else if (progress >= 36 && progress <= 43) {
            ImageView img = imgList.get(4);
            img.setAlpha(alpha);
        } else if (progress >= 49 && progress <= 56) {
            ImageView img = imgList.get(3);
            img.setAlpha(alpha);
        } else if (progress >= 62 && progress <= 69) {
            ImageView img = imgList.get(2);
            img.setAlpha(alpha);
        } else if (progress >= 75 && progress <= 82) {
            ImageView img = imgList.get(1);
            img.setAlpha(alpha);
        } else if (progress >= 88 && progress <= 95) {
            ImageView img = imgList.get(0);
            img.setAlpha(alpha);
        }

    }

    // progress value에 따른 커튼 이미지 보이게 하기
    private void updateCloseImageAlpha(ArrayList<ImageView> imgList, int progress) {
        float alpha = 1f;

        if (progress >= 17 && progress <= 22) {
            ImageView img = imgList.get(6);
            img.setAlpha(alpha);
        } else if (progress >= 28 && progress <= 35) {
            ImageView img = imgList.get(5);
            img.setAlpha(alpha);
        } else if (progress >= 41 && progress <= 48) {
            ImageView img = imgList.get(4);
            img.setAlpha(alpha);
        } else if (progress >= 54 && progress <= 61) {
            ImageView img = imgList.get(3);
            img.setAlpha(alpha);
        } else if (progress >= 67 && progress <= 74) {
            ImageView img = imgList.get(2);
            img.setAlpha(alpha);
        } else if (progress >= 81 && progress <= 87) {
            ImageView img = imgList.get(1);
            img.setAlpha(alpha);
        } else if (progress >= 93 && progress <= 100) {
            ImageView img = imgList.get(0);
            img.setAlpha(alpha);
        }
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

        ValueAnimator openLeftSeekBarAnimator;
        ValueAnimator openRightSeekBarAnimator;
        ValueAnimator closeLeftSeekBarAnimator;
        ValueAnimator closeRightSeekBarAnimator;

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

            openButton = itemView.findViewById(R.id.btn_open_in_room);
            closeButton = itemView.findViewById(R.id.btn_close_in_room);
            pauseButton = itemView.findViewById(R.id.btn_pause_in_room);

            // seekbar 터치 비활성화 처리
            seekBarLeft.setEnabled(false);
            seekBarRight.setEnabled(false);

            // 열림 버튼 리스너
            openButton.setOnClickListener(v -> {
                if (listener != null && getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    Log.d("openButtonInRoom click!!!", getBindingAdapterPosition() + "");
                    openLeftSeekBarAnimator = ValueAnimator.ofInt(seekBarLeft.getProgress(), 10);
                    openRightSeekBarAnimator = ValueAnimator.ofInt(seekBarRight.getProgress(), 10);

                    // 아이템 상태가 '닫힘' 상태일 때 동작함.
                    if (item.getCurtainList().get(getBindingAdapterPosition()).getStatus() == 0 || item.getCurtainList().get(getBindingAdapterPosition()).getStatus() == 4) {
                        listener.onOpenClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList, getBindingAdapterPosition(), openLeftSeekBarAnimator, openRightSeekBarAnimator);
                    }
                }
            });

            closeButton.setOnClickListener(v -> {
                if (listener != null && getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    Log.d("closeButton click!!!", getBindingAdapterPosition() + "");
                    closeLeftSeekBarAnimator = ValueAnimator.ofInt(seekBarLeft.getProgress(), 100);
                    closeRightSeekBarAnimator = ValueAnimator.ofInt(seekBarRight.getProgress(), 100);

                    // 아이템 상태가 '열림'상태일 때 동작함.
                    if (item.getCurtainList().get(getBindingAdapterPosition()).getStatus() == 1 || item.getCurtainList().get(getBindingAdapterPosition()).getStatus() == 4)
                        listener.onCloseClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList, getBindingAdapterPosition(), closeLeftSeekBarAnimator, closeRightSeekBarAnimator);
                }
            });

            pauseButton.setOnClickListener(v -> {
                if (listener != null && getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    Log.d("pauseButton click!!!", getBindingAdapterPosition() + "");
                    // 여는 중일 때, 중단한 경우
                    if (item.getCurtainList().get(getBindingAdapterPosition()).getStatus() == 2) {
                        listener.onPauseClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList, getBindingAdapterPosition(), openLeftSeekBarAnimator, openRightSeekBarAnimator);
                    }
                    // 닫는 중일 때, 중단한 경우
                    else if (item.getCurtainList().get(getBindingAdapterPosition()).getStatus() == 3) {
                        listener.onPauseClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList, getBindingAdapterPosition(), closeLeftSeekBarAnimator, closeRightSeekBarAnimator);
                    }
                }
            });
        }

        void onBind(ElectricCurtainRoomItem item, ElectricCurtainItem inItem, int position) {
            String name = item.getRoomName()+" 커튼 " + (position+1);
            curtainName.setText(name);

//            seekBarLeft.setProgress(item.getCurtainList().get(position).getValue());
//            seekBarRight.setProgress(item.getCurtainList().get(position).getValue());

            // 열려 있는 경우,
            if(inItem.getStatus() == 1) {
                seekBarLeft.setProgress(10);
                seekBarRight.setProgress(10);
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
                seekBarLeft.setProgress(100);
                seekBarRight.setProgress(100);
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
