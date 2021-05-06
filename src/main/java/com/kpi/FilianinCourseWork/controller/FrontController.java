package com.kpi.FilianinCourseWork.controller;

import com.kpi.FilianinCourseWork.exceptions.ExistentUserException;
import com.kpi.FilianinCourseWork.exceptions.FailedPasswordConfirmationException;
import com.kpi.FilianinCourseWork.exceptions.InvalidPasswordException;
import com.kpi.FilianinCourseWork.exceptions.NonexistentUserException;
import com.kpi.FilianinCourseWork.service.QuestionService;
import com.kpi.FilianinCourseWork.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


@WebServlet(name = "FrontController", urlPatterns = "/do/*")
public class FrontController extends HttpServlet {

    private UserService userService;
    private QuestionService questionService;

    @Override
    public void init(ServletConfig config)  {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        questionService = (QuestionService) config.getServletContext().getAttribute("questionService");
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }
        try {
            switch (pathInfo) {
                case "/login":
                    logIn(request, response);
                    break;
                case "/logout":
                    logOut(request, response);
                    break;
                case "/signup":
                    signUp(request, response);
                    break;
//                case "/questions":
//                    questions(request, response);
//                    break;
                case "/":
                case "/questions":
                    questions(request, response);
                    break;
                default:
                    response.sendError(405);
                    break;
            }
        } catch (RuntimeException ex) {
            error(request, response);
        }
    }
    private void questions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/questions.jsp").forward(request, response);
    }

    private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/html/error404.html").forward(request, response);
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("./questions");
    }

    private void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        String login = request.getParameter("login");
        if (login == null) {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }
        String password = request.getParameter("password");
        try {
            request.getSession().setAttribute("user", userService.logIn(login, password));
        } catch (NonexistentUserException e) {
            request.setAttribute("errorMessage", "Please, enter an existing login");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        } catch (InvalidPasswordException e) {
            request.setAttribute("errorMessage", "Password is incorrect");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        } catch (NoSuchAlgorithmException e) {
            error(request, response);
        }

        response.sendRedirect("./questions");
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        String login = request.getParameter("login");
        if (login == null) {
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        }
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        try {
            userService.signUp(login, password1, password2);
        } catch (FailedPasswordConfirmationException e) {
            request.setAttribute("errorMessage", "Passwords don't match");
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        } catch (ExistentUserException e) {
            request.setAttribute("errorMessage", "Login already taken");
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        } catch (NoSuchAlgorithmException e) {
            error(request, response);
            return;
        }
        request.setAttribute("greeting", "You have been registered! Now log in.");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}