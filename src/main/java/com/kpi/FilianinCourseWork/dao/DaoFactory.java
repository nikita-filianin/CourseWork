package com.kpi.FilianinCourseWork.dao;

import com.kpi.FilianinCourseWork.model.Answer;
import com.kpi.FilianinCourseWork.model.Question;

public interface DaoFactory {

    public UserDao getUserDao();

    public QuestionDao getQuestionDao();

    public AnswerDao getAnswerDao();
}

