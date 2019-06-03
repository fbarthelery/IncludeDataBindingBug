IncludeDataBindingBug
=====================

DataBinding fails to bind an included layout when this layout is added to an "hidden" viewgroup.

```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android" xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>

    </data>
    <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:context=".MaterialCardViewActivity">

        <!-- replace this with CardView for a working version -->
        <com.google.android.material.card.MaterialCardView
                android:layout_width="match_parent"
                android:layout_height="wrap_content">

            <include android:id="@+id/form"
                     android:layout_width="match_parent" android:layout_height="match_parent"
                     app:name="@{`MaterialCardView`}"
                     layout="@layout/included_form"/>
        </com.google.android.material.card.MaterialCardView>
    </FrameLayout>
</layout>

```

Starting with "com.google.android.material:material:1.1.0-alpha01", `MaterialCardView` adds its children 
to a "hidden" `contentLayout` instead of adding them to the MaterialCardView itself.
This behavior is reproduced in `CustomViewGroup` class.

This works fine with a `CardView` as the included layout is added to CardView directly.

Stacktrace: 

```
2019-06-03 10:59:24.899 2077-2077/com.geekorum.includedatabindingbug E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.geekorum.includedatabindingbug, PID: 2077
    java.lang.RuntimeException: Unable to start activity ComponentInfo{com.geekorum.includedatabindingbug/com.geekorum.includedatabindingbug.CustomViewGroupActivity}: java.lang.NullPointerException: Attempt to invoke virtual method 'void com.geekorum.includedatabindingbug.databinding.IncludedFormBinding.invalidateAll()' on a null object reference
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3121)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3257)
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:81)
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135)
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1935)
        at android.os.Handler.dispatchMessage(Handler.java:107)
        at android.os.Looper.loop(Looper.java:214)
        at android.app.ActivityThread.main(ActivityThread.java:7116)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:925)
     Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'void com.geekorum.includedatabindingbug.databinding.IncludedFormBinding.invalidateAll()' on a null object reference
        at com.geekorum.includedatabindingbug.databinding.ActivityCustomViewBindingImpl.invalidateAll(ActivityCustomViewBindingImpl.java:53)
        at com.geekorum.includedatabindingbug.databinding.ActivityCustomViewBindingImpl.<init>(ActivityCustomViewBindingImpl.java:45)
        at com.geekorum.includedatabindingbug.databinding.ActivityCustomViewBindingImpl.<init>(ActivityCustomViewBindingImpl.java:33)
        at com.geekorum.includedatabindingbug.DataBinderMapperImpl.getDataBinder(DataBinderMapperImpl.java:58)
        at androidx.databinding.MergedDataBinderMapper.getDataBinder(MergedDataBinderMapper.java:74)
        at androidx.databinding.DataBindingUtil.bind(DataBindingUtil.java:199)
        at androidx.databinding.DataBindingUtil.bindToAddedViews(DataBindingUtil.java:327)
        at androidx.databinding.DataBindingUtil.setContentView(DataBindingUtil.java:306)
        at androidx.databinding.DataBindingUtil.setContentView(DataBindingUtil.java:284)
        at com.geekorum.includedatabindingbug.CustomViewGroupActivity.onCreate(CustomViewGroupActivity.kt:14)
        at android.app.Activity.performCreate(Activity.java:7698)
        at android.app.Activity.performCreate(Activity.java:7687)
        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1299)
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3096)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3257) 
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:81) 
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135) 
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95) 
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1935) 
        at android.os.Handler.dispatchMessage(Handler.java:107) 
        at android.os.Looper.loop(Looper.java:214) 
        at android.app.ActivityThread.main(ActivityThread.java:7116) 
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492) 
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:925) 
```

This was tested on both Android Gradle plugin 3.4.0 and 3.6.0-alpha02
