package com.example.wallpad_ui_ver_1_1.adapter;

import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ArrayList;

public interface OnCurtainControlClickListener {
    void onOpenClick(SeekBar seekBar, SeekBar seekBar2, ArrayList<ImageView> leftCurtainImgList, ArrayList<ImageView> rightCurtainImgList);
    void onCloseClick(SeekBar seekBar, SeekBar seekBar2, ArrayList<ImageView> leftCurtainImgList, ArrayList<ImageView> rightCurtainImgList);
    void onPauseClick(SeekBar seekBar, SeekBar seekBar2, ArrayList<ImageView> leftCurtainImgList, ArrayList<ImageView> rightCurtainImgList);
}
