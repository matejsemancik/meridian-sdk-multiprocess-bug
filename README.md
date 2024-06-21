# Meridian SDK multiprocess bug reproduction sample

This repository demonstrates an issue with [Meridian Android SDK](https://docs.meridianapps.com/hc/en-us/articles/360039670134-The-Android-SDK-Guide) version `10.2.0`,
where Activity in non-default process is unable to use `MeridianLocationManager` to observe location, due to the crash when location listening is started:

```stacktrace
java.lang.ClassCastException: android.os.BinderProxy cannot be cast to com.arubanetworks.meridian.location.MeridianLocationService$LocationManagerBinder
    at com.arubanetworks.meridian.location.MeridianLocationManager$b.onServiceConnected(Unknown Source:8)
    at android.app.LoadedApk$ServiceDispatcher.doConnected(LoadedApk.java:2198)
    at android.app.LoadedApk$ServiceDispatcher$RunConnection.run(LoadedApk.java:2231)
    at android.os.Handler.handleCallback(Handler.java:958)
    at android.os.Handler.dispatchMessage(Handler.java:99)
    at android.os.Looper.loopOnce(Looper.java:205)
    at android.os.Looper.loop(Looper.java:294)
    at android.app.ActivityThread.main(ActivityThread.java:8177)
    at java.lang.reflect.Method.invoke(Native Method)
    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:552)
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:971)
```

This crash happens for all activities which explicitly specify `android:process` in `AndroidManifest.xml`.

### Sample project setup

Create a `secrets.properties` file with your Meridian Editor credentials:

```properties
MERIDIAN_APP_ID=****
MERIDIAN_EDITOR_TOKEN=****
```

### Repro steps

In this sample project, the `SecondaryActivity` is configured to be launched in non-default process.  
The app is configured to observe simulated location from Meridian Editor.

1. Launch the app -> App opens `MainActivity` in default process.
2. Optional: Start observing location in this Activity -> No crash happens.
3. Click "New process" button -> The `SecondaryActivity` in non-default process is launched.
4. Click on "Start listening for location" -> Observe mentioned crash 👆
