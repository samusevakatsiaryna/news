package web.dao;

import web.bean.News;
import web.dao.impl.NewsSQL;
import web.dao.impl.NewsTypeSQL;
import web.dao.impl.SessionSQL;
import web.dao.impl.UserSQL;

import java.util.List;

public class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();

    private final NewsDAO news = new NewsSQL();
    private final UserDAO user = new UserSQL();
    private final SessionDAO session = new SessionSQL();
    private final NewsTypeDAO newsTypeDAO = new NewsTypeSQL();

    private DAOProvider() {

    }

    public UserDAO getUser() {
        return user;
    }

    public SessionDAO getSession() {
        return session;
    }

    public NewsDAO getNewsDAO() {
        return news;
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public NewsTypeDAO getNewsTypeDAO() {
        return newsTypeDAO;
    }

}
