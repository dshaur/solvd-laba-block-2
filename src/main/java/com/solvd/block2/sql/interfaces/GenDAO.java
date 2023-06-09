package com.solvd.block2.sql.interfaces;

import java.util.List;

public interface GenDAO<T> {
    T findById(int id);

    List<T> findAll();

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}

