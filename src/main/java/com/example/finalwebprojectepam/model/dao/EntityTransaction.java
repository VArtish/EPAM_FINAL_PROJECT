package com.example.finalwebprojectepam.model.dao;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction implements AutoCloseable {
    private Connection connection;

    public void begin() throws DaoException {
        initConnection(false);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void beginWithAutoCommit() throws DaoException {
        initConnection(true);
    }

    @Override
    public void close() {
        end();
    }

    public void end() {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                //logger
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.releaseConnection(connection);
                connection = null;
            }
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Exception when commit transaction", e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            //logger
        }
    }

    private void initConnection(boolean autoCommit) throws DaoException {
        if (connection == null) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
        }
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new DaoException("Exception when setting autoCommit in Connection. Value=" + autoCommit, e);
        }
    }
}