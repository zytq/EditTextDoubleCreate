package com.lishuai.edittextdoublecreate;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lishuai.edittextdoublecreate.adapter.AddShopBaseInfoAdapter;
import com.lishuai.edittextdoublecreate.adapter.AddShopInfoAdapter;
import com.lishuai.edittextdoublecreate.bean.ProductParam;
import com.lishuai.edittextdoublecreate.view.RecycleViewDivider;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements AddShopBaseInfoAdapter.OnKeyFillListener, AddShopBaseInfoAdapter.OnValueFillListener, AddShopInfoAdapter.OnKeyFillListener2, AddShopInfoAdapter.OnValueFillListener2 {

    /**
     * 商品基本信息
     */
    @BindView(R.id.shop_basic_info_recyclerview)
    RecyclerView shopBasicInfoRecyclerview;
    /**
     * 添加基本信息
     */
    @BindView(R.id.tv_add_basic_info)
    TextView tvAddBasicInfo;
    /**
     * 商品规格参数
     */
    @BindView(R.id.shop_shop_standard_parameter)
    RecyclerView shopShopStandardParameter;
    /**
     * 添加规格参数
     */
    @BindView(R.id.tv_add_standard_parameter)
    TextView tvAddStandardParameter;

    private ArrayList<ProductParam> params = new ArrayList<>();//商品基本参数
    private ArrayList<ProductParam> standardParams = new ArrayList<>();//商品规格参数
    private AddShopBaseInfoAdapter addShopBaseInfoAdapter;//添加条目的信息
    private AddShopInfoAdapter standardAdapter;
    private LinearLayoutManager manager1;
    private LinearLayoutManager manager2;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        //添加基本信息
        manager1 = new LinearLayoutManager(mContext);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        int mColor = ContextCompat.getColor(mContext, R.color.background);
        shopBasicInfoRecyclerview.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, 1, mColor));
        shopBasicInfoRecyclerview.setLayoutManager(manager1);
        addShopBaseInfoAdapter = new AddShopBaseInfoAdapter(mContext);
        shopBasicInfoRecyclerview.setAdapter(addShopBaseInfoAdapter);

        //添加规格参数
        manager2 = new LinearLayoutManager(mContext);
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        shopShopStandardParameter.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL, 1, mColor));
        shopShopStandardParameter.setLayoutManager(manager2);
        standardAdapter = new AddShopInfoAdapter(mContext);
        shopShopStandardParameter.setAdapter(standardAdapter);
    }

    @Override
    protected void initView() {
        setTitleText(getString(R.string.app_name));

    }

    @Override
    protected void initListener() {
        super.initListener();
        tvAddBasicInfo.setOnClickListener(this);
        tvAddStandardParameter.setOnClickListener(this);
        addShopBaseInfoAdapter.setOnKeyFillListener(this);
        addShopBaseInfoAdapter.setOnValueFillListener(this);
        standardAdapter.setOnKeyFillListener2(this);
        standardAdapter.setOnValueFillListener2(this);
    }

    @Override
    protected void onInitClick(View v) {
        super.onInitClick(v);
        switch (v.getId()) {
            case R.id.tv_add_basic_info://添加基本信息
                if (params != null) {
                    params.add(new ProductParam());
                    addShopBaseInfoAdapter.setParams(params);
                    addShopBaseInfoAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_add_standard_parameter://添加规格参数
                if (standardParams != null) {
                    standardParams.add(new ProductParam());
                    standardAdapter.setStandardParams(standardParams);
                    standardAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    /**
     * 回调参数名填好后的方法
     * @param position
     */
    @Override
    public void onKeyFill(int position, String key) {
        if (position < params.size()) {
            params.get(position).setKey(key);
        }

    }

    /**
     * 回调参数值填好后的方法
     * @param position
     */
    @Override
    public void onValueFill(int position, String value) {
        if (position < params.size()) {
            params.get(position).setValue(value);
        }

    }
    /**
     * 回调参数名填好后的方法
     * @param position
     */
    @Override
    public void onKeyFill2(int position, String key) {
        //判断当前位置是否存在
        if (position < standardParams.size()) {
            standardParams.get(position).setKey(key);
        }

    }

    /**
     * 回调参数值填好后的方法
     * @param position
     */
    @Override
    public void onValueFill2(int position, String value) {
        if (position < standardParams.size()) {
            standardParams.get(position).setValue(value);
        }

    }
}
