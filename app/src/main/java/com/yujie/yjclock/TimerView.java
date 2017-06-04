package com.yujie.yjclock;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by admin on 2017/6/3.
 */

public class TimerView extends LinearLayout {
    private Button btnStart,btnPause,btnResume,btnReset;
    private EditText edHour,edMin,edSec;
    public TimerView(Context context) {
        super(context);
    }

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        btnStart = (Button) findViewById(R.id.bt_time_start);
        btnPause = (Button) findViewById(R.id.bt_time_pause);
        btnResume = (Button) findViewById(R.id.bt_time_resume);
        btnReset = (Button) findViewById(R.id.bt_time_reset);

        edHour = (EditText) findViewById(R.id.ed_hour);
        edMin = (EditText) findViewById(R.id.ed_min);
        edSec = (EditText) findViewById(R.id.ed_sec);

    }
}
