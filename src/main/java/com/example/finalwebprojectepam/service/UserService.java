package com.example.finalwebprojectepam.service;

import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.entity.User;
import com.example.finalwebprojectepam.model.entity.type.UserRole;
import com.example.finalwebprojectepam.model.entity.type.UserState;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    boolean registerUser(Map<String, String> userData) throws ServiceException;

    Optional<User> logIn(Map<String, String> userData) throws ServiceException;

    Optional<User> showUserCabinet(String login) throws ServiceException;

    Optional<User> updateUserProfile(Map<String, String> userUpdateData) throws ServiceException;

    boolean changeUserPassword(Map<String, String> userData) throws ServiceException;

    List<User> getUserList() throws ServiceException;

    boolean updateUserRole(String login, UserRole userRole) throws ServiceException;

    boolean updateUserState(String login, UserState userState) throws ServiceException;

    boolean deleteUser(String login) throws ServiceException;
}
