package by.iba.markovsky.festivalorganisation.repository.impl.connection;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.model.entity.Activity;
import by.iba.markovsky.festivalorganisation.model.entity.ActivityType;
import by.iba.markovsky.festivalorganisation.model.entity.Artist;
import by.iba.markovsky.festivalorganisation.model.entity.WebIdentity;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.DatabaseUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.iba.markovsky.festivalorganisation.constant.TableCol.*;

public class DAOActivity extends DAOAbstract<Activity> implements DAO<Activity> {

    private static final String INSERT_HASWEBIDENTITY = "INSERT INTO Activity_has_WebIdentity(activity_id,webIdentity_id) VALUES(?,?)";
    private static final String DELETE_HASWEBIDENTITY = "DELETE FROM Activity_has_WebIdentity WHERE activity_id=?";
    private static final String INSERT_HASARTIST = "INSERT INTO Activity_has_Artist(activity_id,artist_id) VALUES(?,?)";
    private static final String DELETE_HASARTIST = "DELETE FROM Activity_has_Artist WHERE activity_id=?";

    private static final String INSERT = "INSERT INTO Activity(place_id,activityType,name,date,description) VALUES(?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM Activity WHERE activity_id=?";
    private static final String UPDATE = "UPDATE Activity SET place_id=?,activityType=?,name=?,date=?,description=? WHERE activity_id=?;";
    private static final String GET_ALL = "SELECT activity_id,place_id,activityType,name,date,description FROM Activity";
    private static final String GET_BY_ID = "SELECT activity_id,place_id,activityType,name,date,description FROM Activity WHERE activity_id=?";

    private void insertHasTable(Activity obj, int lastInsertIndex) throws RepositoryException {
        try {
            for (WebIdentity webIdentity : obj.getUsers()) {
                preparedStatement = DatabaseUtil.getStatement(INSERT_HASWEBIDENTITY);
                preparedStatement.setInt(1, lastInsertIndex);
                preparedStatement.setInt(2, webIdentity.getId());
                preparedStatement.executeUpdate();
            }
            for (Artist artist : obj.getArtists()) {
                preparedStatement = DatabaseUtil.getStatement(INSERT_HASARTIST);
                preparedStatement.setInt(1, lastInsertIndex);
                preparedStatement.setInt(2, artist.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }
    private void deleteFromHasTable(Activity obj) throws RepositoryException {
        super.delete(DELETE_HASARTIST, obj.getId());
        super.delete(DELETE_HASWEBIDENTITY, obj.getId());
    }

    @Override
    public void add(Activity obj) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(INSERT);
            preparedStatement.setInt(1, obj.getPlace().getId());
            preparedStatement.setString(2, obj.getActivityType().name());
            preparedStatement.setString(3, obj.getName());
            preparedStatement.setDate(4, new Date(obj.getDate().getTime()));
            preparedStatement.setString(5, obj.getDescription());
            preparedStatement.executeUpdate();

            obj.setId(getGeneratedIndex());
            insertHasTable(obj, obj.getId());
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    @Override
    public void update(Activity obj) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(UPDATE);
            preparedStatement.setInt(1, obj.getPlace().getId());
            preparedStatement.setString(2, obj.getActivityType().name());
            preparedStatement.setString(3, obj.getName());
            preparedStatement.setDate(4, new Date(obj.getDate().getTime()));
            preparedStatement.setString(5, obj.getDescription());
            preparedStatement.setInt(6, obj.getId());
            preparedStatement.executeUpdate();

            deleteFromHasTable(obj);
            insertHasTable(obj, obj.getId());
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    @Override
    public void delete(Activity obj) throws RepositoryException {
        deleteFromHasTable(obj);
        super.delete(DELETE, obj.getId());
    }

    @Override
    public List<Activity> getByQuery(String query) throws RepositoryException {
        List<Activity> activities = new ArrayList<>();
        try {
            preparedStatement = DatabaseUtil.getStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                activities.add(new Activity(resultSet.getInt(ACTIVITY_ID_COL), ActivityType.valueOf(resultSet.getString(ACTIVITYTYPE_COL)),
                        resultSet.getString(NAME_ACTIVITY_COL), resultSet.getInt(PLACE_ID_COL),
                        resultSet.getString(DESCRIPTION_COL), resultSet.getDate(DATE_COL)));
            }

        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
        return activities;
    }

    @Override
    public Activity getById(int id) throws RepositoryException {
        Activity activity = null;
        try {
            preparedStatement = DatabaseUtil.getStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                activity = new Activity(resultSet.getInt(ACTIVITY_ID_COL), ActivityType.valueOf(resultSet.getString(ACTIVITYTYPE_COL)),
                        resultSet.getString(NAME_ACTIVITY_COL), resultSet.getInt(PLACE_ID_COL),
                        resultSet.getString(DESCRIPTION_COL), resultSet.getDate(DATE_COL));
            }
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
        return activity;
    }

    @Override
    public List<Activity> getAll() throws RepositoryException {
        return getByQuery(GET_ALL);
    }

}
