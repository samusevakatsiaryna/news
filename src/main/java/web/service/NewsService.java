package web.service;

import web.bean.News;

import java.util.List;

public interface NewsService {

    List<News> getAllNews() throws ServiceException;
    void addNews(News news) throws ServiceException;
    boolean deleteNewsById(int id) throws ServiceException;
    void updateNews(int newsId, String title, String content) throws ServiceException;
}

