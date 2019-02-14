package org.schabi.newpipelegacy.settings;

import android.os.Bundle;
import android.support.v7.preference.Preference;

import org.schabi.newpipelegacy.BuildConfig;
import org.schabi.newpipelegacy.CheckForNewAppVersionTask;
import org.schabi.newpipelegacy.R;

public class MainSettingsFragment extends BasePreferenceFragment {
    public static final boolean DEBUG = !BuildConfig.BUILD_TYPE.equals("release");

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.main_settings);

        if (!CheckForNewAppVersionTask.isGithubApk()) {
            final Preference update = findPreference(getString(R.string.update_pref_screen_key));
            getPreferenceScreen().removePreference(update);

            defaultPreferences.edit().putBoolean(getString(R.string.update_app_key), false).apply();
        }

        if (!DEBUG) {
            final Preference debug = findPreference(getString(R.string.debug_pref_screen_key));
            getPreferenceScreen().removePreference(debug);
        }
    }
}