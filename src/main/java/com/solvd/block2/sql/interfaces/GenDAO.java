package com.solvd.block2.sql.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface GenDAO<T> {
    T findById(int id) throws SQLException;

    List<T> findAll() throws SQLException;

    void create(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(T entity) throws SQLException;
}

