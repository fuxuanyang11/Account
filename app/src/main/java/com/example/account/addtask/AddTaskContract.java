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

        void setAmount(String amount);

        void setPrice(String price);

        void setTotal(String total);

        void setBalance(String balance);

        void setRemark(String remark);

    }

    interface Presenter extends BasePresenter {
        void saveTask(String date, String number, String specification, String amount, String price,
                      String total, String balance, String remark);

        void onTaskLoaded();

    }
}
