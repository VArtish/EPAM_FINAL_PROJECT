package com.example.finalwebprojectepam.model.entity;

import com.example.finalwebprojectepam.model.entity.type.UserRole;
import com.example.finalwebprojectepam.model.entity.type.UserState;

import java.sql.Date;

public class User extends AbstractEntity {
    private long id;
    private String name;
    private String surname;
    private String email;
    private Date dateOfBirth;
    private String login;
    private UserRole userRole;
    private UserState userState;
    private String password;

    public User() {
    }

    public User(long id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getLogin() {
        return login;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public UserState getUserState() {
        return userState;
    }

    public static class Builder {
        private final User user;

        public Builder(long id, String login) {
            user = new User(id, login);
        }

        public Builder(String login) {
            user = new User(login);
        }

        public Builder buildUserState(UserState userState) {
            user.setUserState(userState);
            return this;
        }

        public Builder buildUserRole(UserRole userRole) {
            user.setUserRole(userRole);
            return this;
        }

        public Builder buildName(String name) {
            user.setName(name);
            return this;
        }

        public Builder buildSurname(String surname) {
            user.setSurname(surname);
            return this;
        }

        public Builder buildPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder buildEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder buildDateOfBirth(Date dateOfBirth) {
            user.setDateOfBirth(dateOfBirth);
            return this;
        }

        public Builder buildLogin(String login) {
            user.setLogin(login);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
