package com.kpi.FilianinCourseWork.model;

import java.util.Objects;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Answer {
    private Integer id;
    private Question question;
    private User user;
    private String text;
    private Instant instant;


    public Answer(Integer id, Question question, User user, String text, Instant instant) {
        this.id = id;
        this.question = question;
        this.user = user;
        this.text = text;
        this.instant = instant;
    }

    public Answer(Integer id, Question question, User user, String text) {
        this(id, question, user, text, Instant.now());
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

    public String getInstant() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                .withLocale( Locale.UK )
                .withZone( ZoneId.systemDefault() );
        return formatter.format(instant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id.equals(answer.id) && question.equals(answer.question) && user.equals(answer.user) &&
                text.equals(answer.text) && instant.equals(answer.instant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, user, text, instant);
    }
}

