package com.kpi.FilianinCourseWork.controller;

import com.kpi.FilianinCourseWork.dao.impl.DaoFactoryImpl;
import com.kpi.FilianinCourseWork.dao.impl.Database;
import com.kpi.FilianinCourseWork.model.Question;
import com.kpi.FilianinCourseWork.model.User;
import com.kpi.FilianinCourseWork.service.QuestionService;
import com.kpi.FilianinCourseWork.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.security.NoSuchAlgorithmException;


@WebListener
public class ApplicationContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Database database = new Database();
        Question question1 = new Question(1, "Kak sdat' kursa4b?");
        database.getQuestions().put(1, question1);
        Question question2 = new Question(2, "Kak sdat' ekzamen?");
        database.getQuestions().put(2, question2);
        Question question3 = new Question(3, "Does finally always work?");
        database.getQuestions().put(3, question3);


        DaoFactoryImpl daoFactory = database.getDaoFactory();

        UserService userService = new UserService(daoFactory);
        QuestionService questionService = new QuestionService(daoFactory);

        try {
            User tom =  new User(1, "mitin", userService.passwordHasher("1221"));
            database.getUsers().put(1, tom);
            daoFactory.getAnswerDao().addAnswer(question1, tom, "Da tak... -100/100");

            User john =  new User(2, "bukasov", userService.passwordHasher("1331"));
            database.getUsers().put(2, john);
            daoFactory.getAnswerDao().addAnswer(question2, john, "Da tak... -50/50");

            User will =  new User(3, "vovk", userService.passwordHasher("1441"));
            database.getUsers().put(3, will);
            daoFactory.getAnswerDao().addAnswer(question3, will, "na peresdachy!!!");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("questionService", questionService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

