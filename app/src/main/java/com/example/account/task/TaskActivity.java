package com.example.account.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.account.BaseActivity;
import com.example.account.R;
import com.example.account.addtask.AddEditTaskActivity;
import com.example.account.data.CeramicsInfos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.fab_add_task)
    FloatingActionButton mFabAddTask;

    private TaskContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getTitleView().setText("111");
        mPresenter = new TaskPresent(this);

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



    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void setLoadingIndicator(boolean active) {

        mRefreshLayout.post(
                () -> mRefreshLayout.setRefreshing(active));
    }


    @Override
    public void showTasks(List<CeramicsInfos> tasks) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mNoTasks.setVisibility(View.GONE);
        mRecyclerView.setAdapter(new TaskAdapter(tasks));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showNoTasks() {
        mRecyclerView.setVisibility(View.GONE);
        mNoTasks.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddTask() {
        Intent intent = new Intent(this, AddEditTaskActivity.class);
        startActivityForResult(intent, AddEditTaskActivity.REQUEST_ADD_TASK);
    }

    @OnClick(R.id.fab_add_task)
    public void onViewClicked() {
        mPresenter.addNewTask();
    }


    private static class TaskAdapter extends BaseQuickAdapter<CeramicsInfos, BaseViewHolder> {


        public TaskAdapter(@Nullable List<CeramicsInfos> data) {
            super(R.layout.task_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CeramicsInfos item) {
            helper.setText(R.id.title, item.getDate())
                    .setText(R.id.content, item.getNumber());
        }
    }
}
