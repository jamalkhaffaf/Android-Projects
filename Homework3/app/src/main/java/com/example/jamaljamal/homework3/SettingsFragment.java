package com.example.jamaljamal.homework3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by jamaljamal on 3/6/15.
 * Fragment associated with "SettingsAcitivity.class" - used for the "Settings" page
 * Allows user to change text color for the TextView on the Main Activity
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public SettingsFragment(){}

    @Override
    public void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);

        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Preference preference = findPreference(key);

        if (preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preference;
            preference.setSummary(listPreference.getEntry());
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        initSummary();
    }

    @Override
    public void onPause(){
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    // Method used to persist the selected color in "summary" section
    public void initSummary(){
        SharedPreferences prefs = getPreferenceManager().getSharedPreferences();
        Preference preference = findPreference("color");
        String defualtSummary = "Select text color";
        String currentSummary = prefs.getString("color", "");

        // I kept trying to retrieve the key instead of the value but I was not able so I took the long way
        // It will be great if you show me how to retrieve the "Key" instead of the value.
        if (!currentSummary.isEmpty()){
            switch (currentSummary){
                case "#0000FF":
                    preference.setSummary("Blue");
                    break;
                case "#ff0000":
                    preference.setSummary("Red");
                    break;
                case "#008B00":
                    preference.setSummary("Green");
                    break;
                case "#000000":
                    preference.setSummary("Black");
                    break;
            }
        } else{
            preference.setSummary(defualtSummary);
        }
    }
}
