package com.kpi.FilianinCourseWork.model;

import java.util.Objects;

public class User {
    private Integer id;
    private String login;
    private String passwordHash;

    public User(Integer id, String login, String passwordHash) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && login.equals(user.login) && passwordHash.equals(user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, passwordHash);
    }
}
