package com.example.renchunlin.electricity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.renchunlin.electricity.service.MyServiceMusic;
import com.example.renchunlin.electricity.utils.ShareUtils;

/**
 * 项目名：   Electricity
 * 包名：     package com.example.renchunlin.electricity.receiver;
 * 文件名：   OnBootReceiver
 * 创建者：   RCL
 * 创建时间： 2017/8/22 10:41
 * 描述：     OnBootReceiver
 */
public class OnBootReceiver extends BroadcastReceiver{
    private static final String TAG = "SmartService";

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isSpeak= ShareUtils.getBoolean(context,"isSpeak",false);

        if (isSpeak) {

            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                /*// 开启应用
                Intent sintent = context.getPackageManager().getLaunchIntentForPackage( "com.example.renchunlin.music1.MainActivity");
                context.startActivity( sintent );*/

                    // 开启服务代码
                    context.startService(new Intent(context, MyServiceMusic.class));
                    Log.e(TAG, "Brodcast received!!!");
            }
        }

    }
}
