package com.example.account.addcustom;

import com.example.account.BasePresenter;
import com.example.account.BaseView;

public class CustomTaskContract {

    public interface View extends BaseView<Presenter> {
        void showEmptyTaskError();

        void showTasksList();

        void showTaskDelete();

        void setDate(String date);

        void setDeduct(String deduct);

        void setAmount(String amount);

        void setPrice(String price);

        void setTotal(String total);

        void setBalance(String balance);

        void setRemark(String remark);

    }

    interface Presenter extends BasePresenter {
        void saveTask(String date, String deduct, String amount,
                      String price, String total, String balance, String remark);

        void onTaskLoaded();

        void deleteTask();

    }
}
