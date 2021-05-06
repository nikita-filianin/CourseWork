package com.kpi.FilianinCourseWork.model;

import java.util.Objects;

public class Answer {
    private Integer id;
    private Question question;
    private User user;
    private String text;


    public Answer(Integer id, Question question, User user, String text) {
        this.id = id;
        this.question = question;
        this.user = user;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id.equals(answer.id) && question.equals(answer.question) && user.equals(answer.user) &&
                text.equals(answer.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, user, text);
    }
}

