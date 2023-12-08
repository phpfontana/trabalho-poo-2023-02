package org.example.dao.interfaceDao;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudDao<T> {
    void insert(T entity);
    Optional<T> selectById(UUID id);
    List<T> selectAll();
    boolean deleteById(UUID id);
    boolean updateById(UUID id, T entity);
}