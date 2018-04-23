package com.example.jjsampayo.daosample1.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.jjsampayo.daosample1.repositories.local.AppDatabase;
import com.example.jjsampayo.daosample1.repositories.local.entities.Friend;

public class AddViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;

    public AddViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(application);
    }

    public void addFriend(Friend friend) {
        new AsyncAddFriend(appDatabase).execute(friend);
    }

    private static class AsyncAddFriend extends AsyncTask<Friend, Void, Void> {

        private AppDatabase appDatabase;

        AsyncAddFriend(AppDatabase appDatabase) {
            this.appDatabase = appDatabase;
        }

        @Override
        protected Void doInBackground(Friend... friends) {
            appDatabase.friendDao().insert(friends[0]);
            return null;
        }
    }
}
