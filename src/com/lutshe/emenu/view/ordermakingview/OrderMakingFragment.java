package com.lutshe.emenu.view.ordermakingview;

import android.app.Fragment;
import android.app.FragmentTransaction;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.lutshe.emenu.R;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */

@EFragment(R.layout.order_making_fragment)
public class OrderMakingFragment extends Fragment {

    @AfterViews
    void init(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.list_fragment, new CategoryListFragment());
        ft.add(R.id.details_fragment, new DetailsFragment_());
        ft.add(R.id.modifications_fragment, new ModificationsFragment_());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
