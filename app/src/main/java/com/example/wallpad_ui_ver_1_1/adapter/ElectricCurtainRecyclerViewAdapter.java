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
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpad_ui_ver_1_1.R;
import com.example.wallpad_ui_ver_1_1.item.ElectricCurtainRoomItem;

import java.util.ArrayList;

public class ElectricCurtainRecyclerViewAdapter extends RecyclerView.Adapter<ElectricCurtainRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ElectricCurtainRoomItem> list;
    private OnCurtainControlClickListener listener;
    private ValueAnimator openLeftSeekBarAnimator;
    private ValueAnimator openRightSeekBarAnimator;
    private ValueAnimator closeLeftSeekBarAnimator;
    private ValueAnimator closeRightSeekBarAnimator;
    private AnimatorSet openLeftAnimatorSet;
    private AnimatorSet openRightAnimatorSet;
    private AnimatorSet closeLeftAnimatorSet;
    private AnimatorSet closeRightAnimatorSet;

//    private TextView roomName;
//    private TextView status; // 전동 커튼이 열려 있는지 닫혀 있는지 상태
//
//    private SeekBar seekBarLeft;
//    private SeekBar seekBarRight;
//
//    private ArrayList<ImageView> leftCurtainImgList;
//    private ImageView leftCurtain2;
//    private ImageView leftCurtain3;
//    private ImageView leftCurtain4;
//    private ImageView leftCurtain5;
//    private ImageView leftCurtain6;
//    private ImageView leftCurtain7;
//    private ImageView leftCurtain8;
//
//    private ArrayList<ImageView> rightCurtainImgList;
//    private ImageView rightCurtain2;
//    private ImageView rightCurtain3;
//    private ImageView rightCurtain4;
//    private ImageView rightCurtain5;
//    private ImageView rightCurtain6;
//    private ImageView rightCurtain7;
//    private ImageView rightCurtain8;
//
//    private ConstraintLayout openButton;
//    private ConstraintLayout closeButton;
//    private ConstraintLayout pauseButton;

    public ElectricCurtainRecyclerViewAdapter(ArrayList<ElectricCurtainRoomItem> list) {
        this.list = list;
        this.listener = new OnCurtainControlClickListener() {
            @Override
            public void onOpenClick(SeekBar seekBarLeft, SeekBar seekBarRight, ArrayList<ImageView> leftImgList, ArrayList<ImageView> rightImgList) {
                Log.d("openButton 클릭 후, onOpenClick() 실행", " ================ ");
                
                openLeftSeekBarAnimator = ValueAnimator.ofInt(seekBarLeft.getProgress(), 10);
                openRightSeekBarAnimator = ValueAnimator.ofInt(seekBarRight.getProgress(), 10);
                openLeftSeekBarAnimator.setDuration(14003); // Adjust duration as needed
                openRightSeekBarAnimator.setDuration(14003);

                openLeftAnimatorSet = new AnimatorSet();
                openRightAnimatorSet = new AnimatorSet();

                //status.setText("여는 중");

                openLeftSeekBarAnimator.addUpdateListener(animation -> {
                    int num = (int) openLeftSeekBarAnimator.getAnimatedValue();
                    seekBarLeft.setProgress(num);
                });
                openRightSeekBarAnimator.addUpdateListener(animation -> {
                    int num = (int) openRightSeekBarAnimator.getAnimatedValue();
                    seekBarRight.setProgress(num);
                });
                openLeftSeekBarAnimator.start();
                openRightSeekBarAnimator.start();

                // left curtain 이미지 사라지는 애니메이션 적용
                int delay = 0;
                int duration = 2000; // 각 이미지가 사라지는 시간

                for (int i = 0; i < leftImgList.size(); i++) {
                    ImageView img = leftImgList.get(i);
//                    openLeftViewPropertyAnimator = img.animate().alpha(0f).setDuration(duration).setStartDelay(delay).withEndAction(() -> img.setVisibility(View.INVISIBLE));
//                    openLeftViewPropertyAnimator.start();

                    ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f);
                    alphaAnimator.setDuration(duration);
                    alphaAnimator.setStartDelay(delay);
                    alphaAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            img.setVisibility(View.INVISIBLE);
                        }
                    });
                    openLeftAnimatorSet.playTogether(alphaAnimator);

                    delay += duration; // 다음 이미지의 애니메이션이 시작될 지연 시간 증가
                    if (i >= 6) {
                        delay -= 1600;
                    }
                    if (i <= 1) {
                        delay += 500;
                    }
                    if (i == 3 || i == 4) {
                        delay -= 700;
                    }
                }

                // rightcurtain 이미지 사라지는 애니메이션 적용
                delay = 0;
                for (int i = 0; i < rightImgList.size(); i++) {
                    ImageView img = rightImgList.get(i);
//                    openRightViewPropertyAnimator = img.animate().alpha(0f).setDuration(duration).setStartDelay(delay).withEndAction(() -> img.setVisibility(View.INVISIBLE));
//                    openRightViewPropertyAnimator.start();

                    ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f);
                    alphaAnimator.setDuration(duration);
                    alphaAnimator.setStartDelay(delay);
                    alphaAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            img.setVisibility(View.INVISIBLE);
                        }
                    });

                    openRightAnimatorSet.playTogether(alphaAnimator);

                    delay += duration;
                    if (i >= 6) {
                        delay -= 1600;
                    }
                    if (i <= 1) {
                        delay += 500;
                    }
                    if (i == 3 || i == 4) {
                        delay -= 700;
                    }
                }

                openLeftAnimatorSet.start();
                openRightAnimatorSet.start();
            }

            @Override
            public void onCloseClick(SeekBar seekBarLeft, SeekBar seekBarRight, ArrayList<ImageView> leftImgList, ArrayList<ImageView> rightImgList) {
                closeLeftSeekBarAnimator = ValueAnimator.ofInt(seekBarLeft.getProgress(), 100);
                closeRightSeekBarAnimator = ValueAnimator.ofInt(seekBarRight.getProgress(), 100);
                closeLeftSeekBarAnimator.setDuration(14003); // Adjust duration as needed
                closeRightSeekBarAnimator.setDuration(14003);

                closeLeftAnimatorSet = new AnimatorSet();
                closeRightAnimatorSet = new AnimatorSet();

                //status.setText("닫는 중");

                closeLeftSeekBarAnimator.addUpdateListener(animation -> {
                    int num = (int) closeLeftSeekBarAnimator.getAnimatedValue();
                    seekBarLeft.setProgress(num);
                });
                closeRightSeekBarAnimator.addUpdateListener(animation -> {
                    int num = (int) closeRightSeekBarAnimator.getAnimatedValue();
                    seekBarRight.setProgress(num);
                });
                closeLeftSeekBarAnimator.start();
                closeRightSeekBarAnimator.start();

                // left curtain 이미지 생겨나는 애니메이션 적용
                int delay = 0;
                int duration = 2000; // 각 이미지가 사라지는 시간

                // left curtain 이미지 생겨나는 애니메이션 적용
                for (int i = 6; i >= 0; i--) {
                    ImageView img = leftImgList.get(i);
//                    img.setVisibility(View.VISIBLE);
//                    img.setAlpha(0f);
//                    img.animate().alpha(1f).setDuration(duration).setStartDelay(delay).start();

                    ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f);
                    alphaAnimator.setDuration(duration);
                    alphaAnimator.setStartDelay(delay);

                    closeLeftAnimatorSet.playTogether(alphaAnimator);

                    delay += duration;
                    if (i <= 2 && i > 0) {
                        delay -= 1000;
                    }
                }

                // right curtain 이미지 생겨나는 애니메이션 적용
                delay = 0;
                for (int i = rightImgList.size() - 1; i >= 0; i--) {
                    ImageView img = rightImgList.get(i);
//                    img.setVisibility(View.VISIBLE);
//                    img.setAlpha(0f);
//                    img.animate().alpha(1f).setDuration(duration).setStartDelay(delay).start();
                    ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f);
                    alphaAnimator.setDuration(duration);
                    alphaAnimator.setStartDelay(delay);

                    closeRightAnimatorSet.playTogether(alphaAnimator);

                    delay += duration;
                    if (i <= 2 && i > 0) {
                        delay -= 1000;
                    }
                }

                closeLeftAnimatorSet.start();
                closeRightAnimatorSet.start();
            }

            @Override
            public void onPauseClick(SeekBar seekBarLeft, SeekBar seekBarRight, ArrayList<ImageView> leftImgList, ArrayList<ImageView> rightImgList) {

                // 열림 버튼 누른 후, 일시정지 누른 경우
                if (openLeftSeekBarAnimator != null && openRightSeekBarAnimator != null && openLeftSeekBarAnimator.isRunning() && openRightSeekBarAnimator.isRunning()) {
                    openLeftSeekBarAnimator.pause();
                    openRightSeekBarAnimator.pause();

                    openLeftAnimatorSet.pause();
                    openRightAnimatorSet.pause();
                }

                // 닫힘 버튼 누른 후, 일시정지 누른 경우
                if(closeLeftSeekBarAnimator != null && closeRightSeekBarAnimator != null && closeLeftSeekBarAnimator.isRunning() && closeRightSeekBarAnimator.isRunning()) {
                    closeLeftSeekBarAnimator.pause();
                    closeRightSeekBarAnimator.pause();

                    closeLeftAnimatorSet.pause();
                    closeRightAnimatorSet.pause();
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roomName = itemView.findViewById(R.id.tv_electric_curtain_room_name);
            status = itemView.findViewById(R.id.tv_electric_curtain_status);

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
                    // 사용자가 처음 이 화면에 진입했을 초기 경우,
                    if(openLeftSeekBarAnimator == null && openRightSeekBarAnimator == null) {
                        listener.onOpenClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList);
                    }
                    //
                    if(openLeftSeekBarAnimator != null && openRightSeekBarAnimator != null) {
                        if(!openLeftSeekBarAnimator.isRunning() && !openRightSeekBarAnimator.isRunning())
                            listener.onOpenClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList);
                        if(openLeftSeekBarAnimator.isPaused() && openRightSeekBarAnimator.isPaused()) {
                            listener.onOpenClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList);
                        }
                    }
                }
            });
            closeButton.setOnClickListener(v -> {
                if (listener != null && getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    Log.d("closeButton click!!!", getBindingAdapterPosition() + "");
                    if(closeLeftSeekBarAnimator == null && closeRightSeekBarAnimator == null
                        || (closeLeftSeekBarAnimator != null && closeRightSeekBarAnimator != null
                            && !closeLeftSeekBarAnimator.isRunning() && !closeRightSeekBarAnimator.isRunning()))
                        listener.onCloseClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList);
                }
            });
            pauseButton.setOnClickListener(v -> {
                if (listener != null && getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    Log.d("pauseButton click!!!", getBindingAdapterPosition() + "");
                    listener.onPauseClick(seekBarLeft, seekBarRight, leftCurtainImgList, rightCurtainImgList);
                }
            });
        }

        void onBind(ElectricCurtainRoomItem item) {
            roomName.setText(item.getRoomName());
            status.setText(item.getStatus() == 1 ? "열림" : "닫힘");

            // 각 방의 커튼이 열려있는지 닫혀있는지 상태에 따라 커튼 이미지와 SeekBar progress 표시
            if (item.getStatus() == 1) {
                seekBarLeft.setProgress(10);
                seekBarRight.setProgress(10);

                // leftCurtain 투명도 처리
                leftCurtain2.setAlpha(0f);
                leftCurtain3.setAlpha(0f);
                leftCurtain4.setAlpha(0f);
                leftCurtain5.setAlpha(0f);
                leftCurtain6.setAlpha(0f);
                leftCurtain7.setAlpha(0f);
                leftCurtain8.setAlpha(0f);

                // leftCurtain 시리즈를 invisible 처리
//                leftCurtain2.setVisibility(View.INVISIBLE);
//                leftCurtain3.setVisibility(View.INVISIBLE);
//                leftCurtain4.setVisibility(View.INVISIBLE);
//                leftCurtain5.setVisibility(View.INVISIBLE);
//                leftCurtain6.setVisibility(View.INVISIBLE);
//                leftCurtain7.setVisibility(View.INVISIBLE);
//                leftCurtain8.setVisibility(View.INVISIBLE);

                rightCurtain2.setAlpha(0f);
                rightCurtain3.setAlpha(0f);
                rightCurtain4.setAlpha(0f);
                rightCurtain5.setAlpha(0f);
                rightCurtain6.setAlpha(0f);
                rightCurtain7.setAlpha(0f);
                rightCurtain8.setAlpha(0f);

                // rightCurtain 시리즈를 invisible 처리
//                rightCurtain2.setVisibility(View.INVISIBLE);
//                rightCurtain3.setVisibility(View.INVISIBLE);
//                rightCurtain4.setVisibility(View.INVISIBLE);
//                rightCurtain5.setVisibility(View.INVISIBLE);
//                rightCurtain6.setVisibility(View.INVISIBLE);
//                rightCurtain7.setVisibility(View.INVISIBLE);
//                rightCurtain8.setVisibility(View.INVISIBLE);
            } else {
                seekBarLeft.setProgress(100);
                seekBarRight.setProgress(100);
            }
        }

    }
}

