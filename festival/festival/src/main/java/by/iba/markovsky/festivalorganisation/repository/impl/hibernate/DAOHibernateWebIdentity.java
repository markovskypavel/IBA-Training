package by.iba.markovsky.festivalorganisation.repository.impl.hibernate;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.model.entity.WebIdentity;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.HibernateUtil;

import java.util.List;

public class DAOHibernateWebIdentity extends DAOHibernateAbstract<WebIdentity> implements DAO<WebIdentity> {

    @Override
    public WebIdentity getById(int id) throws RepositoryException {
        WebIdentity webIdentity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            webIdentity = session.get(WebIdentity.class, id);
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return webIdentity;
    }

    @Override
    public List<WebIdentity> getAll() throws RepositoryException {
        List<WebIdentity> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("From WebIdentity").list();
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
