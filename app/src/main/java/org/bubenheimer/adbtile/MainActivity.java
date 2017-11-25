package org.bubenheimer.adbtile;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import static org.bubenheimer.adbtile.Utils.isWriteSecureSettingsPermissionGranted;

public final class MainActivity extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActionBar(findViewById(R.id.toolbar));

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.prefs_frame, new SettingsFragment())
                .commit();
    }

    public static final class SettingsFragment extends PreferenceFragment
            implements Preference.OnPreferenceClickListener {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

            getWriteSecureSettingsPreference().setOnPreferenceClickListener(this);
        }

        @Override
        public void onDestroy() {
            getWriteSecureSettingsPreference().setOnPreferenceClickListener(null);
            super.onDestroy();
        }

        @Override
        public boolean onPreferenceClick(final Preference preference) {
            Utils.getPermissionsReminderDialog(getContext()).show();
            return false;
        }

        @Override
        public void onResume() {
            super.onResume();

            getWriteSecureSettingsPreference().setSummary(
                    isWriteSecureSettingsPermissionGranted(getContext()) ?
                            R.string.granted : R.string.not_granted);
        }

        private Preference getWriteSecureSettingsPreference() {
            return findPreference(getContext().getString(R.string.pref_write_secure_settings));
        }
    }
}
