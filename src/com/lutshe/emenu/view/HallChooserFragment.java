package com.lutshe.emenu.view;

import android.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lutshe.emenu.FragmentsSwitcher;
import com.lutshe.emenu.R;
import com.lutshe.emenu.database.HallDao;
import com.lutshe.emenu.model.Hall;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EFragment(R.layout.hall_chooser_fragment)
public class HallChooserFragment extends Fragment {

    @ViewById(R.id.hall_list)
    RelativeLayout relativeLayout;
    @Bean
    HallDao hallDao;
    @Bean
    FragmentsSwitcher fragmentsSwitcher;

    @AfterViews
    void init() {
        Hall[] halls = hallDao.getAllHalls();

        for (int i = 0; i < halls.length; i++) {
            Button button = getButton(i);
            drawButton(button);
            addOnClickListener(button);
        }
    }

    private void drawButton(Button button) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        if (button.getId() != 1) {
            layoutParams.addRule(RelativeLayout.BELOW, button.getId() - 1);
        }
        relativeLayout.addView(button, layoutParams);
    }

    private Button getButton(int i) {
        Button button = new Button(getActivity());
        button.setText("Hall " + i);
        button.setId(i + 1);
        return button;
    }

    private void addOnClickListener(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = TableChooserFragment_.builder().hallId(button.getId() - 1).build();
                fragmentsSwitcher.show(fragment);
            }
        });
    }
}
