package com.lutshe.emenu.view;

import android.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.*;
import com.lutshe.emenu.FragmentsSwitcher;
import com.lutshe.emenu.R;
import com.lutshe.emenu.database.ServerDao;
import com.lutshe.emenu.model.Server;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EFragment(R.layout.server_setup_fragment)
public class ServerSetupFragment extends Fragment {

    @ViewById(R.id.initializationText)
    TextView text;
    @ViewById(R.id.server_url)
    EditText serverUrlField;
    @ViewById(R.id.login)
    EditText loginField;
    @ViewById(R.id.password)
    EditText passwordField;

    @Bean
    ServerDao serverDao;
    @Bean
    FragmentsSwitcher fragmentsSwitcher;

    @AfterViews
    void bind() {
        Server server = serverDao.getServer();
        if (server != null) {
            serverUrlField.setText(server.getUrl());
            loginField.setText(server.getLogin());
            passwordField.setText(server.getPassword());
        }
    }

    @Click(R.id.loadDataButton)
    void load() {
        fragmentsSwitcher.show(new ProgressFragment_());
    }

    @Click(R.id.aboutButton)
    void showAbout() {
        fragmentsSwitcher.show(new AboutFragment_());
    }
}
