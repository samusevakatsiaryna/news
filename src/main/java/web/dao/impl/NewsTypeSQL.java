package web.dao.impl;

import web.dao.DaoException;
import web.dao.NewsTypeDAO;
import web.util.ConnectionPool;
import web.util.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsTypeSQL implements NewsTypeDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SELECT_ID_BY_NAME = "SELECT id FROM news_types WHERE type_name = ?";
    private static final String SELECT_NAME_BY_ID = "SELECT type_name FROM news_types WHERE id = ?";

    @Override
    public int getIdByName(String name) throws DaoException {
        int typeId = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement stmt = connection.prepareStatement(SELECT_ID_BY_NAME)) {
                statement = stmt;
                statement.setString(1, name);
                resultSet = statement.executeQuery();
                System.out.println("📌 [NewsTypeDAO] SQL-запрос выполнен, обрабатываем результат...");

                if (resultSet.next()) {
                    typeId = resultSet.getInt("id");
                } else {
                    System.err.println("❌ [NewsTypeDAO] News type '" + name + "' не найден в БД!");
                }
            }
            return typeId;

        } catch (SQLException e) {
            throw new DaoException("Cannot find name", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Error with Connection pool", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public String getNameById(int id) throws DaoException {
        String typeName = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement stmt = connection.prepareStatement(SELECT_NAME_BY_ID)) {
                statement = stmt;
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    typeName = resultSet.getString("type_name");
                } else {
                    System.err.println("❌ [NewsTypeDAO] Категория с ID " + id + " не найдена в БД!");
                }
            }

           return typeName;

        } catch (SQLException e) {
            throw new DaoException("Cannot find name", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Error with Connection pool", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }
}
