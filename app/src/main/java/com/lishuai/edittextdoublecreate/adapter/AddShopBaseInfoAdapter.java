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
 * 添加商品基本信息
 */
public class AddShopBaseInfoAdapter extends RecyclerView.Adapter<AddShopBaseInfoAdapter.ViewHolder> {
    private Context mContext;

    private ArrayList<ProductParam> mParams;



    public ArrayList<ProductParam> getParams() {
        return mParams;
    }

    public void setParams(ArrayList<ProductParam> params) {
        this.mParams = params;
    }



    public AddShopBaseInfoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_shop_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.etParamKey.setTag(position);
        holder.etParamValue.setTag(position);
        holder.etParamKey.setText(mParams.get(position).getKey());
        holder.etParamValue.setText(mParams.get(position).getValue());
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
                        onKeyFillListener.onKeyFill(position,s.toString());
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
                        onValueFillListener.onValueFill(position,s.toString());

                }
            }
        });
       // 设置删除条目的操作
          holder.ivMinus.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (mParams.size() != 0) {
                      deleteItem(position);
                  }
              }
          });
    }


    @Override
    public int getItemCount() {
            return mParams == null ? 0 : mParams.size();

    }


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

    private OnKeyFillListener onKeyFillListener;

    private OnValueFillListener onValueFillListener;

    public interface OnKeyFillListener {
        void onKeyFill(int position, String key);
    }

    public interface OnValueFillListener {
        void onValueFill(int position, String value);
    }

    public void setOnKeyFillListener(OnKeyFillListener onPriceFillListener) {
        this.onKeyFillListener = onPriceFillListener;
    }

    public void setOnValueFillListener(OnValueFillListener onSubFillListener) {
        this.onValueFillListener = onSubFillListener;
    }
    /**
     * 删除条目
     * @param position
     */
    private void deleteItem(int position ) {
        if (mParams.size() > position) {
            mParams.remove(position);
            notifyItemRemoved(position);

        }
    }
}