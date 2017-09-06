package com.example.account.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.example.account.R;
import com.example.account.data.CeramicsInfo;

import java.util.List;

public class TaskAdapter extends ExpandableRecyclerAdapter<Recipe, CeramicsInfo, RecipeViewHolder, IngredientViewHolder> {


    private LayoutInflater mInflater;

    protected OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnClick(View view, int parentPosition, int childPosition);
    }

    public TaskAdapter(Context context, @NonNull List<Recipe> parentList) {
        super(parentList);

        mInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView = mInflater.inflate(R.layout.recipe_view, parentViewGroup, false);
        return new RecipeViewHolder(recipeView);
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView = mInflater.inflate(R.layout.task_item, childViewGroup, false);
        return new IngredientViewHolder(ingredientView, mListener);
    }

    @Override
    public void onBindParentViewHolder(@NonNull RecipeViewHolder parentViewHolder, int parentPosition, @NonNull Recipe parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull IngredientViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull CeramicsInfo child) {
        childViewHolder.bind(child);
    }

    public void update(List<Recipe> recipes) {
        getParentList().clear();
        setParentList(recipes, false);
        notifyDataSetChanged();
    }
}
