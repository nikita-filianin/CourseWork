package com.kpi.FilianinCourseWork.dao;

import com.kpi.FilianinCourseWork.model.Question;

import java.util.Collection;

public interface QuestionDao {

    Collection<Question> getAllQuestions();

    Question getQuestionById(Integer id);

    void addQuestion(String text);

    void deleteQuestion(Question question);

    void updateQuestion(Question question, Integer id);

}

