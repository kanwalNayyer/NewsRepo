package com.hisham.news.utils;

public interface ItemSelectedListener {

    void onPollAnswerSelected(String vote, String content, int parentPosition,int votePosition);

    void onCategoryItemSelected(String category);

    void onCategoryItemUnSelected(String category);


}
