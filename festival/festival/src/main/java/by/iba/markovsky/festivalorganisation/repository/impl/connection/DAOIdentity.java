package by.iba.markovsky.festivalorganisation.repository.impl.connection;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.model.entity.Identity;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.iba.markovsky.festivalorganisation.constant.TableCol.*;

public class DAOIdentity extends DAOAbstract<Identity> implements DAO<Identity> {

    private static final String INSERT = "INSERT INTO Identity(name,surname,age) VALUES(?,?,?)";
    private static final String DELETE = "DELETE FROM Identity WHERE identity_id=?";
    private static final String UPDATE = "UPDATE Identity SET name=?,surname=?,age=? WHERE identity_id=?;";
    private static final String GET_ALL = "SELECT identity_id,name,surname,age FROM Identity";
    private static final String GET_BY_ID = "SELECT identity_id,name,surname,age FROM Identity WHERE identity_id=?";

    @Override
    public void add(Identity obj) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(INSERT);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getSurname());
            preparedStatement.setInt(3, obj.getAge());
            preparedStatement.executeUpdate();

            obj.setId(getGeneratedIndex());
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    @Override
    public void update(Identity obj) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(UPDATE);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getSurname());
            preparedStatement.setInt(3, obj.getAge());
            preparedStatement.setInt(4, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    @Override
    public void delete(Identity obj) throws RepositoryException {
        super.delete(DELETE, obj.getId());
    }

    @Override
    public List<Identity> getByQuery(String query) throws RepositoryException {
        List<Identity> identities = new ArrayList<>();
        try {
            preparedStatement = DatabaseUtil.getStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                identities.add(new Identity(resultSet.getInt(IDENTITY_ID_COL), resultSet.getString(NAME_IDENTITY_COL), resultSet.getString(SURNAME_COL), resultSet.getInt(AGE_COL)));
            }
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
        return identities;
    }

    @Override
    public Identity getById(int id) throws RepositoryException {
        Identity identity = null;
        try {
            preparedStatement = DatabaseUtil.getStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                identity = new Identity(resultSet.getInt(IDENTITY_ID_COL), resultSet.getString(NAME_IDENTITY_COL), resultSet.getString(SURNAME_COL), resultSet.getInt(AGE_COL));
            }
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
        return identity;
    }

    @Override
    public List<Identity> getAll() throws RepositoryException {
        return getByQuery(GET_ALL);
    }

}
