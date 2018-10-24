package by.iba.markovsky.festivalorganisation.repository.impl.connection;

import by.iba.markovsky.festivalorganisation.exception.RepositoryException;
import by.iba.markovsky.festivalorganisation.repository.DAO;
import by.iba.markovsky.festivalorganisation.util.database.DatabaseUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAOAbstract<E> implements DAO<E> {

    protected PreparedStatement preparedStatement = null;

    protected void delete(String query, int id) throws RepositoryException {
        try {
            preparedStatement = DatabaseUtil.getStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

    protected int getGeneratedIndex() throws RepositoryException {
        try {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException sqle) {
            throw new RepositoryException(sqle.getMessage());
        }
    }

}
