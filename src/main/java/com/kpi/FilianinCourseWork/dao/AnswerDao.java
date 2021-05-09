package com.kpi.FilianinCourseWork.dao;

import com.kpi.FilianinCourseWork.model.Answer;
import com.kpi.FilianinCourseWork.model.Question;
import com.kpi.FilianinCourseWork.model.User;

import java.util.Collection;

public interface AnswerDao {

    Collection<Answer> findByQuestionId(Integer id);

    void addAnswer(Question question, User user, String text);
}