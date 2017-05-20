package com.yujie.yjclock;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Timer;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class timeView extends LinearLayout {
    private TextView tvShow;
    public timeView(Context context) {
        super(context);
    }

    public timeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public timeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvShow = (TextView) findViewById(R.id.showtime);
        tvShow.setText("Hello");
        timeHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        if (visibility == View.VISIBLE) {
            timeHandler.sendEmptyMessage(0);
        } else {
            timeHandler.removeMessages(0);
        }
    }


    public void reflashTime() {
        Calendar data = Calendar.getInstance();
        tvShow.setText(String.format("%d:%d:%d",data.get(Calendar.HOUR_OF_DAY),data.get(Calendar.MINUTE),data.get(Calendar.SECOND)));
    }

    private Handler timeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            reflashTime();
            if (getVisibility() == View.VISIBLE) {
                timeHandler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };
}
