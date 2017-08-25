package com.example.account;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;



public abstract class BaseActivity extends AppCompatActivity {



    private Toolbar mToolbar;

    private LinearLayout mRootLayout;

    TextView mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base);
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            if (isHideTitleView()) {
                mToolbar.setVisibility(View.GONE);
            } else {
                mToolbar.setVisibility(View.VISIBLE);
                initToolbar();
            }
        }
    }


    protected abstract boolean isHideTitleView();

    protected abstract boolean isHideNavigationIcon();


    protected TextView getTitleView() {
        return mTitle;
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    private void initToolbar() {
        if (mToolbar != null) {
            mTitle = (TextView) findViewById(R.id.title);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            if (!isHideNavigationIcon()) {
                mToolbar.setNavigationIcon(R.drawable.icon_white_arrow_left);
                mToolbar.setNavigationOnClickListener(v -> finish());
            }
        }
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        mRootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (mRootLayout == null)
            return;
        mRootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    protected void startAction(Class cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

}
