package by.wt.lab4.repository;

import java.util.List;

import by.wt.lab4.entity.Entity;
import by.wt.lab4.exception.RepositoryException;
import by.wt.lab4.specification.Specification;

public interface Repository<T extends Entity> {

    boolean add(T entity) throws RepositoryException;

    boolean remove(T entity) throws RepositoryException;

    boolean update(T entity) throws RepositoryException;

    List<T> query(Specification specification) throws RepositoryException;

}
