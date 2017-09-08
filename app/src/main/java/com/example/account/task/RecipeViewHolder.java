package com.example.account.task;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.example.account.R;
import com.example.account.data.CeramicsInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeViewHolder extends ParentViewHolder {

    @BindView(R.id.recipe_textview)
    TextView mRecipeTextview;
    @BindView(R.id.month_total)
    TextView mMonthTotal;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bind(Recipe recipe) {

        mRecipeTextview.setText(recipe.getName());
        List<CeramicsInfo> childList = recipe.getChildList();
        float monthTotal = 0;
        for (CeramicsInfo info : childList) {
            float value = Float.valueOf(info.getTotal());
            monthTotal += value;
        }
        mMonthTotal.setText(recipe.getName().substring(5, 8) + "总价:" + monthTotal);
    }

    @Override
    public void setMainItemClickToExpand() {
        super.setMainItemClickToExpand();
    }
}
