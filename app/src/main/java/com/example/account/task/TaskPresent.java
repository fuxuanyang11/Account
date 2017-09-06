package com.example.account.task;


import android.app.Activity;

import com.example.account.addtask.AddEditTaskActivity;
import com.example.account.data.CeramicsInfo;
import com.example.account.util.RealmUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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
                if (recipeDate.get(i).equals(ceramicsInfos.get(j).getDate().substring(0, 7))){
                    newCeramicsInfo.add(ceramicsInfos.get(j));
                }
            }
            Recipe recipe = new Recipe(recipeDate.get(i), newCeramicsInfo);
            recipes.add(recipe);
        }
        sortParentDate(recipes);
        return recipes;
    }

    private List<String> getRecipeDate(List<CeramicsInfo> ceramicsInfos) {
        List<String> recipeDate = new ArrayList<>();

        for (CeramicsInfo info : ceramicsInfos) {
            String date = info.getDate();
            if (!recipeDate.contains(date.substring(0, 7))) {
                recipeDate.add(date.substring(0, 7));
            }
        }

        return recipeDate;
    }

    private void sortParentDate(List<Recipe> list) {
        Collections.sort(list, (recipe, recipe2) -> {
            Date date1 = stringToDate(recipe.getName());
            Date date2 = stringToDate(recipe2.getName());
            if (date2.before(date1)) {
                return -1;
            }
            return 1;
        });
    }

    private  Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }
}
