package com.kpi.FilianinCourseWork.service;

import com.kpi.FilianinCourseWork.dao.DaoFactory;
import com.kpi.FilianinCourseWork.exceptions.IncorrectPasswordException;
import com.kpi.FilianinCourseWork.exceptions.IncorrectLoginException;
import com.kpi.FilianinCourseWork.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    private DaoFactory daoFactory;

    public UserService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public String passwordHasher(String password) throws NoSuchAlgorithmException {
        StringBuilder hash = new StringBuilder();
        MessageDigest sha = MessageDigest.getInstance("SHA-512");
        byte[] hashedBytes = sha.digest(password.getBytes(StandardCharsets.UTF_8));
        char[] values = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int idx = 0; idx < hashedBytes.length; idx++) {
            byte b = hashedBytes[idx];
            hash.append(values[(b & 0xf0) >> 4]);
            hash.append(values[b & 0x0f]);
        }

        return hash.toString();
    }

    public User logIn(String login, String password)
            throws IncorrectLoginException, IncorrectPasswordException, NoSuchAlgorithmException, NullPointerException {
        if (login == null) {
            throw new NullPointerException("Login field is empty!");
        }

        if (password == null) {
            throw new NullPointerException("Password field is empty!");
        }
        User user = daoFactory.getUserDao().getUserByLogin(login);
        if (user == null) {
            throw new IncorrectLoginException("User with the login '" + login + "'not found!");
        }

        if (!user.getPasswordHash().equals(passwordHasher(password))) {
            throw new IncorrectPasswordException("Invalid Password!");
        }
        return user;
    }

    public void signUp(String login, String password, String confirmedPassword)
            throws IncorrectPasswordException, IncorrectLoginException, NoSuchAlgorithmException {

        if (daoFactory.getUserDao().getUserByLogin(login) != null) {
            throw new IncorrectLoginException("User with the login '" + login + "' already exists!");
        }

        if (!password.equals(confirmedPassword)) {
            throw new IncorrectPasswordException("Passwords are different!");
        }

        daoFactory.getUserDao().addUser(login, passwordHasher(password));
    }
}