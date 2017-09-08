package com.example.account.addtask;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.account.BaseActivity;
import com.example.account.R;
import com.example.account.util.DialogUtil;
import com.example.account.util.ITextWatcher;
import com.example.account.util.StringUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class AddEditTaskActivity extends BaseActivity implements AddTaskContract.View {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    public static final int REQUEST_ADD_TASK = 1;
    public static final String EXTRA_TASK_ID = "EXTRA_TASK_ID";
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
    @BindView(R.id.parent_layout)
    CoordinatorLayout mRootLayout;
    private AddTaskContract.Presenter mPresenter;

    private Realm mRealm;
    private Integer mAmount = 0;
    private float mPrice = 0.0f;
    //选中的日期
    private String mSelectedDate;
    private Dialog mDialog;

    private String mTaskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        ButterKnife.bind(this);
        getTitleView().setText("添加瓷砖信息");
        initData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mTaskId !=null) {
            getMenuInflater().inflate(R.menu.text_menu, menu);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                mPresenter.deleteTask();
                return true;
        }
        return false;
    }

    private void initData() {
        mTaskId = getIntent().getStringExtra(EXTRA_TASK_ID);
        mRealm = Realm.getDefaultInstance();
        mPresenter = new AddTaskPresent(this, mRealm, mTaskId);
        mPresenter.start();
        textWatch();

    }

    private void textWatch() {
        if (mTaskId != null) {
            mPrice = Float.valueOf(mPriceContent.getText().toString());
            mAmount = Integer.valueOf(mAmountContent.getText().toString());
        }
        mAmountContent.addTextChangedListener(new ITextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    mAmount = Integer.valueOf(s.toString());
                } else {
                    mAmount = 0;
                }
                mTotalContent.setText(String.valueOf( (float)(Math.round(mPrice * mAmount *100))/100));
            }
        });
        mPriceContent.addTextChangedListener(new ITextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()) && !(s.toString().startsWith("."))) {
                    mPrice = Float.valueOf(s.toString());
                } else {
                    mPrice = 0;
                }
                mTotalContent.setText(String.valueOf( (float)(Math.round(mPrice * mAmount *100))/100));
            }
        });
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
        Snackbar.make(mRootLayout, getString(R.string.empty_task_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTasksList() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void showTaskDelete() {
        finish();
    }

    @Override
    public void setDate(String date) {
        mDateContent.setText(date);
    }

    @Override
    public void setNumber(String number) {
        mNumberContent.setText(number);
    }

    @Override
    public void setSpecification(String specification) {
        mSpecificationContent.setText(specification);
    }

    @Override
    public void setAmount(String amount) {
        mAmountContent.setText(amount);
    }

    @Override
    public void setPrice(String price) {
        mPriceContent.setText(price);
    }

    @Override
    public void setTotal(String total) {
        mTotalContent.setText(total);
    }

    @Override
    public void setBalance(String balance) {
        mBalanceContent.setText(balance);
    }

    @Override
    public void setRemark(String remark) {
        mRemarkContent.setText(remark);
    }



    @OnClick({R.id.date, R.id.fab_add_task})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.date:
                setDate();
                break;
            case R.id.fab_add_task:
                mPresenter.saveTask(mDateContent.getText().toString(), mNumberContent.getText().toString(),
                        mSpecificationContent.getText().toString(), mAmountContent.getText().toString(),
                        mPriceContent.getText().toString(), mTotalContent.getText().toString(),
                        mBalanceContent.getText().toString(), mRemarkContent.getText().toString());
                break;
        }
    }


    private void setDate() {
        mDialog = DialogUtil.showDateDialog(this, v -> {
                    switch (v.getId()) {
                        case R.id.btn_confirm:
                            mDateContent.setText(mSelectedDate);
                            mDialog.dismiss();
                            break;
                        case R.id.btn_cancel:
                            mDialog.dismiss();
                            break;
                    }
                },
                (widget, date, selected) -> {
                    mSelectedDate = StringUtil.formatDateToString(date.getDate());
                    mDateContent.setText(mSelectedDate);
                });
    }
}
