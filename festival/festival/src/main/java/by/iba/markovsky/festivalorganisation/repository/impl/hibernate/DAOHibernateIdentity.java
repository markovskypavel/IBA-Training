package by.iba.markovsky.festivalorganisation.repository.impl.hibernate;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.model.entity.Identity;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.HibernateUtil;

import java.util.List;

public class DAOHibernateIdentity extends DAOHibernateAbstract<Identity> implements DAO<Identity> {

    @Override
    public Identity getById(int id) throws RepositoryException {
        Identity identity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            identity = session.get(Identity.class, id);
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return identity;
    }

    @Override
    public List<Identity> getAll() throws RepositoryException {
        List<Identity> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("From Identity").list();
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
