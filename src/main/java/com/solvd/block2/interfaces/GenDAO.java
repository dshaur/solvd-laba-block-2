package com.solvd.block2.interfaces;

import java.util.List;

public interface GenDAO<T> {
    T findById(int id);

    List<T> findAll();

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}

