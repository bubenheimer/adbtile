# ADB Tile

ADB Tile provides an Android Quick Settings Tile to enable and disable Android USB debugging.

ADB Tile makes it easy to enable and disable USB debugging, and the tile also helps with not forgetting to disable USB debugging after a debugging session is done to keep the phone secure.

It is necessary to manually give the app the permission to read secure system settings. The permission will survive reboots. This is needed to access the USB debugging setting. The app does not require a rooted phone.

To provide the permissions, use the adb command as below after the app has installed.

    adb shell grant org.bubenheimer.adbtile android.permission.SECURE_SETTINGS

This app is forked from another quick settings tile app from Francesco Franco, Demo Mode Tile, which he kindly offered as open source. Many thanks to him for showing how it's done.

Quick settings tiles are available in Android Nougat (7.0) and later. This app will not work in earlier Android versions.

All images are derived from Apache-licensed clip art in Android Studio 2.3.

This project is licensed under the terms of the Apache License Version 2.0.
