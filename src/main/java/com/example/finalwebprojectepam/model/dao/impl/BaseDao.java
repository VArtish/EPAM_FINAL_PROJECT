package com.example.finalwebprojectepam.model.dao.impl;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends AbstractEntity, K> {
    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(K id) throws DaoException;
}
