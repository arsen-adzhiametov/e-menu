package com.lutshe.emenu.view.ordermakingview;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EFragment;
import com.lutshe.emenu.R;
import com.lutshe.emenu.database.DishDao;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EFragment
public class CategoryListFragment extends ListFragment {

    @Bean
    DishDao dishDao;

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        String[] values = new String[] {
                "категория 0",
                "категория 1",
                "категория 2",
                "категория 3",
                "категория 4",
                "категория 5",
                "категория 6"};
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }



    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.details_fragment, DetailsFragment_.builder().index(pos).build());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

}
