package com.kpi.FilianinCourseWork.dao.impl;

import com.kpi.FilianinCourseWork.dao.QuestionDao;
import com.kpi.FilianinCourseWork.model.Question;

import java.util.Collection;

public class QuestionDaoImpl implements QuestionDao {
    private Database database;
    private Integer id;

    public QuestionDaoImpl(Database database) {
        this.database = database;
        this.id = 5;
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
    public void addQuestion(String text, Integer userId) {
        Question question = new Question(id, text, userId);
        database.getQuestions().put(id++, question);
    }

    @Override
    public void deleteQuestion(Question question) {
        database.getQuestions().remove(question.getId());
    }

    @Override
    public void updateQuestion(Question question, Integer id) {
        database.getQuestions().put(id, question);
    }
}


