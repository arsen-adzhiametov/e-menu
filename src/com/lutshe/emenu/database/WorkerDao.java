package com.lutshe.emenu.database;

import android.content.ContentValues;
import android.database.Cursor;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.lutshe.emenu.model.Worker;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EBean
public class WorkerDao {

    static final String WORKERS_TABLE = "worker";
    static final String WORKER_ID = "id";
    static final String WORKER_NAME = "name";
    static final String WORKER_PASSWORD = "password";
    static final String WORKER_ROLE = "role";

    static final String SELECT_ALL_WORKERS = "SELECT * FROM " + WORKERS_TABLE;

    @Bean
    DatabaseHelper db;

    public void addWorker(Worker worker) {
        ContentValues values = new ContentValues();
        values.put(WORKER_NAME, worker.getName());
        values.put(WORKER_PASSWORD, worker.getPassword());
        values.put(WORKER_ROLE, worker.getRole());
        db.getWritableDatabase().insert(WORKERS_TABLE, null, values);
    }

    public Worker[] getAllWorkers() {
        Cursor cursor = db.getReadableDatabase().rawQuery(SELECT_ALL_WORKERS, null);
        Worker[] workers = null;
        if (cursor != null && cursor.moveToFirst()) {
            workers = new Worker[cursor.getCount()];
            int i = 0;
            do {
                Worker worker = mapWorker(cursor);
                workers[i++] = worker;
            } while (cursor.moveToNext());
            cursor.close();
        }
        return workers;
    }

    private Worker mapWorker(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex(WORKER_ID));
        String name = cursor.getString(cursor.getColumnIndex(WORKER_NAME));
        String password = cursor.getString(cursor.getColumnIndex(WORKER_PASSWORD));
        String role = cursor.getString(cursor.getColumnIndex(WORKER_ROLE));

        Worker worker = new Worker();
        worker.setName(name);
        worker.setPassword(password);
        worker.setRole(role);
        return worker;
    }
}
