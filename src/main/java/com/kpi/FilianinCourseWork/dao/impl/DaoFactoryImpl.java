package com.kpi.FilianinCourseWork.dao.impl;

import com.kpi.FilianinCourseWork.dao.AnswerDao;
import com.kpi.FilianinCourseWork.dao.DaoFactory;
import com.kpi.FilianinCourseWork.dao.QuestionDao;
import com.kpi.FilianinCourseWork.dao.UserDao;


public class DaoFactoryImpl implements DaoFactory {
    private UserDao userDao;
    private QuestionDao questionDao;
    private AnswerDao answerDao;

    public DaoFactoryImpl(Database database) {
        this.userDao = new UserDaoImpl(database);
        this.questionDao = new QuestionDaoImpl(database);
        this.answerDao = new AnswerDaoImpl(database);

    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public QuestionDao getQuestionDao() {
        return questionDao;
    }

    @Override
    public AnswerDao getAnswerDao() {
        return answerDao;
    }
}