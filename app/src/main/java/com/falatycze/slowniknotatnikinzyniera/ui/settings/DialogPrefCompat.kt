package com.falatycze.slowniknotatnikinzyniera.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceDialogFragmentCompat

class DialogPrefCompat : PreferenceDialogFragmentCompat() {

    lateinit var positiveResult: ()->Unit

    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            positiveResult()
        }
    }

    companion object {
        fun newInstance(key: String): DialogPrefCompat {
            val fragment = DialogPrefCompat()
            val bundle = Bundle(1)
            bundle.putString(PreferenceDialogFragmentCompat.ARG_KEY, key)
            fragment.arguments = bundle
            return fragment
        }
    }
}