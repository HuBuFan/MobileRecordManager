# MobileRecordManager
Android P 以下手机拨号录音功能

使用方式：

第一步：添加相关权限

<uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS" />
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />

第二步：在manifest注册广播以及服务

<receiver android:name="com.hubufan.mobile.record_lib.UrMobileManagerReceiver">
     <intent-filter android:priority="1000">
         <action android:name="android.intent.action.NEW_OUTGOING_CALL" />
         <action android:name="android.intent.action.PHONE_STATE" />
     </intent-filter>
</receiver>
<service android:name="com.zlw.main.recorderlib.recorder.RecordService" />
