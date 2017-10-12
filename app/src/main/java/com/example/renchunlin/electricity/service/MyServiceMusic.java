package com.example.renchunlin.electricity.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.example.renchunlin.electricity.R;

/**
 * 项目名：   Electricity
 * 包名：     package com.example.renchunlin.electricity.service;
 * 文件名：   MyServiceMusic
 * 创建者：   RCL
 * 创建时间： 2017/8/18 11:05
 * 描述：     MyServiceMusic
 */
public class MyServiceMusic extends Service {

    private MediaPlayer mPlayer;

    private int sum=0;

    @Override
    public void onCreate() {
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tsy);
        super.onCreate();

        //注册一个接受广播类型
        registerReceiver(batteryChangedReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    //接受广播
    private BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                sum=(level * 100 / scale);
                if(sum==100){

                    mPlayer.start();

                }
                //tvBatteryChanged.setText("电池电量：" + (level * 100 / scale) + "%");
            }
        }
    };

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(MyServiceMusic.this, R.string.open_service, Toast.LENGTH_SHORT).show();
        //mPlayer.start();
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        mPlayer.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}