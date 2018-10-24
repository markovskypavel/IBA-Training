package by.iba.markovsky.festivalorganisation.repository.impl.hibernate;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.model.entity.Place;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.HibernateUtil;

import java.util.List;

public class DAOHibernatePlace extends DAOHibernateAbstract<Place> implements DAO<Place> {

    @Override
    public Place getById(int id) throws RepositoryException {
        Place place = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            place = session.get(Place.class, id);
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return place;
    }

    @Override
    public List<Place> getAll() throws RepositoryException {
        List<Place> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("From Place").list();
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
