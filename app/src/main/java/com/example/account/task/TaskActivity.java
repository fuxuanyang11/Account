package com.example.account.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.account.BaseActivity;
import com.example.account.R;
import com.example.account.data.CeramicsInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class TaskActivity extends BaseActivity implements TaskContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.noTasksIcon)
    ImageView mNoTasksIcon;
    @BindView(R.id.noTasksAdd)
    TextView mNoTasksAdd;
    @BindView(R.id.noTasks)
    LinearLayout mNoTasks;

    private TaskContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getTitleView().setText("111");
        mPresenter = new TaskPresent(this);
//        insertData();
        mPresenter.start();
    }

    @Override
    protected boolean isHideTitleView() {
        return false;
    }

    @Override
    protected boolean isHideNavigationIcon() {
        return true;
    }


    private void insertData() {
        Realm realm=Realm.getDefaultInstance();
        for (int i = 0; i < 10; i++) {

            final int finalI = i;
            realm.executeTransaction(realm1 -> {
                CeramicsInfo object = realm1.createObject(CeramicsInfo.class);
                object.setNumber("5555" + finalI);
                object.setDate("" + finalI);
            });
        }

    }


    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showTasks(List<CeramicsInfo> tasks) {
        mRecyclerView.setAdapter(new TaskAdapter(tasks));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showNoTasks() {

    }



    private static class TaskAdapter extends BaseQuickAdapter<CeramicsInfo, BaseViewHolder> {


        public TaskAdapter( @Nullable List<CeramicsInfo> data) {
            super(R.layout.task_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CeramicsInfo item) {
            helper.setText(R.id.title, item.getDate())
                    .setText(R.id.content, item.getNumber());
        }
    }
}
