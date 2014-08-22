package com.lutshe.emenu.view;

import android.app.Fragment;
import android.os.SystemClock;
import android.widget.ProgressBar;
import com.googlecode.androidannotations.annotations.*;
import com.lutshe.emenu.FragmentsSwitcher;
import com.lutshe.emenu.R;
import com.lutshe.emenu.database.HallDao;
import com.lutshe.emenu.database.ServerDao;
import com.lutshe.emenu.database.TableDao;
import com.lutshe.emenu.database.WorkerDao;
import com.lutshe.emenu.model.Data;
import com.lutshe.emenu.model.Hall;
import com.lutshe.emenu.model.Table;
import com.lutshe.emenu.wsclient.DataDeserializer;

import java.util.List;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EFragment(R.layout.progress_fragment)
public class ProgressFragment extends Fragment {

    @ViewById(R.id.progress)
    ProgressBar progressBar;

    volatile int progress = 0;

    @Bean
    FragmentsSwitcher fragmentsSwitcher;

    @Bean
    DataDeserializer dataDeserializer;

    @Bean
    WorkerDao workerDao;

    @Bean
    ServerDao serverDao;

    @Bean
    HallDao hallDao;

    @Bean
    TableDao tableDao;

    private static final String file = "data.xml";

    @AfterViews
    void init(){
        doBackGround();
        doBackGround2();

    }

    @UiThread
    void updateProgressBar(){
        progressBar.setMax(50);
        progressBar.setProgress(progress);
    }

    @Background
    void doBackGround(){
        while (progress < 50) {
            SystemClock.sleep(200);
            progress+=2;
        updateProgressBar();
        }
    }

    @Background
    void doBackGround2(){
        Data data = dataDeserializer.readFile(file);

        for(int i = 0; i < data.getWorkers().size(); i++){
            workerDao.addWorker(data.getWorkers().get(i));
        }

        List<Hall> halls = data.getHalls();
        for(int i = 0; i < halls.size(); i++){
            hallDao.addHall(halls.get(i));
            List<Table> tables = halls.get(i).getTables();
            for (int j = 0; j < tables.size(); j++) {
                  tableDao.addTable(tables.get(j), halls.get(i).getId());
            }
        }

        serverDao.addServer(data.getServer());

        fragmentsSwitcher.show(new UserLoginFragment_());
    }
}
