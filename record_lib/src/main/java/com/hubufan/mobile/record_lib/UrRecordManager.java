package com.hubufan.mobile.record_lib;

import android.app.Application;

import com.zlw.main.recorderlib.RecordManager;
import com.zlw.main.recorderlib.recorder.RecordConfig;
import com.zlw.main.recorderlib.recorder.listener.RecordResultListener;

import java.io.File;

/**
 * @author With You
 * @version 5.0.0
 * @date 2020/04/16 15:34
 * @email 1713397546@qq.com
 * @description
 */
public class UrRecordManager {

    private volatile static UrRecordManager instance;
    private Application context;

    private UrRecordManager() {
    }

    public static UrRecordManager getInstance() {
        if (instance == null) {
            synchronized (UrRecordManager.class) {
                if (instance == null) {
                    instance = new UrRecordManager();
                }
            }
        }
        return instance;
    }

    public void init(Application application) {
        RecordManager.getInstance().init(application, true);
        RecordManager.getInstance().changeFormat(RecordConfig.RecordFormat.MP3);
    }

    public void setResultListener(final UrRecordResultListener resultListener) {
        RecordManager.getInstance().setRecordResultListener(new RecordResultListener() {
            @Override
            public void onResult(File result) {
                resultListener.onResult(result);
            }
        });
    }

    /**
     * 开始录音
     */
    public void start() {
        RecordManager.getInstance().start();
    }

    /**
     * 关闭录音
     */
    public void stop() {
        RecordManager.getInstance().stop();
    }

}
