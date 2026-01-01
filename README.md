# ADB Tile

ADB Tile provides an Android Quick Settings Tile to enable and disable Android USB debugging.

ADB Tile makes it easy to enable and disable USB debugging, and the tile also helps with not forgetting to disable USB debugging after a debugging session is done to keep the phone secure.

## Installation
The app should be installed from [playstore](https://play.google.com/store/apps/details?id=org.bubenheimer.adbtile&hl=en_ZA). Or you can paste the playstore link in [here](https://apkcombo.com/downloader/#package=org.bubenheimer.adbtile&device=phone&sdk=30) and download the `.apk` file.

## Setup

It is necessary to manually give the app the permission to read secure system settings. The permission will survive reboots. This is needed to access the USB debugging setting. The app does not require a rooted phone.

To provide the permissions, use the adb command as below after the app has installed.

``` shell
adb shell pm grant org.bubenheimer.adbtile android.permission.WRITE_SECURE_SETTINGS
```
> Note: no need to do `adb shell` first and then run rest of the command. 
> Run the above command from your terminal. First ensuring that your mobile device is connected with USB debugging.

This app is forked from another quick settings tile app from Francesco Franco, Demo Mode Tile, which he kindly made available as open source. Many thanks to him for showing how it's done.

Quick settings tiles are available in Android Nougat (7.0) and later. This app will not work in earlier Android versions.

All images are derived from Apache-licensed clip art in Android Studio 2.3.

This project is licensed under the terms of the Apache License Version 2.0.
