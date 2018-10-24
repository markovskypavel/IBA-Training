package by.iba.markovsky.festivalorganisation.repository;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;

import java.util.List;

public interface DAO<E> {
    void add(E obj) throws RepositoryException;
    void update(E obj) throws RepositoryException;
    void delete(E obj) throws RepositoryException;
    E getById(int id) throws RepositoryException;
    List<E> getAll() throws RepositoryException;
    List<E> getByQuery(String query) throws RepositoryException;
}
