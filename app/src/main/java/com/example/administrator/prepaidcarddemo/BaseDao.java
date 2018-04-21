package com.example.administrator.prepaidcarddemo;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by asus on 2016/9/17.
 */
public class BaseDao {

    private Realm realm;


    public BaseDao(Realm realm) {
        this.realm = realm;
    }

    /**
     * 批量添加
     */

    public boolean insert(RealmObject list) {
        try {
            realm.beginTransaction();
            realm.insert(list);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
            return false;
        }
    }

    public boolean insertOrUpdate(RealmObject object) {
        try {
            realm.beginTransaction();
            realm.insertOrUpdate(object);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
            return false;
        }
    }
}
