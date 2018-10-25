package by.iba.markovsky.festivalorganisation.service;

import by.iba.markovsky.festivalorganisation.exception.LimitException;
import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.exception.ServiceException;
import by.iba.markovsky.festivalorganisation.model.entity.*;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.repository.impl.connection.DAOActivity;
import by.iba.markovsky.festivalorganisation.repository.impl.hibernate.DAOHibernateActivity;

import java.util.Date;
import java.util.List;

public class ActivityHibernateService {

    private DAO<Activity> activityDAO = new DAOHibernateActivity();
    private Activity activity = new Activity();

    public void addAdctivityToDB() throws LimitException, ServiceException {
        if (activity.getPlace().getCapacity() < activity.getUsers().size()) {
            throw new LimitException("Capacity is less than participants quantity");
        }
        try {
            activityDAO.add(activity);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void addParticipant(String name, String surname, int age,
                               String username, String password, String email, String telephone, boolean status) {
        Identity identity = new Identity(name, surname, age);
        WebIdentity webIdentity = new WebIdentity(username, password, email, telephone, status, identity);
        activity.getUsers().add(webIdentity);
    }
    public void addArtist(String name, String genre) {
        Artist artist = new Artist(name, genre);
        activity.getArtists().add(artist);
    }
    public void addPlace(String address, int capacity) {
        Place place = new Place(address, capacity);
        activity.setPlace(place);
    }
    public void addActivityInfo(ActivityType activityType, String name, String description, Date date) {
        activity.setActivityType(activityType);
        activity.setName(name);
        activity.setDescription(description);
        activity.setDate(date);
    }

    public void deleteActivity(int id) throws ServiceException {
        try {
            activity = activityDAO.getById(id);
            activityDAO.delete(activity);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public Activity getActivity() {
        return activity;
    }
    public List<Activity> getAllActivities() throws ServiceException {
        try {
            return activityDAO.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
