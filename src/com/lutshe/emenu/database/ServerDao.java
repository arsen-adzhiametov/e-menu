package com.lutshe.emenu.database;

import android.content.ContentValues;
import android.database.Cursor;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.lutshe.emenu.model.Server;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EBean
public class ServerDao {

    static final String SERVER_TABLE = "server";
    static final String SERVER_ID = "id";
    static final String SERVER_URL = "url";
    static final String SERVER_LOGIN = "login";
    static final String SERVER_PASSWORD = "password";

    static final String SELECT_SERVER = "SELECT * FROM " + SERVER_TABLE;

    @Bean
    DatabaseHelper db;

    public void addServer(Server server) {
        ContentValues values = new ContentValues();
        values.put(SERVER_URL, server.getUrl());
        values.put(SERVER_LOGIN, server.getLogin());
        values.put(SERVER_PASSWORD, server.getPassword());
        db.getWritableDatabase().insert(SERVER_TABLE, null, values);
    }

    public Server getServer() {
        Cursor cursor = db.getReadableDatabase().rawQuery(SELECT_SERVER, null);
        Server server = null;
        if (cursor != null && cursor.moveToFirst()) {
            server = mapServer(cursor);
            cursor.close();
        }
        return server;
    }

    private Server mapServer(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex(SERVER_ID));
        String url = cursor.getString(cursor.getColumnIndex(SERVER_URL));
        String login = cursor.getString(cursor.getColumnIndex(SERVER_LOGIN));
        String password = cursor.getString(cursor.getColumnIndex(SERVER_PASSWORD));

        Server server = new Server();
        server.setUrl(url);
        server.setLogin(login);
        server.setPassword(password);
        return server;
    }
}
