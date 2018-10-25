package by.iba.markovsky.festivalorganisation.repository.impl.hibernate;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.model.entity.Artist;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.HibernateUtil;

import java.util.List;

public class DAOHIbernateArtist extends DAOHibernateAbstract<Artist> implements DAO<Artist> {

    @Override
    public Artist getById(int id) throws RepositoryException {
        Artist artist = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            artist = session.get(Artist.class, id);
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return artist;
    }

    @Override
    public List<Artist> getAll() throws RepositoryException {
        List<Artist> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("From Artist").list();
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
