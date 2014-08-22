package com.lutshe.emenu;

import android.app.Activity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.lutshe.emenu.view.ServerSetupFragment_;

/**
 * Created by Arsen Adzhiametov on 21/08/13.
 */
@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @Bean
    FragmentsSwitcher fragmentsSwitcher;

    @AfterViews
    public void initViews() {
        fragmentsSwitcher.setActivity(this);
        fragmentsSwitcher.show(new ServerSetupFragment_());
    }

}
