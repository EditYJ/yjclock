package com.yujie.yjclock;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class alarmView extends LinearLayout {
    private ListView alarmList;
    private Button addAlarm;
    private ArrayAdapter<timeNum> adapter;
    public alarmView(Context context) {
        super(context);
    }

    public alarmView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public alarmView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        alarmList = (ListView) findViewById(R.id.alarm_list);
        addAlarm = (Button) findViewById(R.id.add_alarm);
        //设置列表资源
        adapter = new ArrayAdapter<timeNum>(getContext(), android.R.layout.simple_list_item_1);
        alarmList.setAdapter(adapter);
        //adapter.add(new timeNum(System.currentTimeMillis()));
        //按钮事件
        addAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addTime();
            }
        });
    }

    private void addTime() {
        //TODO
        adapter.add(new timeNum(System.currentTimeMillis()));
    }

    private class timeNum {
        public timeNum(long time) {
            this.time = time;
            date = new Date(time);
            timeLab = date.getHours() + ":" + date.getMinutes();
        }

        public long getTime() {
            return time;
        }

        public String getTimeLab() {
            return timeLab;
        }

        @Override
        public String toString() {
            return timeLab;
        }

        public long time=0;
        public String timeLab="";
        public Date date;
    }

}
