package com.kpi.FilianinCourseWork.service;

import com.kpi.FilianinCourseWork.dao.DaoFactory;
import com.kpi.FilianinCourseWork.model.Question;
import com.kpi.FilianinCourseWork.model.User;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuestionService {
    private DaoFactory daoFactory;

    public QuestionService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void addQuestion(String text) {
        daoFactory.getQuestionDao().addQuestion(text);
    }

    public void deleteQuestion(Question question) {
        daoFactory.getQuestionDao().deleteQuestion(question);
    }

    public Collection<Question> getAllQuestions() {
        return daoFactory.getQuestionDao().getAllQuestions();
    }

    public Collection<Question> searchByText(String text) {
        String[] words = text.toLowerCase().split(" ");
        return daoFactory.getQuestionDao().getAllQuestions().stream()
                .filter(question -> containsAllWords(question, words))
                .collect(Collectors.toList());
    }

    public Question getQuestionById(Integer id) {
        return daoFactory.getQuestionDao().getQuestionById(id);
    }

    public void addAnswer(Question question, User user, String text) {
        daoFactory.getAnswerDao().addAnswer(question, user, text);
    }

    public void updateQuestion(Question question, String text) {
        daoFactory.getQuestionDao().updateQuestion(new Question(question.getId(), text), question.getId());
    }

    private static boolean containsAllWords(Question question, String[] words) {
        String string = question.getText();
        string = string.toLowerCase();
        return Stream.of(words).allMatch(string::contains);
    }
}

