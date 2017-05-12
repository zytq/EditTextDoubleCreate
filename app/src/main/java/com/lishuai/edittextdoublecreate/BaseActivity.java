package com.lishuai.edittextdoublecreate;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lishuai.edittextdoublecreate.view.CustomProgressDialog;
import com.lishuai.edittextdoublecreate.view.StatusBarCompat;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;



public abstract class BaseActivity extends Activity implements View.OnClickListener {
    // 维护的集合只能有一份
    private static List<BaseActivity> activities = new LinkedList<BaseActivity>();
    public static BaseActivity activity;
    private MyReceiver myReceiver;
    private CustomProgressDialog progressDialog;

    /**
     * 当前Activity的实例
     */
    protected Activity mActivity;
    /**
     * 当前上下文实例
     */
    protected Context mContext;

    /**
     * 返回back
     */
    @Nullable
    @BindView(R.id.top_back)
    protected TextView mTtooTopBack;

    /**
     * 返回back
     */
    @Nullable
    @BindView(R.id.tv_title_back)
    protected TextView mTitleBack;

    /**
     *
     */
    @Nullable
    @BindView(R.id.title_text)
    protected TextView mTitle;


    /**
     * 右边image按钮
     */
    @Nullable
    @BindView(R.id.ib_right)
    protected ImageButton mRightImage;

    /**
     * 右边image 并列左边按钮
     */
    @Nullable
    @BindView(R.id.ib_right_left)
    protected ImageButton mRightImageLeft;

    /**
     * 右边文字
     */
    @Nullable
    @BindView(R.id.right_text)
    protected TextView mRightText;

    protected Unbinder mUnbinder;



    protected boolean isLocationed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        mContext = this;
        beforeSetContentView();
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("cn.hnshangyu.zuyu.BaseActivity.MyReceiver");
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, filter);
        initView();
        initData(savedInstanceState);
        initListener();
    }


    /**
     * 在setContentView之前要做的一些操作
     */
    protected void beforeSetContentView() {

    }

    protected void setStatus() {
        StatusBarCompat.translucentStatusBar(mActivity);
    }

    /**
     * 获取布局layout的资源ID
     *
     * @return 资源ID
     */
    protected int getLayoutId() {
        return 0;
    }

    /**
     * 初始化控件(必须实现此方法)
     */
    protected abstract void initView();

    /**
     * 初始化监听
     */
    protected void initListener() {
    }


    /**
     * 初始化数据
     * (用到时实现)
     *
     * @param savedInstanceState
     */
    protected void initData(Bundle savedInstanceState) {

    }

    /**
     * 是否显示topBack 默认添加点击事件
     *
     * @param isShow
     */
    protected void onShowTopBack(boolean isShow) {
        if (mTtooTopBack != null) {
            if (isShow) {
                mTtooTopBack.setVisibility(View.VISIBLE);
                mTtooTopBack.setOnClickListener(this);
            } else {
                mTtooTopBack.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 是否显示titleBack 默认添加点击事件
     *
     * @param isShow
     */
    protected void onShowTitleBack(boolean isShow) {
        if (mTitleBack != null) {
            if (isShow) {
                mTitleBack.setVisibility(View.VISIBLE);
                mTitleBack.setOnClickListener(this);
            } else {
                mTitleBack.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置title
     *
     * @param cs 标题
     */
    protected void setTitleText(CharSequence cs) {
        if (mTitle != null && !StringUtil.isBland(cs)) {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText(cs);
            mTitle.setOnClickListener(this);
        }
    }

    /**
     * 设置title
     *
     * @param resId 资源ID
     */
    protected void setTitleText(int resId) {
        setTitleText(getString(resId));
    }

    /**
     * 设置右边图片按钮图片资源
     *
     * @param resid 图片资源ID
     */
    protected void setRightImage(int resid) {
        if (mRightImage != null) {
            mRightImage.setVisibility(View.VISIBLE);
            mRightImage.setImageResource(resid);
            mRightImage.setOnClickListener(this);
        }
    }

    /**
     * 设置右边图片按钮图片资源
     *
     * @param bitmap 图片资源
     */
    protected void setRightImage(Bitmap bitmap) {
        if (mRightImage != null) {
            mRightImage.setVisibility(View.VISIBLE);
            mRightImage.setImageBitmap(bitmap);
        }
    }

    /**
     * 设置右边图片按钮并列左边图片资源
     *
     * @param resid 图片资源ID
     */
    protected void setRightImageLeft(int resid) {
        if (mRightImageLeft != null) {
            mRightImageLeft.setVisibility(View.VISIBLE);
            mRightImageLeft.setImageResource(resid);
            mRightImageLeft.setOnClickListener(this);
        }
    }

    /**
     * 设置右边图片按钮并列左边图片资源
     *
     * @param bitmap 图片资源
     */
    protected void setRightImageLeft(Bitmap bitmap) {
        if (mRightImageLeft != null) {
            mRightImageLeft.setVisibility(View.VISIBLE);
            mRightImageLeft.setImageBitmap(bitmap);
        }
    }

    /**
     * 设置右边文字
     * 设置时默认显示
     *
     * @param cs 文字
     */
    protected void setRightText(CharSequence cs) {
        if (mRightText != null && !StringUtil.isBland(cs)) {
            mRightText.setVisibility(View.VISIBLE);
            mRightText.setText(cs);
            mRightText.setOnClickListener(this);
        }
    }

    /**
     * 设置右边文字
     * 设置时默认显示
     *
     * @param resid 资源ID
     */
    protected void setRightText(int resid) {
        if (mRightText != null) {
            mRightText.setVisibility(View.VISIBLE);
            mRightText.setText(resid);
            mRightText.setOnClickListener(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 能够退出当前所有activity的的广播接收者
     */
    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            finishAllActivity();
        }

    }

    private void finishAllActivity() {
        List<BaseActivity> tempList;
        synchronized (activities) {
            tempList = new LinkedList<BaseActivity>(activities);
        }
        for (BaseActivity a : tempList) {
            a.finish();
        }
    }

    /**
     * 退出所有的activity
     */
    public void killAll() {
        finishAllActivity();
        //杀死当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (activities) {
            activities.remove(this);
        }

        if (mUnbinder != null)
            mUnbinder.unbind();

        onInitDestroy();
        unregisterReceiver(myReceiver);
        //关闭试图的时候，如果有加载对话框，则关闭
        dissmissProgressDialog();
        System.gc();
    }

    protected void onInitDestroy() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                finish();
                break;
        }
        onInitClick(v);
    }

    protected void onInitClick(View v) {

    }


    /**
     * 弹出对话框
     *
     * @param c
     * @param msg
     */
    protected void showProgressDialog(Context c, String msg) {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(c);
            progressDialog.setMessage(msg);
            progressDialog.setCanceledOnTouchOutside(false);
        }

        progressDialog.show();
    }

    /**
     * 弹出对话框
     */
    protected void showProgressDialog(String msg) {
        showProgressDialog(mContext, msg);
    }

    protected void dissmissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


}
