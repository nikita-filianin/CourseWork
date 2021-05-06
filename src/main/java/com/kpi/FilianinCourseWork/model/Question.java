package com.kpi.FilianinCourseWork.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Question {
    private Integer id;
    private String text;
    private Collection<Answer> answers;

    public Question(Integer id, String text) {
        this.id = id;
        this.text = text;
        this.answers = new ArrayList<>();
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
