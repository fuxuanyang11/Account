package com.example.account.addtask;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.account.BaseActivity;
import com.example.account.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEditTaskActivity extends BaseActivity implements AddTaskContract.View {

    public static final int REQUEST_ADD_TASK = 1;
    @BindView(R.id.fab_add_task)
    FloatingActionButton mFabAddTask;
    @BindView(R.id.date_content)
    TextView mDateContent;
    @BindView(R.id.number_content)
    EditText mNumberContent;
    @BindView(R.id.specification_content)
    EditText mSpecificationContent;
    @BindView(R.id.amount_content)
    EditText mAmountContent;
    @BindView(R.id.price_content)
    EditText mPriceContent;
    @BindView(R.id.total_content)
    TextView mTotalContent;
    @BindView(R.id.balance_content)
    EditText mBalanceContent;
    @BindView(R.id.remark_content)
    EditText mRemarkContent;

    private AddTaskContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        ButterKnife.bind(this);

        mPresenter.onTaskLoaded();
    }

    @Override
    protected boolean isHideTitleView() {
        return false;
    }

    @Override
    protected boolean isHideNavigationIcon() {
        return false;
    }

    @Override
    public void setPresenter(AddTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showEmptyTaskError() {
        // Snackbar.make(mTitle, getString(R.string.empty_task_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTasksList() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void setDate(String date) {

    }

    @Override
    public void setNumber(String number) {

    }

    @Override
    public void setSpecification(String specification) {

    }

    @Override
    public void setAmount(int amount) {

    }

    @Override
    public void setPrice(float price) {

    }

    @Override
    public void setBalance(String balance) {

    }

    @Override
    public void setRemark(String remark) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @OnClick(R.id.fab_add_task)
    public void onViewClicked() {
        //        mPresenter.saveTask();
    }
}
