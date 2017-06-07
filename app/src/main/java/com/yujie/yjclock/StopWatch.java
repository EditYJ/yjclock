package com.yujie.yjclock;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class StopWatch extends LinearLayout {
    public StopWatch(Context context) {
        super(context);
    }

    public StopWatch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Hour = (TextView) findViewById(R.id.st_tv_hour);
        Min = (TextView) findViewById(R.id.st_tv_min);
        Sec = (TextView) findViewById(R.id.st_tv_sec);
        mSec = (TextView) findViewById(R.id.st_tv_msec);
        Hour.setText("0");
        Min.setText("0");
        Sec.setText("0");
        mSec.setText("0");

        Start = (Button) findViewById(R.id.st_but_start);
        Start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartTimer();
                Start.setVisibility(View.GONE);
                Pause.setVisibility(View.VISIBLE);
                Lap.setVisibility(View.VISIBLE);
            }
        });
        reSet = (Button) findViewById(R.id.st_but_reset);
        reSet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StopTime();
                timeCount = 0;
                adapter.clear();
                reSet.setVisibility(View.GONE);
                Resume.setVisibility(View.GONE);
                Start.setVisibility(View.VISIBLE);
            }
        });
        Lap = (Button) findViewById(R.id.st_but_lap);
        Lap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                adapter.insert(String.format("%d:%d:%d:%d", timeCount / 100 / 60 / 60, timeCount / 100 / 60 % 60, timeCount / 100 % 60, timeCount % 100), 0);
            }
        });
        Pause = (Button) findViewById(R.id.st_but_pause);
        Pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StopTime();
                Pause.setVisibility(View.GONE);
                Lap.setVisibility(View.GONE);
                Resume.setVisibility(View.VISIBLE);
                reSet.setVisibility(View.VISIBLE);
            }
        });
        Resume = (Button) findViewById(R.id.st_but_resume);
        Resume.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartTimer();
                Resume.setVisibility(View.GONE);
                reSet.setVisibility(View.GONE);
                Pause.setVisibility(View.VISIBLE);
                Lap.setVisibility(View.VISIBLE);
            }
        });
        lvLapTime = (ListView) findViewById(R.id.lv_stopwatch);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        lvLapTime.setAdapter(adapter);

        reSet.setVisibility(View.GONE);
        Lap.setVisibility(View.GONE);
        Pause.setVisibility(View.GONE);
        Resume.setVisibility(View.GONE);
        showTimeTask=new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG_WHAT_SHOW_TIME);
            }
        };
        timer.schedule(showTimeTask,200,200);
    }

    private void StartTimer() {
        if (timerTask == null) {
            timerTask=new TimerTask() {
                @Override
                public void run() {
                    timeCount++;
                }
            };
        }
        timer.schedule(timerTask, 10, 10);
    }

    private void StopTime() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case MSG_WHAT_SHOW_TIME:
                    Hour.setText(timeCount / 100 / 60 / 60+"");
                    Min.setText(timeCount / 100 / 60 % 60+"");
                    Sec.setText(timeCount / 100 % 60 +"");
                    mSec.setText(timeCount % 100+"");
                    break;
                default:
                    break;
            }
        }
    };

    private final int MSG_WHAT_SHOW_TIME = 1;
    private int timeCount=0;
    private Timer timer = new Timer();
    private TimerTask timerTask = null;
    private TimerTask showTimeTask = null;
    private TextView Hour,Min,Sec, mSec;
    private Button Start,reSet,Lap,Pause, Resume;
    private ListView lvLapTime;
    private ArrayAdapter<String> adapter;
}
