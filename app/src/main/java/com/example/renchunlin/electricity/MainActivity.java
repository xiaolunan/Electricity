package com.example.renchunlin.electricity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.renchunlin.electricity.service.MyServiceMusic;
import com.example.renchunlin.electricity.utils.ShareUtils;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    //充电提示开关
    private Switch sw_speak;

    //当前电量
    private TextView tvBatteryChanged;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册一个接受广播类型
        registerReceiver(batteryChangedReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        //初始化变量
        initView();
    }

    //接受广播
    private BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                tvBatteryChanged.setText(getString(R.string.electricity) + (level * 100 / scale) + "%");
            }
        }
    };

    //初始化变量
    private void initView(){
        tvBatteryChanged = (TextView)findViewById(R.id.tvBatteryChanged);
        sw_speak = (Switch) findViewById(R.id.sw_speak);
        sw_speak.setOnCheckedChangeListener(this);

        boolean isSpeak = ShareUtils.getBoolean(this, "isSpeak", false);
        sw_speak.setChecked(isSpeak);
    }

    //点击事件
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked){
            startService(new Intent(this,MyServiceMusic.class));

            //保存状态
            ShareUtils.putBoolean(this, "isSpeak", sw_speak.isChecked());
        }else{
            stopService(new Intent(this,MyServiceMusic.class));

            //保存状态
            ShareUtils.putBoolean(this, "isSpeak", sw_speak.isChecked());
        }
    }
}