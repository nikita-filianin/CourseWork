package com.kpi.FilianinCourseWork.dao.impl;

import com.kpi.FilianinCourseWork.dao.UserDao;
import com.kpi.FilianinCourseWork.model.User;

import java.util.Collection;

public class UserDaoImpl implements UserDao {
    Database database;
    Integer id;

    public UserDaoImpl(Database database) {
        this.database = database;
        this.id = 0;
    }

    @Override
    public User getUserById(Integer id)
    {
        return database.getUsers().get(id);
    }

    public User getUserByLogin(String login) {
        for (User user : database.getUsers().values()) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    @Override
    public void addUser(String login, String passwordHash) {
        User user = new User(id, login, passwordHash);
        database.getUsers().put(id++, user);
    }

    @Override
    public Collection<User> getAllUsers() {
        return database.getUsers().values();
    }
}

