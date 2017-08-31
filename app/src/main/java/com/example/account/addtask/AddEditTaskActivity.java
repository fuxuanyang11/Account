package com.example.account.addtask;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.account.BaseActivity;
import com.example.account.R;
import com.example.account.util.DialogUtil;
import com.example.account.util.ITextWatcher;
import com.orhanobut.logger.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class AddEditTaskActivity extends BaseActivity implements AddTaskContract.View {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

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
    @BindView(R.id.parent_layout)
    RelativeLayout mRootLayout;
    private AddTaskContract.Presenter mPresenter;

    private Realm mRealm;
    private Integer mAmount = 0;
    private float mPrice = 0.0f;
    //选中的日期
    private String mSelectedDate;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        ButterKnife.bind(this);
        mRealm = Realm.getDefaultInstance();
        mPresenter = new AddTaskPresent(this, mRealm, null);
        mDateContent.setText(mSelectedDate);
        textWatch();

    }

    private void textWatch() {
        mAmountContent.addTextChangedListener(new ITextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    mAmount = Integer.valueOf(s.toString());
                } else {
                    mAmount = 0;
                }
                mTotalContent.setText(String.valueOf(mPrice * mAmount));
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
                mTotalContent.setText(String.valueOf(mPrice * mAmount));
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
    public void setDate(String date) {

    }

    @Override
    public void setNumber(String number) {

    }

    @Override
    public void setSpecification(String specification) {

    }

    @Override
    public void setAmount(String amount) {

    }

    @Override
    public void setPrice(String price) {

    }

    @Override
    public void setTotal(String total) {

    }

    @Override
    public void setBalance(String balance) {

    }

    @Override
    public void setRemark(String remark) {

    }

    @Override
    public boolean isActive() {
        return true;
    }


    @OnClick({R.id.date, R.id.fab_add_task})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.date:
                setDate();
                break;
            case R.id.fab_add_task:
                Logger.d(mDateContent.getText().toString() + mNumberContent.getText().toString()+
                        mSpecificationContent.getText().toString()+ mAmountContent.getText().toString()+
                        mPriceContent.getText().toString()+ mTotalContent.getText().toString()+
                        mBalanceContent.getText().toString()+ mRemarkContent.getText().toString());
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
                    mSelectedDate = FORMATTER.format(date.getDate());
                    if (mSelectedDate == null) {
                        mSelectedDate = FORMATTER.format(Calendar.getInstance().getTime());
                    }
                });
    }
}
