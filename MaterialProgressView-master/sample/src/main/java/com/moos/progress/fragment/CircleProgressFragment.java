package com.moos.progress.fragment;


import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.moos.library.CircleProgressBar;
import com.moos.progress.R;

/**
 * A sample of CircleProgressView.
 */
public class CircleProgressFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener, CircleProgressBar.CircleProgressUpdateListener {

    private AppCompatSeekBar csb_track_width, csb_start_progress, csb_end_progress, csb_text_size;
    private SwitchCompat csc_trackEnabled, csc_fillEnabled, csc_circleBroken, csc_isGraduated;
    private CircleProgressBar circleProgressBar;
    private Button btn_start;

    public CircleProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_circle_progress, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        csb_track_width = (AppCompatSeekBar) view.findViewById(R.id.csb_track_width);
        csb_start_progress = (AppCompatSeekBar) view.findViewById(R.id.csb_start_progress);
        csb_end_progress = (AppCompatSeekBar) view.findViewById(R.id.csb_end_progress);
        csb_text_size = (AppCompatSeekBar) view.findViewById(R.id.csb_text_size);
        csc_trackEnabled = (SwitchCompat) view.findViewById(R.id.csc_isTracked);
        csc_fillEnabled = (SwitchCompat) view.findViewById(R.id.csc_isFilled);
        csc_circleBroken = (SwitchCompat) view.findViewById(R.id.csc_circleBroken);
        csc_isGraduated = (SwitchCompat) view.findViewById(R.id.csc_isGraduated);
        circleProgressBar = (CircleProgressBar) view.findViewById(R.id.progressView_circle);
        btn_start = (Button) view.findViewById(R.id.cb_start);
        csb_track_width.setOnSeekBarChangeListener(this);
        csb_start_progress.setOnSeekBarChangeListener(this);
        csb_end_progress.setOnSeekBarChangeListener(this);
        csb_text_size.setOnSeekBarChangeListener(this);
        csc_trackEnabled.setOnCheckedChangeListener(this);
        csc_circleBroken.setOnCheckedChangeListener(this);
        csc_fillEnabled.setOnCheckedChangeListener(this);
        csc_isGraduated.setOnCheckedChangeListener(this);
        btn_start.setOnClickListener(this);
        circleProgressBar.setProgressViewUpdateListener(this);
        circleProgressBar.setStartColor(Color.parseColor("#ee0000"));
        circleProgressBar.setGraduatedEnabled(true);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.csb_track_width:
                circleProgressBar.setTrackWidth(progress);
                break;

            case R.id.csb_start_progress:
                circleProgressBar.setStartProgress(progress);
                break;

            case R.id.csb_end_progress:
                circleProgressBar.setMaxProgress(progress);
                break;

            case R.id.csb_text_size:
                circleProgressBar.setProgressTextSize((int) (progress*0.5));
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.csc_isTracked:
                if (isChecked){
                    circleProgressBar.setTrackEnabled(true);
                }else {
                    circleProgressBar.setTrackEnabled(false);
                }

                break;

            case R.id.csc_circleBroken:
                if (isChecked){
                    circleProgressBar.setCircleBroken(true);
                }else {
                    circleProgressBar.setCircleBroken(false);
                }
                break;

            case R.id.csc_isFilled:
                if (isChecked){
                    circleProgressBar.setFillEnabled(true);
                }else {
                    circleProgressBar.setFillEnabled(false);
                }
                break;

            case R.id.csc_isGraduated:
                if (isChecked){
                    circleProgressBar.setGraduatedEnabled(true);
                }else {
                    circleProgressBar.setGraduatedEnabled(false);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cb_start){
            circleProgressBar.startProgressAnimation();
        }
    }

    @Override
    public void onCircleProgressStart(View view) {

        /**
         * you can detail with progressViews' animate event and customize their animate order
         */
    }

    @Override
    public void onCircleProgressUpdate(View view, float progress) {

    }

    @Override
    public void onCircleProgressFinished(View view) {

    }
}
