package com.kpi.FilianinCourseWork.dao;

public interface DaoFactory {

    UserDao getUserDao();

    QuestionDao getQuestionDao();

    AnswerDao getAnswerDao();
}

