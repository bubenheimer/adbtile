package org.bubenheimer.adbtile;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;

final class Utils {
    static boolean isWriteSecureSettingsPermissionGranted(final Context context) {
        return context.getPackageManager().checkPermission(
                Manifest.permission.WRITE_SECURE_SETTINGS,
                context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
    }

    static AlertDialog getPermissionsReminderDialog(final Context context) {
        return new AlertDialog.Builder(context)
                .setTitle(R.string.permission_request_title)
                .setMessage(context.getString(R.string.write_secure_settings_permission_msg,
                        BuildConfig.APPLICATION_ID))
                .create();
    }

    private Utils() {
        throw new UnsupportedOperationException();
    }
}
