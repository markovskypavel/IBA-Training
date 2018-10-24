package by.iba.markovsky.festivalorganisation.repository.impl.connection;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.model.entity.Place;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.iba.markovsky.festivalorganisation.constant.TableCol.*;

public class DAOPlace extends DAOAbstract<Place> implements DAO<Place> {

    private static final String INSERT = "INSERT INTO Place(address,capacity) VALUES(?,?)";
    private static final String DELETE = "DELETE FROM Place WHERE place_id=?";
    private static final String UPDATE = "UPDATE Place SET address=?,capacity=? WHERE place_id=?;";
    private static final String GET_ALL = "SELECT place_id,address,capacity FROM Place";
    private static final String GET_BY_ID = "SELECT place_id,address,capacity FROM Place WHERE place_id=?";

    @Override
    public void add(Place obj) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(INSERT);
            preparedStatement.setString(1, obj.getAddress());
            preparedStatement.setInt(2, obj.getCapacity());
            preparedStatement.executeUpdate();

            obj.setId(getGeneratedIndex());
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    @Override
    public void update(Place obj) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(UPDATE);
            preparedStatement.setString(1, obj.getAddress());
            preparedStatement.setInt(2, obj.getCapacity());
            preparedStatement.setInt(3, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    @Override
    public void delete(Place obj) throws RepositoryException {
        super.delete(DELETE, obj.getId());
    }

    @Override
    public List<Place> getByQuery(String query) throws RepositoryException {
        List<Place> places = new ArrayList<>();
        try {
            preparedStatement = DatabaseUtil.getStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                places.add(new Place(resultSet.getInt(PLACE_ID_COL), resultSet.getString(ADDRESS_COL), resultSet.getInt(CAPACITY_COL)));
            }
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
        return places;
    }

    @Override
    public Place getById(int id) throws RepositoryException {
        Place place = null;
        try {
            preparedStatement = DatabaseUtil.getStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                place = new Place(resultSet.getInt(PLACE_ID_COL), resultSet.getString(ADDRESS_COL), resultSet.getInt(CAPACITY_COL));
            }
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
        return place;
    }

    @Override
    public List<Place> getAll() throws RepositoryException {
        return getByQuery(GET_ALL);
    }

}
