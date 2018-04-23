package com.example.jjsampayo.daosample1.repositories.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jjsampayo.daosample1.repositories.local.daos.FriendDao;
import com.example.jjsampayo.daosample1.repositories.local.entities.Friend;

@Database(entities = {Friend.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "app_friends_db";

    private static AppDatabase appDatabase;

    public static AppDatabase getDatabase(Context context) {
        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();

        return appDatabase;
    }

    public static void destroyInstance() {
        appDatabase = null;
    }

    public abstract FriendDao friendDao();

}
