package web.dao.impl;

import web.dao.DaoException;
import web.dao.UserDAO;
import web.model.User;
import web.util.ConnectionPool;
import web.util.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSQL implements UserDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String QUERY_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    @Override
    public User findByEmail(String email) throws DaoException {

        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement stmt = connection.prepareStatement(QUERY_SELECT_USER_BY_EMAIL)) {
                statement = stmt;
                statement.setString(1, email);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getInt("role_id")
                    );
               } else {
                    System.err.println("❌ [UserSQL] Пользователь с email " + email + " не найден.");
                }
            }

        } catch (SQLException e) {
            throw new DaoException("Cannot find email", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Error with Connection pool", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return user;
    }
}
