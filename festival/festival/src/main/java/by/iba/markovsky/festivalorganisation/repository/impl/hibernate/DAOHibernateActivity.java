package by.iba.markovsky.festivalorganisation.repository.impl.hibernate;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.model.entity.Activity;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.HibernateUtil;

import java.util.List;

public class DAOHibernateActivity extends DAOHibernateAbstract<Activity> implements DAO<Activity> {

    @Override
    public Activity getById(int id) throws RepositoryException {
        Activity activity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            activity = session.get(Activity.class, id);
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return activity;
    }

    @Override
    public List<Activity> getAll() throws RepositoryException {
        List<Activity> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("From Activity").list();
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
