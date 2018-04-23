package com.example.jjsampayo.daosample1.repositories.local.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity(tableName = "friends")
public class Friend {

    @NonNull
    @PrimaryKey
    private String id;

    private String name;
    private String tel;

    public Friend(String name, String tel) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
