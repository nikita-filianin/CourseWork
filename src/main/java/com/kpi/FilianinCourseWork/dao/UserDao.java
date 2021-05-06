package com.kpi.FilianinCourseWork.dao;

import com.kpi.FilianinCourseWork.model.User;

import java.util.Collection;

public interface UserDao {

    public Collection<User> getAllUsers();

    public User getUserById(Integer id);

    public User getUserByLogin(String login);

    public void addUser(String login, String passwordHash);
}

