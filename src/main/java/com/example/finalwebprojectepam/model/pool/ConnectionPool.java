package com.example.finalwebprojectepam.model.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private static final ReentrantLock lock = new ReentrantLock(true);
    private static ConnectionPool instance;
    private BlockingDeque<ProxyConnection> free = new LinkedBlockingDeque<ProxyConnection>(CONNECTION_SIZE);
    private BlockingDeque<ProxyConnection> used = new LinkedBlockingDeque<ProxyConnection>(CONNECTION_SIZE);
    private static final int CONNECTION_SIZE = 8;

    private ConnectionPool() {
        Connection connection = null;

        for (int i = 0; i < CONNECTION_SIZE; i++) {
            connection = ConnectionCreator.getConnection();
            free.add(new ProxyConnection(connection));
        }
        if (free.size() < CONNECTION_SIZE) {
            for (int i = free.size(); i < CONNECTION_SIZE; i++) {
                connection = ConnectionCreator.getConnection();
                free.add(new ProxyConnection(connection));
            }
        }
        if(free.size() < CONNECTION_SIZE) {
            throw new RuntimeException();
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        boolean isRemoved = false;
        if (connection instanceof ProxyConnection) {
            isRemoved = used.remove(connection);
            if (isRemoved) {
                try {
                    free.put((ProxyConnection) connection);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        return isRemoved;
    }

    public void destroyPool() {
        for (int i = 0; i < CONNECTION_SIZE; i++) {
            try {
                free.take().closeProxy();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch(InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        ConnectionCreator.deregisterDriver();
    }
}