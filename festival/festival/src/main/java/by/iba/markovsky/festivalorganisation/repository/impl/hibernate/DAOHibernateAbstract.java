package by.iba.markovsky.festivalorganisation.repository.impl.hibernate;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.HibernateUtil;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * Hibernate DAO implements
 *@variable preparedStatement is for simple DB actions
 *
 **/

public abstract class DAOHibernateAbstract<E> implements DAO<E> {

    protected Session session = null;

    @Override
    public void add(E obj) throws RepositoryException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(E obj) throws RepositoryException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(E obj) throws RepositoryException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<E> getByQuery(String query) throws RepositoryException {
        List<E> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery(query).list();
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

}
