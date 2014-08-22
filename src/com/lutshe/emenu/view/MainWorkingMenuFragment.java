package com.lutshe.emenu.view;

import android.app.Fragment;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.*;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import com.lutshe.emenu.FragmentsSwitcher;
import com.lutshe.emenu.R;
import com.lutshe.emenu.prefs.MyPrefs_;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EFragment(R.layout.main_working_menu)
public class MainWorkingMenuFragment extends Fragment {

    @Bean
    FragmentsSwitcher fragmentsSwitcher;

    @Pref
    MyPrefs_ myPrefs;

    @ViewById(R.id.worker_name)
    TextView workerNameTextView;

    @AfterViews
    void bind(){
        workerNameTextView.setText(myPrefs.workerName().get());
    }

    @Click(R.id.doOrder)
    void doOrder(){
        fragmentsSwitcher.show(new HallChooserFragment_());
    }

}
