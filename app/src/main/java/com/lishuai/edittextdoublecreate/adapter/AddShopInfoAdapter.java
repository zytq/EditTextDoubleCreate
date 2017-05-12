package com.lishuai.edittextdoublecreate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.lishuai.edittextdoublecreate.R;
import com.lishuai.edittextdoublecreate.bean.ProductParam;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 添加商品规格参数
 */
public class AddShopInfoAdapter extends RecyclerView.Adapter<AddShopInfoAdapter.ViewHolder> {
    private Context mContext;

    private ArrayList<ProductParam> mStandardParams;


    public ArrayList<ProductParam> getStandardParams() {
        return mStandardParams;
    }

    public void setStandardParams(ArrayList<ProductParam> mStandardParams) {
        this.mStandardParams = mStandardParams;
    }

    public AddShopInfoAdapter(Context mContext) {
        this.mContext = mContext;

    }

    @Override
    public AddShopInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_shop_info, parent, false);
        return new AddShopInfoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AddShopInfoAdapter.ViewHolder holder, final int position) {
        holder.etParamKey.setTag(position);
        holder.etParamValue.setTag(position);
        holder.etParamKey.setText(mStandardParams.get(position).getKey());
        holder.etParamValue.setText(mStandardParams.get(position).getValue());
        holder.etParamKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (((int)(holder.etParamKey.getTag()))== position) {
                        onKeyFillListener2.onKeyFill2(position,s.toString());

                }
            }
        });

        holder.etParamValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (((int)(holder.etParamValue.getTag()))== position) {

                        onValueFillListener2.onValueFill2(position,s.toString());

                }
            }
        });
        // 设置删除条目的操作
        holder.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStandardParams.size() != 0) {
                    deleteItem(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {

        return mStandardParams == null ? 0:mStandardParams.size();

    }//返回条目的数量


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_minus)
        ImageView ivMinus;

        @BindView(R.id.et_param_key)
        EditText etParamKey;

        @BindView(R.id.et_param_value)
        EditText etParamValue;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private AddShopInfoAdapter.OnKeyFillListener2 onKeyFillListener2;

    private AddShopInfoAdapter.OnValueFillListener2 onValueFillListener2;

    public interface OnKeyFillListener2 {
        void onKeyFill2(int position, String key);
    }

    public interface OnValueFillListener2 {
        void onValueFill2(int position, String value);
    }

    public void setOnKeyFillListener2(AddShopInfoAdapter.OnKeyFillListener2 onPriceFillListener2) {
        this.onKeyFillListener2 = onPriceFillListener2;
    }

    public void setOnValueFillListener2(AddShopInfoAdapter.OnValueFillListener2 onSubFillListener2) {
        this.onValueFillListener2 = onSubFillListener2;
    }
    /**
     * 删除条目
     * @param position
     */
    private void deleteItem(int position ) {
        if (mStandardParams.size() > position) {
            mStandardParams.remove(position);
            notifyItemRemoved(position);

        }
    }

    /**
     * 增加条目
     * @param position
     */
    private void insertItem(int position) {
        notifyItemInserted(position);
    }}