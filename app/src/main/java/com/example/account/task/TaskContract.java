package com.example.account.task;


import com.example.account.BasePresenter;
import com.example.account.BaseView;
import com.example.account.data.CeramicsInfo;

import java.util.List;

/**
 * Created by ASUS on 2017/8/23.
 */

public interface TaskContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showTasks(List<CeramicsInfo> tasks);

        void showNoTasks();

        void showNormalAddTask();

        void showCustomAddTask();

        void showSuccessfullySavedMessage();

        void showTaskDetailsUi(String taskId);
    }


    interface Presenter extends BasePresenter {
        void loadTasks();

        void addNormalTask();

        void addCustomTask();

        void result(int requestCode, int resultCode);
    }
}

