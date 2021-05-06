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
        Question question = new Question(1, "How to solve this equation?");
        database.getQuestions().put(1, question);
        database.getQuestions().put(2, new Question(2, "How to unpark my CPU cores?"));
        database.getQuestions().put(3, new Question(3, "How to create HelloWorld on Assembly?"));

        DaoFactoryImpl daoFactory = database.getDaoFactory();

            UserService userService = new UserService(daoFactory);
            QuestionService questionService = new QuestionService(daoFactory);

        try {
            User user = new User(1, "", userService.passwordHasher(""));
            database.getUsers().put(1, user);
        } catch (
                NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("questionService", questionService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

