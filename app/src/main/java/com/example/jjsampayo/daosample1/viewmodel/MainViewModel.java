package com.example.jjsampayo.daosample1.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.jjsampayo.daosample1.repositories.local.AppDatabase;
import com.example.jjsampayo.daosample1.repositories.local.entities.Friend;
import com.example.jjsampayo.daosample1.util.AsyncUtil;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Friend>> friendList;

    private AppDatabase appDatabase;

    public MainViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(getApplication());
    }

    public LiveData<List<Friend>> getFriendList() {
        if (friendList == null) {
            friendList = new MutableLiveData<>();
            new AsyncLoadData(appDatabase, new AsyncUtil.AsyncFinish() {
                @Override
                public void onLoadDataFinished(List<Friend> list) {
                    friendList.setValue(list);
                }
            }).execute();
        }
        return friendList;
    }

    public void deleteFriend(Friend friend) {
        new AsyncDeleteFriend(appDatabase).execute(friend);

        friendList.getValue().remove(friend);
        friendList.setValue(friendList.getValue());
    }

    private static class AsyncLoadData extends AsyncTask<Void, Void, List<Friend>> {

        private AppDatabase appDatabase;
        private AsyncUtil.AsyncFinish callback;

        AsyncLoadData(AppDatabase appDatabase, AsyncUtil.AsyncFinish callback) {
            this.appDatabase = appDatabase;
            this.callback = callback;
        }

        @Override
        protected List<Friend> doInBackground(Void... voids) {
            try { Thread.sleep(2000); }
            catch (InterruptedException e) { e.printStackTrace(); }

            return appDatabase.friendDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Friend> listLiveData) {
            super.onPostExecute(listLiveData);
            callback.onLoadDataFinished(listLiveData);
        }
    }

    private static class AsyncDeleteFriend extends AsyncTask<Friend, Void, Void> {

        private AppDatabase appDatabase;

        AsyncDeleteFriend(AppDatabase appDatabase) {
            this.appDatabase = appDatabase;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Friend... friends) {
            appDatabase.friendDao().delete(friends[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
