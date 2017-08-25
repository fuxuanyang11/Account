package com.example.account.task;


import com.example.account.data.CeramicsInfo;
import com.example.account.util.RealmUtils;

import java.util.List;

/**
 * Created by ASUS on 2017/8/23.
 */

public class TaskPresent implements TaskContract.Presenter {

    private TaskContract.View mTaskView;

    public TaskPresent(TaskContract.View taskView) {
        mTaskView = taskView;

        mTaskView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks();
    }

    @Override
    public void loadTasks() {
        loadTask(true);
    }

    @Override
    public void addNewTask() {

    }

    private void loadTask(boolean showLoadingUI) {
        if (showLoadingUI) {
            mTaskView.setLoadingIndicator(true);
        }

        List<CeramicsInfo> ceramicsInfos = (List<CeramicsInfo>) RealmUtils.queryRealmObjects(CeramicsInfo.class);
        processTasks(ceramicsInfos);
        if (showLoadingUI) {
            mTaskView.setLoadingIndicator(false);
        }
    }

    private void processTasks(List<CeramicsInfo> tasks) {
        if (tasks.isEmpty()) {
            mTaskView.showNoTasks();
        } else {
            mTaskView.showTasks(tasks);
        }
    }

}
