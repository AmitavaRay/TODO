package com.stcet.navdrawer;

import android.app.Application;

import io.realm.Realm;

public class RealmInitialization extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
