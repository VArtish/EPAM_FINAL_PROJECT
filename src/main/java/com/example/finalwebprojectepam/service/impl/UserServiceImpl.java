package com.example.finalwebprojectepam.service.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.model.dao.UserDao;
import com.example.finalwebprojectepam.model.dao.impl.DefaultUserDao;
import com.example.finalwebprojectepam.model.entity.User;
import com.example.finalwebprojectepam.model.entity.type.UserRole;
import com.example.finalwebprojectepam.model.entity.type.UserState;
import com.example.finalwebprojectepam.service.UserService;
import com.example.finalwebprojectepam.util.PasswordEncryption;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.finalwebprojectepam.controller.ParameterName.*;
import static com.example.finalwebprojectepam.controller.PropertyKey.*;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }

        return instance;
    }

    @Override
    public Optional<User> logIn(Map<String, String> userData) throws ServiceException {
        UserDataResearcherImpl userDataResearcher = UserDataResearcherImpl.getInstance();
        if (!userDataResearcher.researchLoginData(userData)) {
            return Optional.empty();
        }

        UserDao userDao = new DefaultUserDao();
        String login = userData.get(USER_LOGIN);
        Optional<User> user = Optional.empty();
        try {
            Optional<String> passwordFromDb = userDao.findPasswordByLogin(login);
            if (passwordFromDb.isEmpty()) {
                userData.put(INCORRECT_SIGN_IN_DATA, INCORRECT_SIGN_IN_MESSAGE);
                return user;
            }
            String password = userData.get(USER_PASSWORD);
            password = PasswordEncryption.encryption(password);
            if (password.equals(passwordFromDb.get())) {
                user = userDao.findByLogin(login);
            } else {
                userData.put(INCORRECT_SIGN_IN_DATA, INCORRECT_SIGN_IN_MESSAGE);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public boolean registerUser(Map<String, String> userData) throws ServiceException {
        UserDataResearcherImpl userDataResearcher = UserDataResearcherImpl.getInstance();
        if (!userDataResearcher.researchRegisterData(userData)) {
            return false;
        }
        UserDao userDao = new DefaultUserDao();
        boolean result;
        try {
            Optional<User> checkUser = userDao.findByLogin(userData.get(USER_LOGIN));
            if (checkUser.isEmpty()) {
                User user = buildUser(userData);
                String password = user.getPassword();
                String encryptedPassword = PasswordEncryption.encryption(password);
                user.setPassword(encryptedPassword);
                result = userDao.createClient(user);
            } else {
                userData.put(INCORRECT_LOGIN, INCORRECT_LOGIN_MESSAGE);
                result = false;
            }
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }

        return result;
    }

    @Override
    public Optional<User> showUserCabinet(String login) throws ServiceException {
        DefaultUserDao userDao = new DefaultUserDao();
        try {
            Optional<User> user = userDao.findByLogin(login);
            return user;
        } catch (DaoException daoException) {
            // logger
            throw new ServiceException(daoException);
        }
    }


    @Override
    public Optional<User> updateUserProfile(Map<String, String> userUpdateData) throws ServiceException {
        UserDataResearcherImpl userDataResearcher = UserDataResearcherImpl.getInstance();
        if (!userDataResearcher.researchUserProfileData(userUpdateData)) {
            return Optional.empty();
        }

        DefaultUserDao userDao = new DefaultUserDao();
        String email = userUpdateData.get(USER_EMAIL);
        long userId = Long.parseLong(userUpdateData.get(USER_ID));
        String login = userUpdateData.get(USER_LOGIN);
        boolean validate = true;
        try {
            Optional<User> currentUser = userDao.findById(userId);
            userUpdateData.put(USER_PASSWORD, currentUser.get().getPassword());
            if (userDao.findByEmail(email).isPresent() && !currentUser.get().getEmail().equals(email)) {
                validate = false;
                userUpdateData.put(USER_EMAIL_EXIST, EMAIL_EXIST_MESSAGE);
            }
            if (userDao.findByLogin(login).isPresent() && !currentUser.get().getLogin().equals(login)) {
                validate = false;
                userUpdateData.put(USER_LOGIN_EXIST, LOGIN_EXIST_MESSAGE);
            }

            if (!validate) {
                return Optional.empty();
            }

            User updateUser = buildUser(userUpdateData);
            updateUser.setId(userId);
            return userDao.update(updateUser) ? Optional.of(updateUser) : Optional.empty();
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean changeUserPassword(Map<String, String> userData) throws ServiceException {
        UserDataResearcherImpl userDataResearcher = UserDataResearcherImpl.getInstance();
        if (!userDataResearcher.researchChangePasswordData(userData)) {
            return false;
        }

        long userId = Long.parseLong(userData.get(USER_ID));
        DefaultUserDao userDao = new DefaultUserDao();
        try {
            String oldPassword = userData.get(OLD_PASSWORD);
            Optional<String> optionalOldPassword = userDao.findPasswordById(userId);
            if (optionalOldPassword.get().equals(oldPassword)) {
                userData.put(INCORRECT_OLD_PASSWORD, INCORRECT_OLD_PASSWORD_MESSAGE);
                return false;
            }
            String newPassword = userData.get(NEW_PASSWORD);
            String encryptionNewPassword = PasswordEncryption.encryption(newPassword);

            return userDao.updatePasswordById(encryptionNewPassword, userId);
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<User> getUserList() throws ServiceException {
        DefaultUserDao userDao = new DefaultUserDao();
        try {
            List<User> users = userDao.findAll();
            return users;
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean updateUserRole(String login, UserRole userRole) throws ServiceException {
        DefaultUserDao userDao = new DefaultUserDao();
        try {
            return userDao.updateUserRole(login, userRole);
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean updateUserState(String login, UserState userState) throws ServiceException {
        DefaultUserDao userDao = new DefaultUserDao();
        try {
            return userDao.updateUserState(login, userState);
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean deleteUser(String login) throws ServiceException {
        DefaultUserDao userDao = new DefaultUserDao();
        try {
            return userDao.deleteUser(login);
        } catch (DaoException daoException) {
            throw new ServiceException(daoException);
        }
    }

    private User buildUser(Map<String, String> userData) {
        String login = userData.get(USER_LOGIN);
        String roleString = userData.get(USER_ROLE);
        UserRole role = UserRole.valueOf(roleString);
        String password = userData.get(USER_PASSWORD);
        String surname = userData.get(USER_SURNAME);
        String name = userData.get(USER_NAME);
        String email = userData.get(USER_EMAIL);
        Date date_of_birth = Date.valueOf(userData.get(USER_DATE_OF_BIRTH));
        String stateString = userData.get(USER_STATE);
        UserState state = UserState.valueOf(stateString);
        User.Builder builder = new User.Builder(login).
                buildSurname(surname).buildName(name).buildEmail(email).
                buildPassword(password).buildDateOfBirth(date_of_birth).
                buildUserRole(role).buildUserState(state);

        return builder.build();
    }
}
