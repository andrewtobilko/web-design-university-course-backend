package com.tobilko.web.repository;

import com.tobilko.web.entity.Feedback;

import java.io.Serializable;
import java.util.List;

public interface Repository<T extends Serializable> {

    void save(T t);
    List<Feedback> getAll();

}
