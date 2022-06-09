package com.example.finalwebprojectepam.model.dao.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.dao.UserDao;
import com.example.finalwebprojectepam.model.entity.User;
import com.example.finalwebprojectepam.model.entity.type.UserRole;
import com.example.finalwebprojectepam.model.entity.type.UserState;
import com.example.finalwebprojectepam.model.mapper.impl.UserRowMapper;
import com.example.finalwebprojectepam.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.finalwebprojectepam.controller.ParameterName.USER_PASSWORD;

public class DefaultUserDao extends BaseDao<User, Long> implements UserDao {
    private static final int ONE_UPDATED = 1;
    private static final String SQL_SELECT_ALL_USER =
            "SELECT user_id, user_login, user_email, user_password, user_surname, user_name, user_date_of_birth, " +
                    "user_role_id, user_state_id, user_state, user_role FROM users " +
                    "INNER JOIN user_role ON user_role_id = urole_id " +
                    "INNER JOIN user_state ON user_state_id = ustate_id ";
    private static final String SQL_INSERT_USER =
            "INSERT INTO users (user_login, user_password, user_name, user_surname, user_email, " +
                    "user_date_of_birth, user_role_id, user_state_id) values(?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_USER_BY_LOGIN
            = SQL_SELECT_ALL_USER +
            "WHERE user_login = ?;";
    private static final String SQL_SELECT_USER_BY_EMAIL = SQL_SELECT_ALL_USER +
            "WHERE user_email = ?;";

    private static final String SQL_SELECT_USER_BY_ID = SQL_SELECT_ALL_USER +
            "WHERE user_id = ?;";
    private static final String SQL_DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE user_login = ?";
    private static final String SQL_SELECT_PASSWORD_BY_LOGIN = "SELECT user_password FROM users WHERE user_login = ?;";
    private static final String SQL_SELECT_PASSWORD_BY_ID = "SELECT user_password FROM users WHERE user_id = ?";
    private static final String SQL_UPDATE_USER_BY_ID = """
            UPDATE users SET user_name = (?), user_surname = (?), user_password = (?), user_email = (?),
            user_date_of_birth = (?), user_role_id = (?), user_state_id = (?), user_login = (?)
                    WHERE user_id = (?)""";
    private static final String SQL_UPDATE_PASSWORD_BY_ID = """
            UPDATE users SET user_password = (?) 
            WHERE user_id = (?)""";
    private static final String SQL_UPDATE_USER_ROLE_BY_LOGIN = """
            UPDATE users SET user_role_id = (?) 
            WHERE user_login = (?)""";
    private static final String SQL_UPDATE_USER_STATE_BY_LOGIN = """
            UPDATE users SET user_state_id = (?) 
            WHERE user_login = (?)""";

    @Override
    public List<User> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USER)) {

            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            UserRowMapper userRowMapper = UserRowMapper.getInstance();

            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = null;

            while (resultSet.next()) {
                user = UserRowMapper.getInstance().mapRow(resultSet);
            }

            return Optional.ofNullable(user);
        } catch (SQLException sqlException) {
            throw new DaoException("Adding user exception " + sqlException);
        }
    }

    @Override
    public boolean createClient(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER);) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setDate(6, user.getDateOfBirth());
            statement.setLong(7, user.getUserRole().getRoleId());
            statement.setLong(8, user.getUserState().getStateId());

            return statement.executeUpdate() == ONE_UPDATED;

        } catch (SQLException sqlException) {
            throw new DaoException("Adding user exception " + sqlException);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            User user = null;

            while (resultSet.next()) {
                user = UserRowMapper.getInstance().mapRow(resultSet);
            }

            return Optional.ofNullable(user);
        } catch (SQLException sqlException) {
            throw new DaoException("Adding user exception " + sqlException);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            User user = null;

            while (resultSet.next()) {
                user = UserRowMapper.getInstance().mapRow(resultSet);
            }

            return Optional.ofNullable(user);
        } catch (SQLException sqlException) {
            throw new DaoException("Adding user exception " + sqlException);
        }
    }

    @Override
    public Optional<String> findPasswordById(long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PASSWORD_BY_ID)) {

            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            String password = null;

            if (resultSet.next()) {
                password = resultSet.getString(USER_PASSWORD);
            }

            return Optional.ofNullable(password);

        } catch (SQLException e) {
            //logger
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }

    @Override
    public boolean updatePasswordById(String password, long userId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD_BY_ID)) {
            statement.setString(1, password);
            statement.setLong(2, userId);


            return statement.executeUpdate() == ONE_UPDATED;

        } catch (SQLException e) {
            //logger
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }

    @Override
    public boolean updateUserState(String login, UserState userState) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_STATE_BY_LOGIN)) {
            statement.setLong(1, userState.getStateId());
            statement.setString(2, login);

            return statement.executeUpdate() == ONE_UPDATED;

        } catch (SQLException e) {
            //logger
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }

    @Override
    public boolean deleteUser(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_LOGIN)) {
            statement.setString(1, login);

            return statement.executeUpdate() == ONE_UPDATED;
        } catch (SQLException e) {
            //logger
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }


    @Override
    public Optional<String> findPasswordByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PASSWORD_BY_LOGIN)) {

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            String password = null;

            if (resultSet.next()) {
                password = resultSet.getString(USER_PASSWORD);
            }

            return Optional.ofNullable(password);

        } catch (SQLException e) {
            //logger
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }


    @Override
    public boolean update(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_BY_ID)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setDate(5, user.getDateOfBirth());
            statement.setLong(6, user.getUserRole().getRoleId());
            statement.setLong(7, user.getUserState().getStateId());
            statement.setString(8, user.getLogin());
            statement.setLong(9, user.getId());


            return statement.executeUpdate() == ONE_UPDATED;

        } catch (SQLException e) {
            //logger
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }

    @Override
    public boolean updateUserRole(String login, UserRole userRole) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_ROLE_BY_LOGIN)) {
            statement.setLong(1, userRole.getRoleId());
            statement.setString(2, login);

            return statement.executeUpdate() == ONE_UPDATED;

        } catch (SQLException e) {
            //logger
            throw new DaoException("database access error occurred or error parsing resultSet", e);
        }
    }

}
