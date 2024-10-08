package com.example.wallpad_ui_ver_1_1.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.animation.Animator;
import androidx.core.animation.AnimatorListenerAdapter;
import androidx.core.animation.AnimatorSet;
import androidx.core.animation.ObjectAnimator;
import androidx.core.animation.ValueAnimator;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.fragment.electricCurtain.ElectricCurtainFragment;
import com.example.wallpad_ui_ver_1_1.fragment.electricCurtain.ElectricCurtainPopUpFragment;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainRoomItem;

import java.util.ArrayList;

public class ElectricCurtainRecyclerViewAdapter extends RecyclerView.Adapter<ElectricCurtainRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ElectricCurtainRoomItem> list;
    private OnCurtainControlClickListener listener;

    public ElectricCurtainRecyclerViewAdapter(ArrayList<ElectricCurtainRoomItem> list) {
        this.list = list;
        this.listener = new OnCurtainControlClickListener() {
            @Override
            public void onOpenClick(SeekBar seekBarLeft, SeekBar seekBarRight, ArrayList<ImageView> leftImgList, ArrayList<ImageView> rightImgList, int idx, ValueAnimator openLeftSeekBarAnimator, ValueAnimator openRightSeekBarAnimator) {
                Log.d("openButton 클릭 후, onOpenClick() 실행", " ================ ");

                // 이미 열려있는 경우에는 동작 안하게 함.
                if (seekBarLeft.getProgress() == 10 && seekBarRight.getProgress() == 10) {
                    Log.d("seekBarLeft.getProgress() == 10", "seekBarLeft.getProgress() == 10");
                    return;
                }

                openLeftSeekBarAnimator.setDuration(10000); // Adjust duration as needed
                openRightSeekBarAnimator.setDuration(10000);

                openLeftSeekBarAnimator.addUpdateListener(animation -> {
                    int num = (int) openLeftSeekBarAnimator.getAnimatedValue();
                    seekBarLeft.setProgress(num);
                    updateOpenImageAlpha(leftImgList, num);
                    if(num == 10) {
                        list.get(idx).setStatus(1); // 열림 상태로 업데이트
                        list.get(idx).setValue(10);
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

                // 여는 중인 상태로 변경
                list.get(idx).setStatus(2);
                notifyItemChanged(idx);
            }

            @Override
            public void onCloseClick(SeekBar seekBarLeft, SeekBar seekBarRight, ArrayList<ImageView> leftImgList, ArrayList<ImageView> rightImgList, int idx, ValueAnimator closeLeftSeekBarAnimator, ValueAnimator closeRightSeekBarAnimator) {
                // 이미 닫혀있는 경우에는 동작 안하게 함.
                if (seekBarLeft.getProgress() == 100 && seekBarRight.getProgress() == 100) {
                    Log.d("seekBarLeft.getProgress() == 100", "seekBarLeft.getProgress() == 100");
                    return;
                }

                closeLeftSeekBarAnimator.setDuration(10000); // Adjust duration as needed
                closeRightSeekBarAnimator.setDuration(10000);

                closeLeftSeekBarAnimator.addUpdateListener(animation -> {
                    int num = (int) closeLeftSeekBarAnimator.getAnimatedValue();
                    seekBarLeft.setProgress(num);
                    updateCloseImageAlpha(leftImgList, num);
                    if(num == 100) {
                        list.get(idx).setStatus(0);
                        list.get(idx).setValue(100);
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
                list.get(idx).setStatus(3);
                notifyItemChanged(idx);
            }

            @Override
            public void onPauseClick(SeekBar seekBarLeft, SeekBar seekBarRight, ArrayList<ImageView> leftImgList, ArrayList<ImageView> rightImgList, int idx, ValueAnimator leftSeekBarAnimator, ValueAnimator rightSeekBarAnimator) {
                // 각 아이템 상태가 '여는 중' or '닫는 중'인 경우에만 동작함.
                if (list.get(idx).getStatus() == 2 || list.get(idx).getStatus() == 3) {
                    list.get(idx).setStatus(4);
                    list.get(idx).setValue(seekBarLeft.getProgress());
                    notifyItemChanged(idx);

                    leftSeekBarAnimator.pause();
                    rightSeekBarAnimator.pause();
                }
            }
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_electric_curtain, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
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

    // 전체 방 열기 버튼
    public void onOpenClick(RecyclerView rv) {
        for (int i = 0; i < list.size(); i++) {
            ElectricCurtainRoomItem item = list.get(i);
            if (item.getStatus() == 0) {
                // 닫힌 경우 열기
                ViewHolder holder = (ViewHolder) rv.findViewHolderForAdapterPosition(i);
                if (holder != null) {
                    if (holder.getOpenLeftSeekBarAnimator() == null) {
                        Log.d("holder.getOpenLeftSeekBarAnimator() == null", "null 모야ㅑ야야야");
                        ValueAnimator openLeftSeekBarAnimator = ValueAnimator.ofInt(holder.getSeekBarLeft().getProgress(), 10);
                        ValueAnimator openRightSeekBarAnimator = ValueAnimator.ofInt(holder.getSeekBarRight().getProgress(), 10);
                        listener.onOpenClick(holder.getSeekBarLeft(), holder.getSeekBarRight(), holder.getLeftCurtainImgList(), holder.getRightCurtainImgList(), i, openLeftSeekBarAnimator, openRightSeekBarAnimator);
                    } else {
                        listener.onOpenClick(holder.getSeekBarLeft(), holder.getSeekBarRight(), holder.getLeftCurtainImgList(), holder.getRightCurtainImgList(), i, holder.getOpenLeftSeekBarAnimator(), holder.getOpenRightSeekBarAnimator());
                    }
                }
            }
        }

    }

    // 전체 방 닫기 버튼
    public void onCloseClick(RecyclerView rv) {
        for (int i = 0; i < list.size(); i++) {
            ElectricCurtainRoomItem item = list.get(i);
            if (item.getStatus() == 1) {
                // 열려있는 경우 닫기
                ViewHolder holder = (ViewHolder) rv.findViewHolderForAdapterPosition(i);
                if (holder != null) {
                    if (holder.getCloseLeftSeekBarAnimator() == null) {
                        ValueAnimator closeLeftSeekBarAnimator = ValueAnimator.ofInt(holder.getSeekBarLeft().getProgress(), 100);
                        ValueAnimator closeRightSeekBarAnimator = ValueAnimator.ofInt(holder.getSeekBarRight().getProgress(), 100);
                        listener.onCloseClick(holder.getSeekBarLeft(), holder.getSeekBarRight(), holder.getLeftCurtainImgList(), holder.getRightCurtainImgList(), i, closeLeftSeekBarAnimator, closeRightSeekBarAnimator);
                    } else {
                        listener.onCloseClick(holder.getSeekBarLeft(), holder.getSeekBarRight(), holder.getLeftCurtainImgList(), holder.getRightCurtainImgList(), i, holder.getCloseLeftSeekBarAnimator(), holder.getCloseRightSeekBarAnimator());
                    }

                }
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView roomName;
        TextView status; // 전동 커튼이 열려 있는지 닫혀 있는지 상태

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

        ImageView moreBtn;

        public void setOpenLeftSeekBarAnimator(ValueAnimator openLeftSeekBarAnimator) {
            this.openLeftSeekBarAnimator = openLeftSeekBarAnimator;
        }

        public void setOpenRightSeekBarAnimator(ValueAnimator openRightSeekBarAnimator) {
            this.openRightSeekBarAnimator = openRightSeekBarAnimator;
        }

        public void setCloseLeftSeekBarAnimator(ValueAnimator closeLeftSeekBarAnimator) {
            this.closeLeftSeekBarAnimator = closeLeftSeekBarAnimator;
        }

        public void setCloseRightSeekBarAnimator(ValueAnimator closeRightSeekBarAnimator) {
            this.closeRightSeekBarAnimator = closeRightSeekBarAnimator;
        }

        public ValueAnimator getOpenLeftSeekBarAnimator() {
            return openLeftSeekBarAnimator;
        }

        public ValueAnimator getOpenRightSeekBarAnimator() {
            return openRightSeekBarAnimator;
        }

        public ValueAnimator getCloseLeftSeekBarAnimator() {
            return closeLeftSeekBarAnimator;
        }

        public ValueAnimator getCloseRightSeekBarAnimator() {
            return closeRightSeekBarAnimator;
        }

        ValueAnimator openLeftSeekBarAnimator;
        ValueAnimator openRightSeekBarAnimator;
        ValueAnimator closeLeftSeekBarAnimator;
        ValueAnimator closeRightSeekBarAnimator;

        public TextView getStatusTextView() {
            return status;
        }

        public SeekBar getSeekBarLeft() {
            return seekBarLeft;
        }

        public SeekBar getSeekBarRight() {
            return seekBarRight;
        }

        public ArrayList<ImageView> getLeftCurtainImgList() {
            return leftCurtainImgList;
        }

        public ArrayList<ImageView> getRightCurtainImgList() {
            return rightCurtainImgList;
        }

        public ConstraintLayout getOpenButton() {
            return openButton;
        }

        public ConstraintLayout getCloseButton() {
            return closeButton;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roomName = itemView.findViewById(R.id.tv_electric_curtain_room_name);
            status = itemView.findViewById(R.id.tv_electric_curtain_status);

            moreBtn = itemView.findViewById(R.id.btn_more_view_in_rv_electric_curtain);

            seekBarLeft = itemView.findViewById(R.id.seekBar_left);
            seekBarRight = itemView.findViewById(R.id.seekBar_right);
            leftCurtain2 = itemView.findViewById(R.id.img_left_curtain_item2);
            leftCurtain3 = itemView.findViewById(R.id.img_left_curtain_item3);
            leftCurtain4 = itemView.findViewById(R.id.img_left_curtain_item4);
            leftCurtain5 = itemView.findViewById(R.id.img_left_curtain_item5);
            leftCurtain6 = itemView.findViewById(R.id.img_left_curtain_item6);
            leftCurtain7 = itemView.findViewById(R.id.img_left_curtain_item7);
            leftCurtain8 = itemView.findViewById(R.id.img_left_curtain_item8);

            rightCurtain2 = itemView.findViewById(R.id.img_right_curtain_item2);
            rightCurtain3 = itemView.findViewById(R.id.img_right_curtain_item3);
            rightCurtain4 = itemView.findViewById(R.id.img_right_curtain_item4);
            rightCurtain5 = itemView.findViewById(R.id.img_right_curtain_item5);
            rightCurtain6 = itemView.findViewById(R.id.img_right_curtain_item6);
            rightCurtain7 = itemView.findViewById(R.id.img_right_curtain_item7);
            rightCurtain8 = itemView.findViewById(R.id.img_right_curtain_item8);

            openButton = itemView.findViewById(R.id.btn_open);
            closeButton = itemView.findViewById(R.id.btn_close);
            pauseButton = itemView.findViewById(R.id.btn_pause);

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

            // 클릭 리스너 설정
            openButton.setOnClickListener(v -> {
                if (listener != null && getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    Log.d("openButton click!!!", getBindingAdapterPosition() + "");
                    openLeftSeekBarAnimator = ValueAnimator.ofInt(seekBarLeft.getProgress(), 10);
                    openRightSeekBarAnimator = ValueAnimator.ofInt(seekBarRight.getProgress(), 10);

                    // 아이템 상태가 '닫힘' 상태일 때 동작함.
                    if (list.get(getBindingAdapterPosition()).getStatus() == 0 || list.get(getBindingAdapterPosition()).getStatus() == 4) {
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
                    if (list.get(getBindingAdapterPosition()).getStatus() == 1 || list.get(getBindingAdapterPosition()).getStatus() == 4)
                        listener.onCloseClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList, getBindingAdapterPosition(), closeLeftSeekBarAnimator, closeRightSeekBarAnimator);
                }
            });

            pauseButton.setOnClickListener(v -> {
                if (listener != null && getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    Log.d("pauseButton click!!!", getBindingAdapterPosition() + "");
                    // 여는 중일 때, 중단한 경우
                    if (list.get(getBindingAdapterPosition()).getStatus() == 2) {
                        listener.onPauseClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList, getBindingAdapterPosition(), openLeftSeekBarAnimator, openRightSeekBarAnimator);
                    }
                    // 닫는 중일 때, 중단한 경우
                    else if (list.get(getBindingAdapterPosition()).getStatus() == 3) {
                        listener.onPauseClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList, getBindingAdapterPosition(), closeLeftSeekBarAnimator, closeRightSeekBarAnimator);
                    }
                }
            });

            // rv 더 보기(arrow icon) 버튼 클릭 이벤트
            moreBtn.setOnClickListener(view -> {
                FragmentActivity activity = (FragmentActivity) view.getContext();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                ElectricCurtainPopUpFragment popUpFragment = new ElectricCurtainPopUpFragment(list, getBindingAdapterPosition());



                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.add(R.id.main_container, popUpFragment)
                        .addToBackStack(null)
                        .commit();

                Log.d("rv 더 보기 버튼 클릭함 !!!!!!!!!!!!!", "잘 작동하는거뉘?????????");
            });
        }

        void onBind(ElectricCurtainRoomItem item) {
            roomName.setText(item.getRoomName());

            switch (item.getStatus()) {
                case 0:
                    status.setText("닫힘");
                    break;
                case 1:
                    status.setText("열림");
                    break;
                case 2:
                    status.setText("여는 중");
                    break;
                case 3:
                    status.setText("닫는 중");
                    break;
            }

            seekBarLeft.setProgress(item.getValue());
            seekBarRight.setProgress(item.getValue());

            // 각 커튼의 value에 따른 커튼 이미지 투명도 처리
            if (item.getValue() <= 94) {
                leftCurtainImgList.get(0).setAlpha(0f);
                rightCurtainImgList.get(0).setAlpha(0f);
            }
            if (item.getValue() <= 82) {
                leftCurtainImgList.get(1).setAlpha(0f);
                rightCurtainImgList.get(1).setAlpha(0f);
            }
            if (item.getValue() <= 69) {
                leftCurtainImgList.get(2).setAlpha(0f);
                rightCurtainImgList.get(2).setAlpha(0f);
            }
            if (item.getValue() <= 56) {
                leftCurtainImgList.get(3).setAlpha(0f);
                rightCurtainImgList.get(3).setAlpha(0f);
            }
            if (item.getValue() <= 43) {
                leftCurtainImgList.get(4).setAlpha(0f);
                rightCurtainImgList.get(4).setAlpha(0f);
            }
            if (item.getValue() <= 30) {
                leftCurtainImgList.get(5).setAlpha(0f);
                rightCurtainImgList.get(5).setAlpha(0f);
            }
            if(item.getValue() <= 17){
                leftCurtainImgList.get(6).setAlpha(0f);
                rightCurtainImgList.get(6).setAlpha(0f);
            }

        }

    }
}

