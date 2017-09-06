package com.example.account.addcustom;

import com.example.account.data.CeramicsInfo;

import java.util.UUID;

import io.realm.Realm;

public class CustomTaskPresent implements CustomTaskContract.Presenter {

    private CustomTaskContract.View mAddTaskView;

    private Realm mRealm;

    private String mTaskId;

    public CustomTaskPresent(CustomTaskContract.View addTaskView, Realm realm, String taskId) {
        mAddTaskView = addTaskView;
        mRealm = realm;
        mTaskId = taskId;
        mAddTaskView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!isNewTask()) {
            onTaskLoaded();
        }
    }

    @Override
    public void saveTask(String date, String deduct, String amount,
                         String price, String total, String balance, String remark) {

        if (isNewTask()) {
            createTask(date, deduct, amount, price, total, balance, remark);
        } else {
            updateTask(date, deduct, amount, price, total, balance, remark);
        }


    }

    private void createTask(String date, String deduct, String amount,
                            String price, String total, String balance, String remark) {
        String id = String.valueOf(UUID.randomUUID().toString());
        CeramicsInfo ceramicsInfo = new CeramicsInfo(id, date, amount, price, total, balance, remark, deduct);

        if (ceramicsInfo.isCustomEmpty()) {
            mAddTaskView.showEmptyTaskError();
        }else {
            mRealm.executeTransaction(realm -> realm.copyToRealm(ceramicsInfo));
            mAddTaskView.showTasksList();
        }

    }

    private void updateTask(String date, String deduct, String amount,
                            String price, String total, String balance, String remark) {
        CeramicsInfo ceramicsInfo = mRealm.where(CeramicsInfo.class).equalTo("id", mTaskId).findFirst();
        mRealm.beginTransaction();
        ceramicsInfo.setDate(date);
        ceramicsInfo.setDeduct(deduct);
        ceramicsInfo.setAmount(amount);
        ceramicsInfo.setPrice(price);
        ceramicsInfo.setTotal(total);
        ceramicsInfo.setBalance(balance);
        ceramicsInfo.setRemark(remark);
        mRealm.commitTransaction();
        mAddTaskView.showTasksList();
    }

    @Override
    public void onTaskLoaded() {
        CeramicsInfo ceramicsInfo = mRealm.where(CeramicsInfo.class).equalTo("id", mTaskId).findFirst();
            mAddTaskView.setDate(ceramicsInfo.getDate());
            mAddTaskView.setDeduct(ceramicsInfo.getDeduct());
            mAddTaskView.setAmount(ceramicsInfo.getAmount());
            mAddTaskView.setPrice(ceramicsInfo.getPrice());
            mAddTaskView.setTotal(ceramicsInfo.getTotal());
            mAddTaskView.setBalance(ceramicsInfo.getBalance());
            mAddTaskView.setRemark(ceramicsInfo.getRemark());


    }


    private boolean isNewTask() {
        return mTaskId == null;
    }
}
