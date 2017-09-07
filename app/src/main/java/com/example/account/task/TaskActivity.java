package com.example.account.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.account.BaseActivity;
import com.example.account.R;
import com.example.account.addcustom.AddCustomTaskActivity;
import com.example.account.addtask.AddEditTaskActivity;
import com.github.clans.fab.FloatingActionMenu;

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
    FloatingActionMenu mFabAddTask;
    @BindView(R.id.parent_layout)
    CoordinatorLayout mParentLayout;

    private TaskContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getTitleView().setText("瓷砖信息");
        mPresenter = new TaskPresent(this);

        mPresenter.start();
        mRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark)
        );
        mRefreshLayout.setOnRefreshListener(() -> {
          mPresenter.loadTasks();
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
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
    public void showTasks(List<Recipe> tasks) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mNoTasks.setVisibility(View.GONE);
        TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
        mRecyclerView.setAdapter(taskAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter.setOnItemClickListener((view, parentPosition, childPosition) -> {
            if (TextUtils.isEmpty(tasks.get(parentPosition).getChildList().get(childPosition).getDeduct())) {
                showTaskDetailsUi(tasks.get(parentPosition).getChildList().get(childPosition).getId());
            }else {
                showCustomTaskDetailsUi(tasks.get(parentPosition).getChildList().get(childPosition).getId());
            }
        });

    }

    @Override
    public void showNoTasks() {
        mRecyclerView.setVisibility(View.GONE);
        mNoTasks.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNormalAddTask() {
        Intent intent = new Intent(this, AddEditTaskActivity.class);
        startActivityForResult(intent, AddEditTaskActivity.REQUEST_ADD_TASK);
    }

    @Override
    public void showCustomAddTask() {
        Intent intent = new Intent(this, AddCustomTaskActivity.class);
        startActivityForResult(intent, AddCustomTaskActivity.REQUEST_CUSTOM_TASK);
    }

    @Override
    public void showSuccessfullySavedMessage() {
        Snackbar.make(mParentLayout, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTaskDetailsUi(String taskId) {
        Intent intent = new Intent(this, AddEditTaskActivity.class);
        intent.putExtra(AddEditTaskActivity.EXTRA_TASK_ID, taskId);
        startActivity(intent);
    }

    @Override
    public void showCustomTaskDetailsUi(String taskId) {
        Intent intent = new Intent(this, AddCustomTaskActivity.class);
        intent.putExtra(AddCustomTaskActivity.EXTRA_TASK_ID, taskId);
        startActivity(intent);
    }

    @OnClick({R.id.menu_custom, R.id.menu_normal, R.id.fab_add_task})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu_custom:
                mPresenter.addCustomTask();
                mFabAddTask.close(true);
                break;
            case R.id.menu_normal:
                mPresenter.addNormalTask();
                mFabAddTask.close(true);
                break;
            case R.id.fab_add_task:
                mFabAddTask.toggleMenu(true);
                break;
        }
    }
}
