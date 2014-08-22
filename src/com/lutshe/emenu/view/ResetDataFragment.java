package com.lutshe.emenu.view;

import android.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lutshe.emenu.R;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EFragment(R.layout.reset_data_fragment)
public class ResetDataFragment extends Fragment {

    @ViewById(R.id.resetPassword)
    EditText resetEditText;

    @Click(R.id.finalReset)
    void onResetPressed(){
        if(resetEditText.getText().toString().equals("3.14")) {
            Toast.makeText(getActivity(),"Данные очищены. Шутка.", Toast.LENGTH_LONG).show();
        }
    }
}
