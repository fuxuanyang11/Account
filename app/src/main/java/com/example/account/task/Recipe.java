package com.example.account.task;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.example.account.data.CeramicsInfo;

import java.util.List;

public class Recipe implements Parent<CeramicsInfo>{

    private List<CeramicsInfo> mCeramicsInfos;

    private String mName;


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Recipe(String name, List<CeramicsInfo> ceramicsInfo) {
        mCeramicsInfos = ceramicsInfo;
        mName = name;
    }

    @Override
    public List<CeramicsInfo> getChildList() {
        return mCeramicsInfos;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return true;
    }
}
