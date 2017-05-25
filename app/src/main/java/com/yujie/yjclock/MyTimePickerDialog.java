package com.yujie.yjclock;

import android.app.TimePickerDialog;
import android.content.Context;

/**
 * Created by Administrator on 2017/5/25 0025.
 * 主要是解决4.1，4.2TimePickerDialog重复执行两次的bug
 */

public class MyTimePickerDialog extends TimePickerDialog {
    public MyTimePickerDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
    }

    public MyTimePickerDialog(Context context, int themeResId, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, themeResId, listener, hourOfDay, minute, is24HourView);
    }

    @Override
    protected void onStop() {

    }
}
