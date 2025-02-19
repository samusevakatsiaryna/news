package web.service.impl;

import web.bean.News;
import web.dao.DAOProvider;
import web.dao.DaoException;
import web.dao.NewsDAO;
import web.service.NewsService;
import web.service.ServiceException;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private final NewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();

    @Override
    public List<News> getAllNews() throws ServiceException {
        try {
            return newsDAO.getAllNews();
        } catch (DaoException e) {
            throw new ServiceException("Get all news Error", e);
        }
    }

    @Override
    public void addNews(News news) throws ServiceException {
        try {
            newsDAO.addNews(news);
        } catch (DaoException e) {
            throw new ServiceException("Add news Error", e);
        }
    }

    @Override
    public boolean deleteNewsById(int id) throws ServiceException {
        try {
            return newsDAO.deleteNewsById(id);
        } catch (DaoException e) {
            throw new ServiceException("Delete News Error", e);
        }
    }

    @Override
    public void updateNews(int newsId, String title, String content) throws ServiceException {
        try {
            newsDAO.updateNews(newsId, title, content);
        } catch (DaoException e) {
            e.printStackTrace(); // Печатает весь стек ошибки
            throw new ServiceException("Error updating news", e);
        }
    }
}
