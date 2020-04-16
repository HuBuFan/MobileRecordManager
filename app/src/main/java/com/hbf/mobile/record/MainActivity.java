package com.hbf.mobile.record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hubufan.mobile.record_lib.UrRecordManager;
import com.hubufan.mobile.record_lib.UrRecordResultListener;
import com.hubufan.permission.UrPermissionHelper;
import com.hubufan.permission.listener.UrPermissionListener;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "UrMobileManager";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        UrRecordManager.getInstance().init(getApplication());
        UrRecordManager.getInstance().setResultListener(new UrRecordResultListener() {
            @Override
            public void onResult(File result) {
                Log.d(TAG, "**********************录音文件为" + result.getAbsolutePath() + "**********************");
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                UrRecordManager.getInstance().start();
                break;
            case R.id.btn_stop:
                UrRecordManager.getInstance().stop();
                break;
            case R.id.btn_phone:
                UrPermissionHelper.getInstance().with(this)
                        .addPermission(Manifest.permission.READ_PHONE_STATE)
                        .addPermission(Manifest.permission.RECORD_AUDIO)
                        .addPermission(Manifest.permission.CALL_PHONE)
                        .addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .addPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .addPermissionListener(new UrPermissionListener() {

                            @Override
                            public void requestPermissionsSuccess() {
                                callPhone("10000");
                            }

                            @Override
                            public void requestPermissionsFail() {
                                Toast.makeText(mContext, "授权失败", Toast.LENGTH_SHORT).show();
                            }
                        }).requestPermissions();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UrRecordManager.getInstance().stop();
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            return;
        startActivity(intent);
    }

}
