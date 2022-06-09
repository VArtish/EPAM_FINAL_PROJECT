package com.example.finalwebprojectepam.model.mapper;

import com.example.finalwebprojectepam.exception.DaoException;
import com.example.finalwebprojectepam.model.entity.AbstractEntity;

import java.sql.ResultSet;
import java.util.Optional;

public interface CustomRowMapper<T extends AbstractEntity> {
    T mapRow(ResultSet resultSet) throws DaoException;
}