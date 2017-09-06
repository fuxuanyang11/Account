package com.example.account.task;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.example.account.R;
import com.example.account.data.CeramicsInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientViewHolder extends ChildViewHolder implements View.OnClickListener{

    private TaskAdapter.OnItemClickListener mListener;

    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.number)
    TextView mNumber;
    @BindView(R.id.total)
    TextView mTotal;
    @BindView(R.id.specification)
    TextView mSpecification;
    @BindView(R.id.amount)
    TextView mAmount;
    @BindView(R.id.price)
    TextView mPrice;
    @BindView(R.id.deduct)
    TextView mDeduct;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public IngredientViewHolder(@NonNull View itemView, TaskAdapter.OnItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mListener = listener;
        itemView.setOnClickListener(this);

    }

    public void bind(CeramicsInfo ceramicsInfo) {
        mDate.setText("日期:" + ceramicsInfo.getDate());
        mTotal.setText("总价:" + ceramicsInfo.getTotal());
        mAmount.setText("数量:" + ceramicsInfo.getAmount());
        mPrice.setText("价格:" + ceramicsInfo.getPrice());
        if (TextUtils.isEmpty(ceramicsInfo.getNumber()) && TextUtils.isEmpty(ceramicsInfo.getSpecification())){
            mNumber.setVisibility(View.GONE);
            mSpecification.setVisibility(View.GONE);
            mDeduct.setVisibility(View.VISIBLE);
            mDeduct.setText("扣点:" + ceramicsInfo.getDeduct());
        }else {
            mNumber.setVisibility(View.VISIBLE);
            mSpecification.setVisibility(View.VISIBLE);
            mDeduct.setVisibility(View.GONE);
            mNumber.setText("编号:" + ceramicsInfo.getNumber());
            mSpecification.setText("规格:" + ceramicsInfo.getSpecification());
        }

    }


    @Override
    public void onClick(View v) {
        if (mListener != null){
            mListener.OnClick(v, getParentAdapterPosition(), getChildAdapterPosition());
        }
    }
}
