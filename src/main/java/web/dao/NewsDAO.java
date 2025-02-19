package web.dao;

import web.bean.News;

import java.util.List;

public interface NewsDAO {

    List<News> getAllNews() throws DaoException;
    void addNews(News news) throws DaoException;
    boolean deleteNewsById(int id) throws DaoException;
    void updateNews(int newsId, String title, String content) throws DaoException;
}
