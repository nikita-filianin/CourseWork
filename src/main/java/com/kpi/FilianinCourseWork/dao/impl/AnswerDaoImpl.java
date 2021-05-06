package com.kpi.FilianinCourseWork.dao.impl;

import com.kpi.FilianinCourseWork.dao.AnswerDao;
import com.kpi.FilianinCourseWork.model.Answer;
import com.kpi.FilianinCourseWork.model.Question;
import com.kpi.FilianinCourseWork.model.User;

import java.util.Collection;

public class AnswerDaoImpl implements AnswerDao {
    private Database database;
    private Integer id;

    public AnswerDaoImpl(Database database) {
        this.database = database;
        this.id = 0;
    }

    @Override
    public Collection<Answer> findByQuestionId(Integer id) {
        return database.getAnswers().values();
    }

    @Override
    public void addAnswer(Question question, User user, String text) {
        Answer answer = new Answer(id, question, user, text);
        database.getAnswers().put(id++, answer);
        question.addAnswer(answer);
    }
}