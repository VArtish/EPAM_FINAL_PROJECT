package com.example.finalwebprojectepam.model.dao;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.entity.User;

import java.util.Optional;

public interface UserDao {
    boolean createClient(User user) throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    Optional<String> findPasswordByLogin(String login) throws DaoException;

    boolean update(User user) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;

    Optional<String> findPasswordById(long userId) throws DaoException;

    boolean updatePasswordById(String password, long userId) throws DaoException;

}
