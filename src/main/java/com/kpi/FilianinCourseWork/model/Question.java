package com.kpi.FilianinCourseWork.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Question {
    private Integer id;
    private String text;
    private Collection<Answer> answers;
    private Integer userId;
    private Boolean isSolved;

    public Question(Integer id, String text, Integer userId) {
        this.id = id;
        this.text = text;
        this.answers = new ArrayList<>();
        this.isSolved = false;
        this.userId = userId;

    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public Boolean getSolved() {
        return isSolved;
    }

    public void setSolved(Boolean solved) {
        isSolved = solved;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id.equals(question.id) && text.equals(question.text) && answers.equals(question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, answers);
    }
}
