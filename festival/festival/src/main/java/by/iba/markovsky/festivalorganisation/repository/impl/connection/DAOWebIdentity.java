package by.iba.markovsky.festivalorganisation.repository.impl.connection;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.model.entity.WebIdentity;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.iba.markovsky.festivalorganisation.constant.TableCol.*;

public class DAOWebIdentity extends DAOAbstract<WebIdentity> implements DAO<WebIdentity> {

    private static final String INSERT = "INSERT INTO WebIdentity(identity_id,username,password,email,telephone,status) VALUES(?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM WebIdentity WHERE webIdentity_id=?";
    private static final String UPDATE = "UPDATE WebIdentity SET identity_id=?,username=?,password=?,email=?,telephone=?,status=? WHERE webIdentity_id=?;";
    private static final String GET_ALL = "SELECT webIdentity_id,identity_id,username,password,email,telephone,status FROM WebIdentity";
    private static final String GET_BY_ID = "SELECT webIdentity_id,identity_id,username,password,email,telephone,status FROM WebIdentity WHERE webIdentity_id=?";

    @Override
    public void add(WebIdentity obj) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(INSERT);
            preparedStatement.setInt(1, obj.getIdentity().getId());
            preparedStatement.setString(2, obj.getUsername());
            preparedStatement.setString(3, obj.getPassword());
            preparedStatement.setString(4, obj.getEmail());
            preparedStatement.setString(5, obj.getTelephone());
            preparedStatement.setBoolean(6, obj.isStatus());
            preparedStatement.executeUpdate();

            obj.setId(getGeneratedIndex());
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    @Override
    public void update(WebIdentity obj) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(UPDATE);
            preparedStatement.setInt(1, obj.getIdentity().getId());
            preparedStatement.setString(2, obj.getUsername());
            preparedStatement.setString(3, obj.getPassword());
            preparedStatement.setString(4, obj.getEmail());
            preparedStatement.setString(5, obj.getTelephone());
            preparedStatement.setBoolean(6, obj.isStatus());
            preparedStatement.setInt(7, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    @Override
    public void delete(WebIdentity obj) throws RepositoryException {
        super.delete(DELETE, obj.getId());
    }

    @Override
    public List<WebIdentity> getByQuery(String query) throws RepositoryException {
        List<WebIdentity> webIdentities = new ArrayList<>();
        try {
            preparedStatement = DatabaseUtil.getStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                webIdentities.add(new WebIdentity(resultSet.getInt(WEBIDENTITY_ID_COL), resultSet.getInt(IDENTITY_ID_COL), resultSet.getString(USERNAME_COL), resultSet.getString(PASSWORD_COL),
                        resultSet.getString(EMAIL_COL), resultSet.getString(TELEPHONE_COL), resultSet.getBoolean(STATUS_COL)));
            }

        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
        return webIdentities;
    }

    @Override
    public WebIdentity getById(int id) throws RepositoryException {
        WebIdentity webIdentity = null;
        try {
            preparedStatement = DatabaseUtil.getStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                webIdentity = new WebIdentity(resultSet.getInt(WEBIDENTITY_ID_COL), resultSet.getInt(IDENTITY_ID_COL), resultSet.getString(USERNAME_COL), resultSet.getString(PASSWORD_COL),
                        resultSet.getString(EMAIL_COL), resultSet.getString(TELEPHONE_COL), resultSet.getBoolean(STATUS_COL));
            }
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
        return webIdentity;
    }

    @Override
    public List<WebIdentity> getAll() throws RepositoryException {
        return getByQuery(GET_ALL);
    }

}
