package com.lutshe.emenu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.api.Scope;

import static com.lutshe.emenu.database.DishDao.*;
import static com.lutshe.emenu.database.HallDao.HALL_ID;
import static com.lutshe.emenu.database.HallDao.HALL_TABLE;
import static com.lutshe.emenu.database.ServerDao.*;
import static com.lutshe.emenu.database.TableDao.*;
import static com.lutshe.emenu.database.WorkerDao.*;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EBean(scope = Scope.Singleton)
public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "emenuDB";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SERVER_TABLE + " (" +
                SERVER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SERVER_URL + " TEXT NOT NULL, " +
                SERVER_LOGIN + " TEXT NOT NULL, " +
                SERVER_PASSWORD + " TEXT NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE " + WORKERS_TABLE + " (" +
                WORKER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WORKER_NAME + " TEXT NOT NULL, " +
                WORKER_PASSWORD + " TEXT NOT NULL, " +
                WORKER_ROLE + " TEXT NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE " + HALL_TABLE + " (" +
                HALL_ID + " INTEGER PRIMARY KEY" +
                ");");

        db.execSQL("CREATE TABLE " + TABLE_TABLE + " (" +
                TABLE_ID + " INTEGER PRIMARY KEY, " +
                TABLE_HEIGHT + " INTEGER NOT NULL, " +
                TABLE_WIDTH + " INTEGER NOT NULL, " +
                TABLE_POSITION_X + " INTEGER NOT NULL, " +
                TABLE_POSITION_Y + " INTEGER NOT NULL, " +
                ID_HALL + " INTEGER NOT NULL, " +

                "FOREIGN KEY (" + ID_HALL + ") REFERENCES " + HALL_TABLE + " (" + HALL_ID + ")" +
                ");");

        db.execSQL("CREATE TABLE " + DISH_TABLE + " (" +
                DISH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DISH_NAME + " TEXT NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE " + CLAZZ_TABLE + " (" +
                CLAZZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLAZZ_NAME + " TEXT NOT NULL, " +
                ID_DISH + " INTEGER NOT NULL, " +

                "FOREIGN KEY (" + ID_DISH + ") REFERENCES " + DISH_TABLE + " (" + DISH_ID + ")" +
                ");");

        db.execSQL("CREATE TABLE " + MOD_TABLE + " (" +
                MOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MOD_NAME + " TEXT NOT NULL, " +
                MOD_PRICE + " INTEGER NOT NULL, " +
                ID_DISH + " INTEGER NOT NULL, " +

                "FOREIGN KEY (" + ID_DISH + ") REFERENCES " + DISH_TABLE + " (" + DISH_ID + ")" +
                ");");

        db.execSQL("CREATE TABLE " + UNIT_TABLE + " (" +
                UNIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UNIT_NAME + " TEXT NOT NULL, " +
                UNIT_PRICE + " INTEGER NOT NULL, " +
                ID_DISH + " INTEGER NOT NULL, " +

                "FOREIGN KEY (" + ID_DISH + ") REFERENCES " + DISH_TABLE + " (" + DISH_ID + ")" +
                ");");

        Log.d("emenu", "created DB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SERVER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + WORKERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DISH_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CLAZZ_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MOD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + UNIT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + HALL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TABLE);
        onCreate(db);
    }
}
