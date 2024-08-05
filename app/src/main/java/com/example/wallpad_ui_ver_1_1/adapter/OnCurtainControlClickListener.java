package com.example.wallpad_ui_ver_1_1.adapter;

import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.core.animation.AnimatorSet;
import androidx.core.animation.ValueAnimator;

import java.util.ArrayList;

public interface OnCurtainControlClickListener {
    void onOpenClick(SeekBar seekBar, SeekBar seekBar2, ArrayList<ImageView> leftCurtainImgList, ArrayList<ImageView> rightCurtainImgList, int idx, ValueAnimator openLeftSeekBarAnimator, ValueAnimator openRightSeekBarAnimator, AnimatorSet openLeftAnimatorSet, AnimatorSet openRightAnimatorSet);
    void onCloseClick(SeekBar seekBar, SeekBar seekBar2, ArrayList<ImageView> leftCurtainImgList, ArrayList<ImageView> rightCurtainImgList, int idx, ValueAnimator closeLeftSeekBarAnimator, ValueAnimator closeRightSeekBarAnimator, AnimatorSet closeLeftAnimatorSet, AnimatorSet closeRightAnimatorSet);
    void onPauseClick(SeekBar seekBar, SeekBar seekBar2, ArrayList<ImageView> leftCurtainImgList, ArrayList<ImageView> rightCurtainImgList, int idx, ValueAnimator leftSeekBarAnimator, ValueAnimator rightSeekBarAnimator, AnimatorSet leftAnimatorSet, AnimatorSet rightAnimatorSet);
}
