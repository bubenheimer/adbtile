package org.bubenheimer.adbtile;

import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public final class AdbTile extends TileService {
    private static final String ADB_ENABLED = Settings.Global.ADB_ENABLED;

    @Override
    public void onStartListening() {
        setState(isAdbEnabled());
    }

    @Override
    public void onClick() {
        unlockAndRun(() -> {
            if (!Utils.isWriteSecureSettingsPermissionGranted(this)) {
                showDialog(Utils.getPermissionsReminderDialog(AdbTile.this));
            } else {
                final Tile qsTile = getQsTile();
                final boolean enable = qsTile.getState() == Tile.STATE_INACTIVE;
                setState(enable);
                setAdbEnabled(enable);
            }
        });
    }

    private void setState(final boolean enabled) {
        final Tile qsTile = getQsTile();
        qsTile.setState(enabled ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
        qsTile.updateTile();
    }

    private boolean isAdbEnabled() {
        return Settings.Global.getInt(getContentResolver(), ADB_ENABLED, 0) != 0;
    }

    private void setAdbEnabled(final boolean enabled) {
        Settings.Global.putInt(getContentResolver(), ADB_ENABLED, enabled ? 1 : 0);
    }
}
