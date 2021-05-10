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


        DaoFactoryImpl daoFactory = database.getDaoFactory();

        UserService userService = new UserService(daoFactory);
        QuestionService questionService = new QuestionService(daoFactory);

        try {
            User tom = new User(1, "tom", userService.passwordHasher("111"));
            database.getUsers().put(1, tom);
            Question question1 = new Question(1, "How to install Java?", tom.getId());
            database.getQuestions().put(1, question1);
            daoFactory.getAnswerDao().addAnswer(question1, tom, "To install Java, you first need to download the " +
                    "installer program from Oracle. Click the " + "\"Free Java Download\" button. " +
                    "You are then prompted to read and agree with the end " +
                    "user license agreement.");

            User john = new User(2, "john", userService.passwordHasher("222"));
            database.getUsers().put(2, john);
            Question question2 = new Question(2, "How to gitignore .idea files?", john.getId());
            database.getQuestions().put(2, question2);
            daoFactory.getAnswerDao().addAnswer(question2, john, "Go to File > Settings > Version Control > " +
                    "Ignored Files. Then add your folder or file to git ignore.");

            User will = new User(3, "will", userService.passwordHasher("333"));
            database.getUsers().put(3, will);
            Question question3 = new Question(3, "How to create a branch on github?", will.getId());
            database.getQuestions().put(3, question3);
            daoFactory.getAnswerDao().addAnswer(question3, will, "On GitHub, navigate to the main page of " +
                    "the repository. Click the branch selector menu. Type a unique name for your new branch, " +
                    "then select Create branch.");


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

