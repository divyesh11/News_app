package com.example.newsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.preference.PreferenceFragment;

import java.util.ArrayList;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

public class settings_activity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activity);
    }

    public static class newsappfiltersfragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

            Preference catogary=findPreference(getString(R.string.settings_catogary_key));
            bindPreferenceSummaryToValue(catogary);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            ListPreference listPreference=(ListPreference) preference;
            int prefIndex=listPreference.findIndexOfValue(stringValue);

            if(prefIndex>=0) {
                CharSequence[] lables = listPreference.getEntries();
                preference.setSummary(lables[prefIndex]);
            }
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference)
        {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferencestring=sharedPreferences.getString(preference.getKey(),"");
            onPreferenceChange(preference,preferencestring);
        }
    }

}