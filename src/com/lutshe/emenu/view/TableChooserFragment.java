package com.lutshe.emenu.view;

import android.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.googlecode.androidannotations.annotations.*;
import com.lutshe.emenu.FragmentsSwitcher;
import com.lutshe.emenu.R;
import com.lutshe.emenu.database.TableDao;
import com.lutshe.emenu.model.Table;
import com.lutshe.emenu.view.ordermakingview.OrderMakingFragment_;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EFragment(R.layout.table_chooser_fragment)
public class TableChooserFragment extends Fragment {

    @Bean
    FragmentsSwitcher fragmentsSwitcher;

    @FragmentArg
    Integer hallId;

    @ViewById(R.id.table_list)
    RelativeLayout tableListRelativeLayout;

    @Bean
    TableDao tableDao;

    @AfterViews
    void init(){
        Table[] tables = tableDao.getTablesByHallId(hallId);
        for (Table t : tables){
             drawTable(t);
        }
    }

    private void drawTable(Table table) {
        Button button = getButton(table);
        tableListRelativeLayout.addView(button);
    }

    private Button getButton(Table table) {
        Button button = new Button(getActivity());
        button.setText("T" + table.getId());
        button.setWidth(table.getWidth());
        button.setHeight(table.getHeight());
        button.setX(table.getPositionX());
        button.setY(table.getPositionY());
        button.setId(table.getId() + 1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentsSwitcher.show(new OrderMakingFragment_());
            }
        });
        return button;
    }

}
