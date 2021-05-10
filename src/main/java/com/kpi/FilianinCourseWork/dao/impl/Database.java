package com.kpi.FilianinCourseWork.dao.impl;

import com.kpi.FilianinCourseWork.model.Answer;
import com.kpi.FilianinCourseWork.model.Question;
import com.kpi.FilianinCourseWork.model.User;

import java.util.Map;
import java.util.TreeMap;

public class Database {
    private Map<Integer, User> users;
    private Map<Integer, Answer> answers;
    private Map<Integer, Question> questions;

    public Database() {
        users = new TreeMap<>();
        answers = new TreeMap<>();
        questions = new TreeMap<>();
    }

    public DaoFactoryImpl getDaoFactory() {
        return new DaoFactoryImpl(this);
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Map<Integer, Answer> getAnswers() {
        return answers;
    }

    public Map<Integer, Question> getQuestions() {
        return questions;
    }
}

