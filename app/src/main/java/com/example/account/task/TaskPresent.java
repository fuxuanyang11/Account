package com.example.account.task;


import android.app.Activity;

import com.example.account.addtask.AddEditTaskActivity;
import com.example.account.data.CeramicsInfo;
import com.example.account.util.RealmUtils;
import com.example.account.util.StringUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    public void addNormalTask() {
        mTaskView.showNormalAddTask();
    }

    @Override
    public void addCustomTask() {
        mTaskView.showCustomAddTask();
    }

    @Override
    public void result(int requestCode, int resultCode) {
        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
            mTaskView.showSuccessfullySavedMessage();
        }
    }

    private void loadTask(boolean showLoadingUI) {
        if (showLoadingUI) {
            mTaskView.setLoadingIndicator(true);
        }

        List<CeramicsInfo> ceramicsInfos = (List<CeramicsInfo>) RealmUtils.queryRealmObjects(CeramicsInfo.class);
        sortParentDate(ceramicsInfos);
        processTasks(ceramicsInfos);
        if (showLoadingUI) {
            mTaskView.setLoadingIndicator(false);
        }
    }

    private void processTasks(List<CeramicsInfo> tasks) {
        if (tasks.isEmpty()) {
            mTaskView.showNoTasks();
        } else {
            mTaskView.showTasks(getRecipeList(tasks));
        }
    }

    private List<Recipe> getRecipeList(List<CeramicsInfo> ceramicsInfos) {
        List<Recipe> recipes = new ArrayList<>();
        List<String> recipeDate = getRecipeDate(ceramicsInfos);
        for (int i = 0; i < recipeDate.size(); i++) {
            List<CeramicsInfo> newCeramicsInfo = new ArrayList<>();
            for (int j = 0; j < ceramicsInfos.size(); j++) {
                Logger.d(recipeDate.get(i));
                Logger.d(ceramicsInfos.get(j).getDate().substring(0, 8));
                if (recipeDate.get(i).equals(ceramicsInfos.get(j).getDate().substring(0, 8))){
                    newCeramicsInfo.add(ceramicsInfos.get(j));
                }
            }
            Recipe recipe = new Recipe(recipeDate.get(i), newCeramicsInfo);
            recipes.add(recipe);
        }

        return recipes;
    }

    private List<String> getRecipeDate(List<CeramicsInfo> ceramicsInfos) {
        List<String> recipeDate = new ArrayList<>();

        for (CeramicsInfo info : ceramicsInfos) {
            String date = StringUtil.formatShortDate(info.getDate());
            if (!recipeDate.contains(date)) {
                recipeDate.add(date);
            }
        }

        return recipeDate;
    }

    private void sortParentDate(List<CeramicsInfo> list) {
        Collections.sort(list, (o1, o2) -> {
            Date date1 = StringUtil.stringToDate(o1.getDate());
            Date date2 = StringUtil.stringToDate(o2.getDate());
            if (date2.before(date1)) {
                return -1;
            }
            return 1;
        });
    }


}
