package com.fansquad.ffdiatips.skinrewards.datazone;

public class QUnitNode {
    private final String question;
    private final String[] options;
    private final int answerIndex;

    public QUnitNode(String question, String[] options, int answerIndex) {
        this.question = question;
        this.options = options;
        this.answerIndex = answerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }
}
