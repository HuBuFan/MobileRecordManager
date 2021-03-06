# MobileRecordManager

Android 9.0 以下手机拨号自动录音功能

ps：android P的系统为了进一步提高用户的安全隐私，禁止了后台闲置应用使用麦克风。

- 推荐个超级nice的权限请求
https://github.com/HuBuFan/UrPermissionHelper

``` 
dependencies {
        implementation 'com.github.HuBuFan:MobileRecordManager:latestVersion'
}

allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}
``` 

使用方式：

第一步：添加相关权限
``` 
<uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS" />
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
``` 
第二步：在manifest注册广播以及服务
``` 
<receiver android:name="com.hubufan.mobile.record_lib.UrMobileManagerReceiver">
     <intent-filter android:priority="1000">
         <action android:name="android.intent.action.NEW_OUTGOING_CALL" />
         <action android:name="android.intent.action.PHONE_STATE" />
     </intent-filter>
</receiver>
<service android:name="com.zlw.main.recorderlib.recorder.RecordService" />
``` 
第三步：初始化
``` 
UrRecordManager.getInstance().init(getApplication());
``` 
第四步：录音结果监听
``` 
 UrRecordManager.getInstance().setResultListener(new UrRecordResultListener() {
       @Override
       public void onResult(File result) {
          Log.d(TAG, "**********************录音文件为" + result.getAbsolutePath() + "**********************");
       }
    });
```
第五步：在页面销毁时关闭录音

```
@Override
protected void onDestroy() {
    super.onDestroy();
    UrRecordManager.getInstance().stop();
}
```
以上操作就可以时间手机拨号录音功能，如果有其他需要请结合 start()、stop()自己使用
- 手动录音

```
UrRecordManager.getInstance().start();
```

- 关闭录音

```
UrRecordManager.getInstance().stop();
```
