package com.kpi.FilianinCourseWork.dao.impl;

import com.kpi.FilianinCourseWork.dao.QuestionDao;
import com.kpi.FilianinCourseWork.model.Question;

import java.util.Collection;

public class QuestionDaoImpl implements QuestionDao {
    private Database database;
    private Integer id;

    public QuestionDaoImpl(Database database) {
        this.database = database;
        this.id = 0;
    }

    @Override
    public Collection<Question> getAllQuestions() {
        return database.getQuestions().values();
    }

    @Override
    public Question getQuestionById(Integer id) {
        return database.getQuestions().get(id);
    }

    @Override
    public void addQuestion(String text) {
        Question question = new Question(id, text);
        database.getQuestions().put(id++, question);
    }

    @Override
    public void deleteQuestion(Question question) {
        database.getQuestions().remove(question.getId());
    }

    @Override
    public void editQuestion(Question question, Integer id) {
        database.getQuestions().put(id, question);
    }
}

