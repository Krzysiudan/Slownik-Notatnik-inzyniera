package com.falatycze.slowniknotatnikinzyniera.ui.settings

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.falatycze.slowniknotatnikinzyniera.R

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var settingsViewModel:SettingsViewModel

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

       settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

    override fun onDisplayPreferenceDialog(preference: Preference?) {
        val confirmationDialog = preference as? CustomConfirmationDialog
        if(confirmationDialog!=null) {
            val dialogFragment = DialogPrefCompat.newInstance(confirmationDialog.key)
            dialogFragment.setTargetFragment(this, 0)
            dialogFragment.positiveResult = {
                settingsViewModel.resetLearningProgress()
                Toast.makeText(activity as Context,"Learning progress has been reseted",Toast.LENGTH_SHORT).show()
            }
            dialogFragment.show(parentFragmentManager,null)
        } else {
            super.onDisplayPreferenceDialog(preference)

    }
    }
}