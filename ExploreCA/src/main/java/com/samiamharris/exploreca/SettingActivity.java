package com.samiamharris.exploreca;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by samharris on 2/11/14.
 */
public class SettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //deprecated, replaced by fragment version
        addPreferencesFromResource(R.xml.settings);

    }
}
