package com.example.account.task;


import com.example.account.data.CeramicsInfos;
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
        mTaskView.showAddTask();
    }

    private void loadTask(boolean showLoadingUI) {
        if (showLoadingUI) {
            mTaskView.setLoadingIndicator(true);
        }

        List<CeramicsInfos> ceramicsInfos = (List<CeramicsInfos>) RealmUtils.queryRealmObjects(CeramicsInfos.class);
        processTasks(ceramicsInfos);
        if (showLoadingUI) {
            mTaskView.setLoadingIndicator(false);
        }
    }

    private void processTasks(List<CeramicsInfos> tasks) {
        if (tasks.isEmpty()) {
            mTaskView.showNoTasks();
        } else {
            mTaskView.showTasks(tasks);
        }
    }

}
