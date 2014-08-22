package com.lutshe.emenu.database;

import android.content.ContentValues;
import android.database.Cursor;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.lutshe.emenu.model.Hall;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EBean
public class HallDao  {

    static final String HALL_TABLE = "hall";
    static final String HALL_ID = "id";

    static final String SELECT_ALL_HALLS = "SELECT * FROM " + HALL_TABLE;

    @Bean
    DatabaseHelper db;

    public void addHall(Hall hall) {
        ContentValues values = new ContentValues();
        values.put(HALL_ID, hall.getId());
        db.getWritableDatabase().insert(HALL_TABLE, null, values);
    }

    public Hall[] getAllHalls() {
        Cursor cursor = db.getReadableDatabase().rawQuery(SELECT_ALL_HALLS, null);
        Hall[] halls = null;
        if (cursor != null && cursor.moveToFirst()) {
            halls = new Hall[cursor.getCount()];
            int i = 0;
            do {
                Hall hall = mapHall(cursor);
                halls[i++] = hall;
            } while (cursor.moveToNext());
            cursor.close();
        }
        return halls;
    }

    private Hall mapHall(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex(HALL_ID));
        Hall hall = new Hall();
        hall.setId(id);
        return hall;
    }
}
