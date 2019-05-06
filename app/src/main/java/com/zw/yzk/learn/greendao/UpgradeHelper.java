package com.zw.yzk.learn.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zw.yzk.learn.greendao.entity.DaoMaster;


/**
 * Created by wei on 2016/3/1.
 */
public class UpgradeHelper extends DaoMaster.DevOpenHelper {

    UpgradeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * Here is where the calls to upgrade are executed
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion);
        try {
            switch (oldVersion) {
                case 1:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Log.e("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " error occur");
            super.onUpgrade(db, oldVersion, newVersion);
        }
    }

}