package com.yujie.yjclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tabtime").setIndicator("时钟").setContent(R.id.tabtime));
        tabHost.addTab(tabHost.newTabSpec("tabalarm").setIndicator("闹钟").setContent(R.id.tabalarm));
        tabHost.addTab(tabHost.newTabSpec("tabtimer").setIndicator("计时器").setContent(R.id.tabtimer));
        tabHost.addTab(tabHost.newTabSpec("tabstopwatch").setIndicator("秒表").setContent(R.id.tabstopwatch));
    }
}
