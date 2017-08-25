package com.example.account.addtask;

import com.example.account.BasePresenter;
import com.example.account.BaseView;

public class AddTaskContract  {

    public interface View extends BaseView<Presenter> {
        void showEmptyTaskError();

        void showTasksList();

        void setDate(String date);

        void setNumber(String number);

        void setSpecification(String specification);

        void setAmount(int amount);

        void setPrice(float price);

        void setBalance(String balance);

        void setRemark(String remark);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void saveTask(String date, String number, String specification, int amount, float price, String balance,
                      String remark);

        void onTaskLoaded();

    }
}
