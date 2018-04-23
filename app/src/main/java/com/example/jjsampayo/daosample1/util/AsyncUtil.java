package com.example.jjsampayo.daosample1.util;

import com.example.jjsampayo.daosample1.repositories.local.entities.Friend;

import java.util.List;

public interface AsyncUtil {

    interface AsyncFinish {
        void onLoadDataFinished(List<Friend> list);
    }

}
