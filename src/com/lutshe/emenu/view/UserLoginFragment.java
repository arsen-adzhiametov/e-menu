package com.lutshe.emenu.view;

import android.app.Fragment;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import com.lutshe.emenu.FragmentsSwitcher;
import com.lutshe.emenu.R;
import com.lutshe.emenu.database.WorkerDao;
import com.lutshe.emenu.model.Worker;
import com.lutshe.emenu.prefs.MyPrefs_;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EFragment(R.layout.user_login_fragment)
public class UserLoginFragment extends Fragment {

    @ViewById(R.id.editTextPassword)
    TextView editTextPassword;
    @Bean
    FragmentsSwitcher fragmentsSwitcher;
    @Bean
    WorkerDao workerDao;
    @Pref
    MyPrefs_ myPrefs;
    private StringBuilder stringBuilder = new StringBuilder("");

    @Click(R.id.b0)
    void button0Pressed() {
        onDigitPressed("0");
    }

    @Click(R.id.b1)
    void button1Pressed() {
        onDigitPressed("1");
    }

    @Click(R.id.b2)
    void button2Pressed() {
        onDigitPressed("2");
    }

    @Click(R.id.b3)
    void button3Pressed() {
        onDigitPressed("3");
    }

    @Click(R.id.b4)
    void button4Pressed() {
        onDigitPressed("4");
    }

    @Click(R.id.b5)
    void button5Pressed() {
        onDigitPressed("5");
    }

    @Click(R.id.b6)
    void button6Pressed() {
        onDigitPressed("6");
    }

    @Click(R.id.b7)
    void button7Pressed() {
        onDigitPressed("7");
    }

    @Click(R.id.b8)
    void button8Pressed() {
        onDigitPressed("8");
    }

    @Click(R.id.b9)
    void button9Pressed() {
        onDigitPressed("9");
    }

    @Click(R.id.bBks)
    void buttonBksPressed() {
        int index = stringBuilder.length() - 1;
        if (index >= 0) {
            stringBuilder.deleteCharAt(index);
        }
        editTextPassword.setText(stringBuilder);
    }

    @Click(R.id.bOk)
    void buttonOkPressed() {
        String workerPasswordInput = stringBuilder.toString();
        Worker worker = null;
        Worker[] workers = workerDao.getAllWorkers();
        for (Worker w : workers) {
            if (w.getPassword().equals(workerPasswordInput)) {
                worker=w;
                break;
            }
        }
        if (worker!=null){
            myPrefs.workerName().put(worker.getName());
            fragmentsSwitcher.show(new MainWorkingMenuFragment_());
        }else {
            editTextPassword.setText("Неверный пароль");
            stringBuilder.delete(0,stringBuilder.length());
        }
    }

    @Click(R.id.resetButton)
    void onResetPressed() {
        fragmentsSwitcher.show(new ResetDataFragment_());
    }

    private void onDigitPressed(String keyValue) {
        stringBuilder.append(keyValue);
        editTextPassword.setText(stringBuilder);
    }

}
