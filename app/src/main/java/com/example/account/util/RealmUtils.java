package com.example.account.util;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmUtils {


    public static void insertRealmObject(RealmObject info) {
        Realm realm=Realm.getDefaultInstance();
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(info);
//        realm.commitTransaction();
//        realm.close();


    }

    /**
     * 批量插入数据
     * @param infos
     */
    public static void insertRealmObjects(List<? extends RealmObject> infos){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        //要使用update这种方法，那插入的对象必须是有主键的
        realm.copyToRealmOrUpdate(infos);
        realm.commitTransaction();
        realm.close();
    }
    /**
     * 查询指定类的全部存储信息
     * @return
     * @param clazz
     */
    public static List<? extends RealmObject> queryRealmObjects(Class<? extends RealmObject> clazz){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<? extends RealmObject> realmResults = realm.where(clazz).findAll();
        List<? extends RealmObject> arrayList = realm.copyFromRealm(realmResults);
        realm.close();
        return arrayList;
    }
    /**
     * 删除指定类的全部数据库信息
     */
    public static void deleteRealmObjects(Class<? extends RealmObject> clazz){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(clazz);
        realm.commitTransaction();
        realm.close();
    }
    /**
     * 删除此realm对应的全部数据库信息
     */
    public static void deleteAllRealmObjects(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.close();
    }

}
