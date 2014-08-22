package com.lutshe.emenu;

import android.app.Activity;
import android.app.Fragment;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.api.Scope;

/**
 * Created by Arsen Adzhiametov on 21/08/13.
 */
@EBean(scope = Scope.Singleton)
public class FragmentsSwitcher {

    private Activity activity;

    public void show(Fragment fragment) {
        activity.getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
