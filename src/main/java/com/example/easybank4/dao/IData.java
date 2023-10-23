package com.example.easybank4.dao;

import java.util.List;
import java.util.Optional;

public interface IData<E, ID> {
    Optional<E> save(E entity);
    Optional<E> update(E entity);
    Optional<E> findById(ID id);
    Optional<E> find(ID id);
    boolean delete(ID id);
    List<E> findAll();
    boolean deleteAll();
}
