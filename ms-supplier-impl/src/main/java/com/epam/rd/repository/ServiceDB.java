package com.epam.rd.repository;

import java.util.List;
import java.util.UUID;

public interface ServiceDB<T> {
    //public void init();
    public List<T> getAll();

    public UUID create();

    public T read(UUID uuid);

    public void update(T t);

    public void delete(UUID uuid);
}
