package by.iba.markovsky.festivalorganisation.service;

import by.iba.markovsky.festivalorganisation.exception.LimitException;
import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.exception.ServiceException;
import by.iba.markovsky.festivalorganisation.model.entity.*;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.repository.impl.connection.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 *
 * EAGER type of data loading
 *
 **/

public class ActivityService {

    private DAO<Activity> activityDAO = new DAOActivity();
    private DAO<Artist> artistDAO = new DAOArtist();
    private DAO<Identity> identityDAO = new DAOIdentity();
    private DAO<Place> placeDAO = new DAOPlace();
    private DAO<WebIdentity> webIdentityDAO = new DAOWebIdentity();

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
                               String username, String password, String email, String telephone, boolean status) throws ServiceException {
        try {
            Identity identity = new Identity(name, surname, age);
            identityDAO.add(identity);
            WebIdentity webIdentity = new WebIdentity(username, password, email, telephone, status, identity);
            webIdentityDAO.add(webIdentity);
            activity.getUsers().add(webIdentity);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void addArtist(String name, String genre) throws ServiceException {
        try {
            Artist artist = new Artist(name, genre);
            artistDAO.add(artist);
            activity.getArtists().add(artist);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void addPlace(String address, int capacity) throws ServiceException {
        try {
            Place place = new Place(address, capacity);
            placeDAO.add(place);
            activity.setPlace(place);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void addActivityInfo(ActivityType activityType, String name, String description, Date date) {
        activity.setActivityType(activityType);
        activity.setName(name);
        activity.setDescription(description);
        activity.setDate(date);
    }

    public Activity getActivity() {
        return activity;
    }
    public List<Activity> getAllActivities() throws ServiceException {
        try {
            List<Activity> activities = activityDAO.getAll();
            for (Activity activity : activities) {
                activity.setPlace(placeDAO.getById(activity.getPlace().getId()));

                List<WebIdentity> webIdentities = webIdentityDAO.getByQuery("SELECT w.webIdentity_id,identity_id,username,password,email,telephone,status FROM WebIdentity w INNER JOIN Activity_has_WebIdentity a ON w.webIdentity_id=a.webIdentity_id");
                for (WebIdentity webIdentity : webIdentities) {
                    webIdentity.setIdentity(identityDAO.getById(webIdentity.getIdentity().getId()));
                }
                activity.setUsers(new HashSet<>(webIdentities));
                //TODO: Здесь также возможно добавить цикл для добавления списка мероприятий к участнику

                List<Artist> artists = artistDAO.getByQuery("SELECT a.artist_id,name,genre FROM Artist a INNER JOIN Activity_has_Artist aa ON a.artist_id=aa.artist_id");
                activity.setArtists(new HashSet<>(artists));
                //TODO: Здесь также возможно добавить цикл для добавления списка мероприятий к артисту
            }
            return activities;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
