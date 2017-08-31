package com.example.account.addtask;

import com.example.account.data.CeramicsInfos;

import io.realm.Realm;

public class AddTaskPresent implements AddTaskContract.Presenter {

    private AddTaskContract.View mAddTaskView;

    private Realm mRealm;

    private String mTaskId;

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
        CeramicsInfos ceramicsInfos = new CeramicsInfos(number, date, specification, amount, price,
                total, balance, remark);
        if (ceramicsInfos.isEmpty()) {
            mAddTaskView.showEmptyTaskError();
        }else {
            mRealm.executeTransaction(realm -> realm.copyToRealm(ceramicsInfos));
        }
//        mRealm.executeTransaction(realm -> {
//            CeramicsInfos ceramicsInfos = realm.createObject(CeramicsInfos.class);
//            ceramicsInfos.setDate(date);
//            ceramicsInfos.setNumber(number);
//            ceramicsInfos.setSpecification(specification);
//            ceramicsInfos.setAmount(amount);
//            ceramicsInfos.setPrice(price);
//            ceramicsInfos.setTotal(total);
//            ceramicsInfos.setBalance(balance);
//            ceramicsInfos.setRemark(remark);
//            if (ceramicsInfos.isEmpty()) {
//
//            } else {
//                mAddTaskView.showTasksList();
//            }
//        });
    }

    private void updateTask(String date, String number, String specification, String amount,
                            String price, String total, String balance, String remark) {
        CeramicsInfos ceramicsInfos = mRealm.where(CeramicsInfos.class).equalTo("id", mTaskId).findFirst();
        mRealm.beginTransaction();
        ceramicsInfos.setDate(date);
        ceramicsInfos.setNumber(number);
        ceramicsInfos.setSpecification(specification);
        ceramicsInfos.setAmount(amount);
        ceramicsInfos.setPrice(price);
        ceramicsInfos.setTotal(total);
        ceramicsInfos.setBalance(balance);
        ceramicsInfos.setRemark(remark);
        mRealm.commitTransaction();
        mAddTaskView.showTasksList();
    }

    @Override
    public void onTaskLoaded() {
        CeramicsInfos ceramicsInfos = mRealm.where(CeramicsInfos.class).equalTo("id", mTaskId).findFirst();
        if (mAddTaskView.isActive()) {
            mAddTaskView.setDate(ceramicsInfos.getDate());
            mAddTaskView.setNumber(ceramicsInfos.getNumber());
            mAddTaskView.setSpecification(ceramicsInfos.getSpecification());
            mAddTaskView.setAmount(ceramicsInfos.getAmount());
            mAddTaskView.setPrice(ceramicsInfos.getPrice());
            mAddTaskView.setTotal(ceramicsInfos.getTotal());
            mAddTaskView.setBalance(ceramicsInfos.getBalance());
            mAddTaskView.setRemark(ceramicsInfos.getRemark());
        }

    }


    private boolean isNewTask() {
        return mTaskId == null;
    }
}
