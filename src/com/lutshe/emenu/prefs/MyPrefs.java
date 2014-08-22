package com.lutshe.emenu.prefs;

import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@SharedPref
public interface MyPrefs {

    String workerName();
}
