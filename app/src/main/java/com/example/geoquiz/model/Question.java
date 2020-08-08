package com.example.geoquiz.model;

public class Question {
    private int mQuestionTextResId;
    private boolean mIsAnswerTrue;
    private boolean mAnswered;
    public int getQuestionTextResId() {
        return mQuestionTextResId;
    }

    public void setQuestionTextResId(int questionTextResId) {
        mQuestionTextResId = questionTextResId;
    }

    public boolean isAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mIsAnswerTrue = answerTrue;
    }

    public Question(int questionTextResId, boolean isAnswerTrue,boolean mAnswered) {
        mQuestionTextResId = questionTextResId;
        mIsAnswerTrue = isAnswerTrue;
        mAnswered=mAnswered;
    }

    public Question() {
    }
}
