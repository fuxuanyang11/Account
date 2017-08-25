package com.example.account.task;


import com.example.account.BasePresenter;
import com.example.account.BaseView;
import com.example.account.data.CeramicsInfos;

import java.util.List;

/**
 * Created by ASUS on 2017/8/23.
 */

public interface TaskContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showTasks(List<CeramicsInfos> tasks);

        void showNoTasks();

        void showAddTask();
    }


    interface Presenter extends BasePresenter {
        void loadTasks();

        void addNewTask();
    }
}

