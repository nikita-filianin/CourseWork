package com.kpi.FilianinCourseWork.controller;

import com.kpi.FilianinCourseWork.exceptions.IncorrectPasswordException;
import com.kpi.FilianinCourseWork.exceptions.IncorrectLoginException;
import com.kpi.FilianinCourseWork.model.Question;
import com.kpi.FilianinCourseWork.model.User;
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
    public void init(ServletConfig config) {
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
                case "/question":
                    question(request, response);
                    break;
                case "/answer":
                    answer(request, response);
                    break;
                case "/addQuestion":
                    addQuestion(request, response);
                    break;
                case "/deleteQuestion":
                    deleteQuestion(request, response);
                    break;
                case "/editQuestion":
                    editQuestion(request, response);
                    break;
                case "/":
                case "/questions":
                    questions(request, response);
                    break;
                default:
                    response.sendError(404);
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
        request.getRequestDispatcher("/WEB-INF/html/error.html").forward(request, response);
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
        } catch (IncorrectLoginException e) {
            request.setAttribute("errorMessage", "Enter an existing login!");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        } catch (IncorrectPasswordException e) {
            request.setAttribute("errorMessage", "Invalid Password!");
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
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");

        try {
            userService.signUp(login, pass1, pass2);
        } catch (IncorrectPasswordException e) {
            request.setAttribute("errorMsg", "Passwords are different!");
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        } catch (IncorrectLoginException e) {
            request.setAttribute("errorMsg", "Login is already taken!");
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        } catch (NoSuchAlgorithmException e) {
            error(request, response);
            return;
        }
        request.setAttribute("success", "You have been registered! Now you can log in!");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    private void editQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idParam = request.getParameter("id");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                response.sendRedirect("questions");
                return;
            }
            Question question = questionService.getQuestionById(id);
            if (question == null) {
                response.sendRedirect("questions");
                return;
            }

            String text = request.getParameter("text");
            questionService.editQuestion(question, text);
        }
        response.sendRedirect("questions");

    }


    private void addQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (title == null || title == "") {
                request.getRequestDispatcher("/WEB-INF/jsp/addQuestion.jsp").forward(request, response);
            } else {
                String text = request.getParameter("text");
                if (text == null) {
                    text = "";
                }
                questionService.addQuestion(text);
                response.sendRedirect("questions");
            }
        } else {
            response.sendRedirect("questions");
        }
    }

    private void deleteQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        User user = (User) request.getSession().getAttribute("user");
        if (idParam == null || user == null ) {
            response.sendRedirect("questions");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("questions");
            return;
        }
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            response.sendRedirect("questions");
            return;
        }
        questionService.deleteQuestion(question);
        questionService.getAllQuestions().forEach(question1 -> System.err.println(question1.getText()));
        response.sendRedirect("questions");
    }

    private void answer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        String text = request.getParameter("text");
        User user = (User) request.getSession().getAttribute("user");
        if (idParam == null || text == null || text == "" || user == null) {
            response.sendRedirect("questions");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("questions");
            return;
        }

        Question question = questionService.getQuestionById(id);
        if (question == null) {
            response.sendRedirect("questions");
            return;
        }

        questionService.addAnswer(question, user, text);
        response.sendRedirect("question?id=" + question.getId());
    }

    private void question(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("questions");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("questions");
            return;
        }

        Question question = questionService.getQuestionById(id);
        if (question == null) {
            response.sendRedirect("questions");
            return;
        }
        request.setAttribute("question", question);
        request.setAttribute("answers", question.getAnswers());
        request.getRequestDispatcher("/WEB-INF/jsp/question.jsp").forward(request, response);
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