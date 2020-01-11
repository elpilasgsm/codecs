package org.sfedu.codecs.service;

import org.sfedu.codecs.model.DBObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractCodecsService<T extends DBObject> {

    private final JpaRepository<T, Long> repository;

    public AbstractCodecsService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T getById(Long id) {
        return repository.getOne(id);
    }

    public List<T> findAllById(Iterable<Long> id) {
        return repository.findAllById(id);
    }

    public T save(T obj) {
        return repository.save(obj);
    }
}
