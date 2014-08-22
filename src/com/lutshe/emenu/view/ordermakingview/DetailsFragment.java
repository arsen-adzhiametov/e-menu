package com.lutshe.emenu.view.ordermakingview;

import android.app.Fragment;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FragmentArg;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lutshe.emenu.R;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EFragment(R.layout.fragment_detail)
public class DetailsFragment extends Fragment {

    @FragmentArg
    Integer index;

    @ViewById(R.id.choosen_category)
    TextView chosenCategoryTextView;

    @AfterViews
    void bind(){
       chosenCategoryTextView.setText("Категория "+index);
    }
}
