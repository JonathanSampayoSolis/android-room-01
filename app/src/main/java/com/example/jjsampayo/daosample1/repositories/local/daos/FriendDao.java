package com.example.jjsampayo.daosample1.repositories.local.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.jjsampayo.daosample1.repositories.local.entities.Friend;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface FriendDao {

    @Query("SELECT * FROM friends")
    List<Friend> getAll();

    @Query("SELECT * FROM friends WHERE name LIKE :name LIMIT 1")
    Friend findByName(String name);

    @Query("SELECT COUNT(*) FROM friends")
    int countFriends();

    @Insert(onConflict = REPLACE)
    void insert(Friend friend);

    @Insert
    void insertAll(Friend... friends);

    @Update
    void update(Friend friend);

    @Delete
    void delete(Friend friend);

}
