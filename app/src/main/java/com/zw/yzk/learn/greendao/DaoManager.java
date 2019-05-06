package com.zw.yzk.learn.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zw.yzk.learn.greendao.entity.DaoMaster;
import com.zw.yzk.learn.greendao.entity.DaoSession;
import com.zw.yzk.learn.greendao.entity.UserDao;


public class DaoManager {
    private static DaoManager instance;
    private SQLiteDatabase db;
    private DaoSession daoSession;

    private DaoManager() {

    }

    public static DaoManager getInstance() {
        if (instance == null) {
            synchronized (DaoManager.class) {
                if (instance == null) {
                    instance = new DaoManager();
                }
            }
        }
        return instance;
    }

    //初始化数据库
    public void init(Context context) {
        UpgradeHelper helper = new UpgradeHelper(context, "component", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public UserDao getUserDao() {
        return daoSession.getUserDao();
    }
}
