package com.moos.progress.fragment;


import android.os.Bundle;
import android.os.Environment;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moos.library.HorizontalProgressBar;
import com.moos.progress.R;
import com.moos.progress.utils.DownloadUtil;

import static android.content.ContentValues.TAG;

/**
 * A sample of HorizontalProgressView.
 * todo:
 * 1. add color selector
 * 2. redesign the UI
 * 3. support more text align ways(like center_vertical)
 */
public class HorizontalProgressFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener, HorizontalProgressBar.HorizontalProgressUpdateListener {


    private AppCompatSeekBar hsb_track_width, hsb_start_progress, hsb_end_progress, hsb_text_size, hsb_corner_radius ;
    private SwitchCompat hsc_trackEnabled, hsc_text_visibility;
    private HorizontalProgressBar horizontalProgressBar;
    private Button btn_start;
    private ImageView btn_download;
    private TextView textView_call_back, bubble_progress;
    public static final String VIDEO_URL = "http://oss.timeory.com/map/video/E11EC9F0-229A-4B34-9272-232724EF2BB1.mp4";
    public static final String SAVED_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Material-ProgressView/download_test" ;

    public HorizontalProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_horizontal_progress, container, false);
        hsb_track_width = (AppCompatSeekBar) view.findViewById(R.id.hsb_track_width);
        hsb_start_progress = (AppCompatSeekBar) view.findViewById(R.id.hsb_start_progress);
        hsb_end_progress = (AppCompatSeekBar) view.findViewById(R.id.hsb_end_progress);
        hsb_text_size = (AppCompatSeekBar) view.findViewById(R.id.hsb_text_size);
        hsb_corner_radius = (AppCompatSeekBar) view.findViewById(R.id.hsb_corner_radius);
        hsc_trackEnabled = (SwitchCompat) view.findViewById(R.id.hsc_isTracked);
        hsc_text_visibility = (SwitchCompat) view.findViewById(R.id.hsc_text_visibility);
        horizontalProgressBar = (HorizontalProgressBar) view.findViewById(R.id.progressView_horizontal);
        btn_start = (Button) view.findViewById(R.id.hb_start);
        textView_call_back = (TextView) view.findViewById(R.id.cb_progress_call_back);
        bubble_progress = (TextView) view.findViewById(R.id.progress_bubble_text);
        btn_download = (ImageView) view.findViewById(R.id.hb_download);

        hsb_track_width.setOnSeekBarChangeListener(this);
        hsb_start_progress.setOnSeekBarChangeListener(this);
        hsb_end_progress.setOnSeekBarChangeListener(this);
        hsb_corner_radius.setOnSeekBarChangeListener(this);
        hsb_text_size.setOnSeekBarChangeListener(this);
        hsc_trackEnabled.setOnCheckedChangeListener(this);
        hsc_text_visibility.setOnCheckedChangeListener(this);
        btn_start.setOnClickListener(this);
        btn_download.setOnClickListener(this);
        horizontalProgressBar.setProgressViewUpdateListener(this);

        
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.e(TAG, "onProgressChanged: "+progress );
        switch (seekBar.getId()){
            case R.id.hsb_start_progress:
                horizontalProgressBar.setStartProgress(progress);
                break;

            case R.id.hsb_end_progress:
                horizontalProgressBar.setEndProgress(progress);
                break;

            case R.id.hsb_track_width:
                horizontalProgressBar.setTrackWidth((int)(progress*0.5));
                break;

            case R.id.hsb_text_size:
                horizontalProgressBar.setProgressTextSize((int)(progress*0.3));
                break;

            case R.id.hsb_corner_radius:
                horizontalProgressBar.setProgressCornerRadius((int)(progress*0.5));
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
            case R.id.hsc_isTracked:
                if(isChecked){
                    horizontalProgressBar.setTrackEnabled(true);
                }else {
                    horizontalProgressBar.setTrackEnabled(false);
                }
                break;

            case R.id.hsc_text_visibility:
                if(isChecked){
                    horizontalProgressBar.setProgressTextVisibility(true);
                }else {
                    horizontalProgressBar.setProgressTextVisibility(false);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.hb_start){
            horizontalProgressBar.startProgressAnimation();
        }else if(v.getId() == R.id.hb_download){
            downloadTheFile();
        }
    }

    @Override
    public void onHorizontalProgressStart(View view) {

    }

    @Override
    public void onHorizontalProgressUpdate(View view,float progress) {
        textView_call_back.setText("progress: "+ (int) (progress)+"%");
        //startBubbleAnimation(progress);
    }

    @Override
    public void onHorizontalProgressFinished(View view) {

    }


    private void downloadTheFile(){

        DownloadUtil.getInstance().download(VIDEO_URL, SAVED_PATH, new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(String path) {

                Toast.makeText(getContext(), "the file is saved", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDownloading(float progress) {
                horizontalProgressBar.setProgress(progress);
            }

            @Override
            public void onDownloadFailed() {
                Log.e(TAG, "downloadTheFile: >>>>>>failed" );
            }
        });
    }
}
