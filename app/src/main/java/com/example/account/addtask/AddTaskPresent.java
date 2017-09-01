package com.example.account.addtask;

import com.example.account.MyApplication;
import com.example.account.data.CeramicsInfo;
import com.orhanobut.logger.Logger;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;

public class AddTaskPresent implements AddTaskContract.Presenter {

    private AddTaskContract.View mAddTaskView;

    private Realm mRealm;

    private String mTaskId;

    private static AtomicInteger mId = new AtomicInteger();

    public AddTaskPresent(AddTaskContract.View addTaskView, Realm realm, String taskId) {
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
    public void saveTask(String date, String number, String specification, String amount,
                         String price, String total, String balance, String remark) {

        if (isNewTask()) {
            createTask(date, number, specification, amount, price, total, balance, remark);
        } else {
            updateTask(date, number, specification, amount, price, total, balance, remark);
        }


    }

    private void createTask(String date, String number, String specification, String amount,
                            String price, String total, String balance, String remark) {
        String id = String.valueOf(mId.getAndIncrement());
        CeramicsInfo ceramicsInfo = new CeramicsInfo(id, number, date,
                specification, amount, price, total, balance, remark);

        if (ceramicsInfo.isEmpty()) {
            mAddTaskView.showEmptyTaskError();
        }else {
            mRealm.executeTransaction(realm -> realm.copyToRealm(ceramicsInfo));
            mAddTaskView.showTasksList();
        }

    }

    private void updateTask(String date, String number, String specification, String amount,
                            String price, String total, String balance, String remark) {
        CeramicsInfo ceramicsInfo = mRealm.where(CeramicsInfo.class).equalTo("id", mTaskId).findFirst();
        mRealm.beginTransaction();
        ceramicsInfo.setDate(date);
        ceramicsInfo.setNumber(number);
        ceramicsInfo.setSpecification(specification);
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
        Logger.d(ceramicsInfo.getDate());
            mAddTaskView.setDate(ceramicsInfo.getDate());
            mAddTaskView.setNumber(ceramicsInfo.getNumber());
            mAddTaskView.setSpecification(ceramicsInfo.getSpecification());
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
