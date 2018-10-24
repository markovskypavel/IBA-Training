package by.iba.markovsky.festivalorganisation.repository.impl.connection;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.model.entity.Activity;
import by.iba.markovsky.festivalorganisation.model.entity.Artist;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.iba.markovsky.festivalorganisation.constant.TableCol.*;

public class DAOArtist extends DAOAbstract<Artist> implements DAO<Artist> {

    private static final String INSERT = "INSERT INTO Artist(name,genre) VALUES(?,?)";
    private static final String DELETE = "DELETE FROM Artist WHERE artist_id=?";
    private static final String UPDATE = "UPDATE Artist SET name=?,genre=? WHERE artist_id=?;";
    private static final String GET_ALL = "SELECT artist_id,name,genre FROM Artist";
    private static final String GET_BY_ID = "SELECT artist_id,name,genre FROM Artist WHERE artist_id=?";

    @Override
    public void add(Artist obj) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(INSERT);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getGenre());
            preparedStatement.executeUpdate();

            obj.setId(getGeneratedIndex());
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    @Override
    public void update(Artist obj) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(UPDATE);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getGenre());
            preparedStatement.setInt(3, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    @Override
    public void delete(Artist obj) throws RepositoryException {
        super.delete(DELETE, obj.getId());
    }

    @Override
    public List<Artist> getByQuery(String query) throws RepositoryException {
        List<Artist> artists = new ArrayList<>();
        try {
            preparedStatement = DatabaseUtil.getStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                artists.add(new Artist(resultSet.getInt(ARTIST_ID_COL), resultSet.getString(NAME_ARTIST_COL), resultSet.getString(GENRE_COL)));
            }
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
        return artists;
    }

    @Override
    public Artist getById(int id) throws RepositoryException {
        Artist artist = null;
        try {
            preparedStatement = DatabaseUtil.getStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                artist = new Artist(resultSet.getInt(ARTIST_ID_COL), resultSet.getString(NAME_ARTIST_COL), resultSet.getString(GENRE_COL));
            }
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
        return artist;
    }

    @Override
    public List<Artist> getAll() throws RepositoryException {
        return getByQuery(GET_ALL);
    }

}
