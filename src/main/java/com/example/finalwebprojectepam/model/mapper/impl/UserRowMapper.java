package com.example.finalwebprojectepam.model.mapper.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.entity.User;
import com.example.finalwebprojectepam.model.entity.type.UserRole;
import com.example.finalwebprojectepam.model.entity.type.UserState;
import com.example.finalwebprojectepam.model.mapper.CustomRowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.finalwebprojectepam.controller.ParameterName.*;

public class UserRowMapper implements CustomRowMapper<User> {
    private static UserRowMapper instance;

    private UserRowMapper() {
    }

    public static UserRowMapper getInstance() {
        if (instance == null) {
            instance = new UserRowMapper();
        }
        return instance;
    }

    @Override
    public User mapRow(ResultSet resultSet) throws DaoException {
        User user;
        try {
            String name = resultSet.getString(USER_NAME);
            String surname = resultSet.getString(USER_SURNAME);
            String password = resultSet.getString(USER_PASSWORD);
            String login = resultSet.getString(USER_LOGIN);
            String email = resultSet.getString(USER_EMAIL);
            Date dateOfBirth = Date.valueOf(resultSet.getString(USER_DATE_OF_BIRTH));
            UserRole userRole = UserRole.valueOf(resultSet.getString(USER_ROLE));
            UserState userState = UserState.valueOf(resultSet.getString(USER_STATE));
            long id = Integer.parseInt(resultSet.getString(USER_ID));
            user = new User.Builder(id, login).buildName(name).buildSurname(surname).buildPassword(password)
                    .buildUserRole(userRole).buildUserState(userState).buildEmail(email).buildDateOfBirth(dateOfBirth)
                    .build();

            return user;
        } catch (SQLException e) {
            //logger
            user = null;
        }

        return user;
    }
}