package web.dao.impl;

import web.bean.News;
import web.dao.DaoException;
import web.dao.NewsDAO;
import web.util.ConnectionPool;
import web.util.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsSQL implements NewsDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ADD_NEWS_QUERY = "INSERT INTO news (title, content, news_type_id) VALUES (?, ?, ?)";
    private static final String GET_NEWS_TYPE_QUERY = "SELECT id FROM news_types WHERE type_name = ?";
    private static final String UPDATE_NEWS_QUERY = "UPDATE news SET title=?, content=? WHERE id=?";

    @Override
    public List<News> getAllNews() throws DaoException {

        List<News> newsList = new ArrayList<>();
        String QUERY_SELECT_ALL_NEWS =
                "SELECT n.id, n.title, n.content, nt.type_name FROM news n " +
                        "INNER JOIN news_types nt ON n.news_type_id = nt.id";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(QUERY_SELECT_ALL_NEWS);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int newsId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String typeName = resultSet.getString("type_name");

                if (typeName == null) {
                    System.err.println("❌ [NewsSQL] Ошибка: Тип новости 'null' найден в БД!");
                } else {
                    newsList.add(new News(newsId, title, content, typeName));
                }
            }

            return newsList;

        } catch (SQLException e) {
            throw new DaoException("Error in work with data", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Connection Pool exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public void addNews(News news) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement stmt = connection.prepareStatement(ADD_NEWS_QUERY)) {
                statement = stmt;
                statement.setString(1, news.getTitle());
                statement.setString(2, news.getContent());
                statement.setInt(3, news.getTypeId());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DaoException("Error in work adding news", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Connection Pool exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public boolean deleteNewsById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement("DELETE FROM news WHERE id = ?");

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new DaoException("Error in work with delete news", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Connection Pool exception", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void updateNews(int newsId, String title, String content) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(UPDATE_NEWS_QUERY);
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setInt(3, newsId);

            int rowsUpdated = statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Error of News update", e);

        } catch (ConnectionPoolException e) {
            throw new DaoException("Error of Connection Pool", e);

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("⚠ PreparedStatement: " + e.getMessage());
            }

            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }
}
