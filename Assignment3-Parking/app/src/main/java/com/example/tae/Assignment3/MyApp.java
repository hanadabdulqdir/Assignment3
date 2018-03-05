package com.example.tae.Assignment3;

import android.app.Application;
import android.content.Context;

/**
 * Created by kalpesh on 08/02/2018.
 */

public class MyApp extends Application {

    private static MyApp sInstance;
    private static Context context;
    public static Application sApplication;

    public static MyApp getInstance() {
        if (sInstance == null) {
            sInstance = new MyApp();
        }
        return sInstance;
    }
    public static Application getApplication() {
        return sApplication;
    }
    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        sApplication = this;
     //   configRealm();

    }


   // public void configRealm()
   // {
     //   Realm.init(this);
     //   RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
     //           .name("MusicViewer")
     //           .schemaVersion(1)
     ////           .deleteRealmIfMigrationNeeded()
      //          .build();
     //   Realm.setDefaultConfiguration(realmConfiguration);
   // }

    public Context getAppContext(){
        return context;
    }

}
