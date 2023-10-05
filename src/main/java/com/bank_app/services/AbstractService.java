package com.bank_app.services;

import java.util.List;

public interface AbstractService<T> {

    Integer Save(T dto);

    List<T> findAll();

    T findById(Integer id);

    void delete(Integer id);
}
