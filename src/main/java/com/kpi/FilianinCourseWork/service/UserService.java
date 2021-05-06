package com.kpi.FilianinCourseWork.service;

import com.kpi.FilianinCourseWork.dao.DaoFactory;
import com.kpi.FilianinCourseWork.exceptions.FailedPasswordConfirmationException;
import com.kpi.FilianinCourseWork.exceptions.InvalidPasswordException;
import com.kpi.FilianinCourseWork.exceptions.NonexistentUserException;
import com.kpi.FilianinCourseWork.exceptions.ExistentUserException;
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
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int idx = 0; idx < hashedBytes.length; idx++) {
            byte b = hashedBytes[idx];
            hash.append(digits[(b & 0xf0) >> 4]);
            hash.append(digits[b & 0x0f]);
        }

        return hash.toString();
    }

    public User logIn(String login, String password)
            throws NonexistentUserException, InvalidPasswordException, NoSuchAlgorithmException, NullPointerException {
        if (login == null) {
            throw new NullPointerException("Login field is empty!");
        }

        if (password == null) {
            throw new NullPointerException("Password field is empty!");
        }
        User user = daoFactory.getUserDao().getUserByLogin(login);
        if (user == null) {
            throw new NonexistentUserException("User with login '" + login + "'not found!");
        }

        if (!user.getPasswordHash().equals(passwordHasher(password))) {
            throw new InvalidPasswordException("Invalid Password!");
        }
        return user;
    }

    public void signUp(String login, String password, String confirmedPassword)
            throws FailedPasswordConfirmationException, ExistentUserException, NoSuchAlgorithmException {

        if (daoFactory.getUserDao().getUserByLogin(login) != null) {
            throw new ExistentUserException("User with login '" + login + "' already exists!");
        }

        if (!password.equals(confirmedPassword)) {
            throw new FailedPasswordConfirmationException("Passwords don't match!");
        }

        daoFactory.getUserDao().addUser(login, passwordHasher(password));
    }
}

