package com.lutshe.emenu.database;

import android.content.ContentValues;
import android.database.Cursor;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.lutshe.emenu.model.Table;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EBean
public class TableDao {

    static final String TABLE_TABLE = "tabl";
    static final String TABLE_ID = "id";
    static final String TABLE_WIDTH = "width";
    static final String TABLE_HEIGHT = "height";
    static final String TABLE_POSITION_X = "x";
    static final String TABLE_POSITION_Y = "y";
    static final String ID_HALL = "id_hall";
    private static final String SELECT_ALL_TABLES_OF_HALL = "SELECT * FROM "+TABLE_TABLE+" WHERE "+ID_HALL+" = ";

    @Bean
    DatabaseHelper db;

    public Table[] getTablesByHallId(Integer hallId) {
            Cursor cursor = db.getReadableDatabase().rawQuery(SELECT_ALL_TABLES_OF_HALL + hallId, null);
            Table[] tables = null;
            if (cursor != null && cursor.moveToFirst()) {
                tables = new Table[cursor.getCount()];
                int i = 0;
                do {
                    Table table = mapTable(cursor);
                    tables[i++] = table;
                } while (cursor.moveToNext());
                cursor.close();
            }
            return tables;
        }

    private Table mapTable(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex(TABLE_ID));
        Integer width = cursor.getInt(cursor.getColumnIndex(TABLE_WIDTH));
        Integer height = cursor.getInt(cursor.getColumnIndex(TABLE_HEIGHT));
        Integer x = cursor.getInt(cursor.getColumnIndex(TABLE_POSITION_X));
        Integer y = cursor.getInt(cursor.getColumnIndex(TABLE_POSITION_Y));

        Table table = new Table();
        table.setId(id);
        table.setWidth(width);
        table.setHeight(height);
        table.setPositionX(x);
        table.setPositionY(y);
        return table;

    }

    public void addTable(Table table, int hallId) {
        ContentValues values = new ContentValues();
        values.put(TABLE_ID, table.getId());
        values.put(TABLE_WIDTH, table.getWidth());
        values.put(TABLE_HEIGHT, table.getHeight());
        values.put(TABLE_POSITION_X, table.getPositionX());
        values.put(TABLE_POSITION_Y, table.getPositionY());
        values.put(ID_HALL, hallId);
        db.getWritableDatabase().insert(TABLE_TABLE, null, values);
    }
}
