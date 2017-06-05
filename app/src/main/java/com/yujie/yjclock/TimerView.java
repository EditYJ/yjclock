package com.yujie.yjclock;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2017/6/3.
 */

public class TimerView extends LinearLayout {

    public TimerView(Context context) {
        super(context);
    }

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        btnStart = (Button) findViewById(R.id.bt_time_start);//开始按钮
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartTime();
                btnStart.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.VISIBLE);
            }
        });
        btnPause = (Button) findViewById(R.id.bt_time_pause);//暂停
        btnPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StopTime();
                btnPause.setVisibility(View.GONE);
                btnResume.setVisibility(View.VISIBLE);
            }
        });
        btnResume = (Button) findViewById(R.id.bt_time_resume);//继续
        btnResume.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartTime();
                btnResume.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
            }
        });
        btnReset = (Button) findViewById(R.id.bt_time_reset);//重置按钮
        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StopTime();
                edHour.setText("00");
                edMin.setText("00");
                edSec.setText("00");

                btnStart.setVisibility(View.VISIBLE);
                btnStart.setEnabled(false);
                btnPause.setVisibility(View.GONE);
                btnResume.setVisibility(View.GONE);
                btnReset.setVisibility(View.GONE);
            }
        });
        edHour = (EditText) findViewById(R.id.ed_hour);
        edMin = (EditText) findViewById(R.id.ed_min);
        edSec = (EditText) findViewById(R.id.ed_sec);

        edHour.setText("00");
        edHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    int textContent = Integer.parseInt(s.toString());
                    if (textContent > 59) {
                        edHour.setText("59");
                    } else if (textContent<0){
                        edHour.setText("00");
                    }
                }
                CheckBtnStartEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edMin.setText("00");
        edMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    int textContent = Integer.parseInt(s.toString());
                    if (textContent > 59) {
                        edMin.setText("59");
                    } else if (textContent<0){
                        edMin.setText("00");
                    }
                }
                CheckBtnStartEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edSec.setText("00");
        edSec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    int textContent = Integer.parseInt(s.toString());
                    if (textContent > 59) {
                        edSec.setText("59");
                    } else if (textContent<0){
                        edSec.setText("00");
                    }
                }
                CheckBtnStartEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnStart.setVisibility(View.VISIBLE);
        btnStart.setEnabled(false);
        btnPause.setVisibility(View.GONE);
        btnResume.setVisibility(View.GONE);
        btnReset.setVisibility(View.GONE);
    }
    //检查是否可以启用开始按钮
    private void CheckBtnStartEnable() {
        //TODO
        btnStart.setEnabled((!TextUtils.isEmpty(edHour.getText().toString())&&Integer.parseInt(edHour.getText().toString()) > 0) ||
                (!TextUtils.isEmpty(edMin.getText().toString())&&Integer.parseInt(edMin.getText().toString()) > 0 )||
                (!TextUtils.isEmpty(edSec.getText().toString())&&Integer.parseInt(edSec.getText().toString()) > 0));
    }

    //开始计时
    private void StartTime() {
        if (timerTask == null) {
            timerCount = Integer.parseInt(edHour.getText().toString()) * 60 * 60 +
                    Integer.parseInt(edMin.getText().toString()) * 60 +
                    Integer.parseInt(edSec.getText().toString());
            timerTask=new TimerTask() {
                @Override
                public void run() {
                    timerCount--;
                    if (timerCount == 0) {
                        handler.sendEmptyMessage(MSG_WHAT_TIME_IS_UP);
                        StopTime();
                    } else {
                        handler.sendEmptyMessage(MSG_WHAT_TIME_IS_RUN);
                    }
                }
            };
            timer.schedule(timerTask,1000,1000);
        }
    }

    private void StopTime() {
        timerTask.cancel();
        timerTask = null;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_WHAT_TIME_IS_UP:
                    new AlertDialog.Builder(getContext())
                            .setTitle("Time is up")
                            .setMessage("Time is up")
                            .setNegativeButton("Cancel",null)
                            .show();
                    btnStart.setVisibility(View.VISIBLE);
                    btnStart.setEnabled(false);
                    btnPause.setVisibility(View.GONE);
                    btnResume.setVisibility(View.GONE);
                    btnReset.setVisibility(View.GONE);
                    break;
                case MSG_WHAT_TIME_IS_RUN:
                    int hour = timerCount / 60 / 60;
                    int min = timerCount / 60 % 60;
                    int sec = timerCount % 60;
                    edHour.setText(hour+"");
                    edMin.setText(min+"");
                    edSec.setText(sec+"");
                    break;
                default:

                    break;
            }
        }
    };
    private Button btnStart,btnPause,btnResume,btnReset;
    private EditText edHour,edMin,edSec;
    private Timer timer = new Timer();
    private TimerTask timerTask = null;
    private int timerCount = 0;
    private final int MSG_WHAT_TIME_IS_UP = 1;
    private final int MSG_WHAT_TIME_IS_RUN = 2;
}
