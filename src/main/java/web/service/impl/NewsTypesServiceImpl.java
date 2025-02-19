package web.service.impl;

import web.dao.DAOProvider;
import web.dao.DaoException;
import web.dao.NewsTypeDAO;
import web.service.NewsTypesService;
import web.service.ServiceException;

public class NewsTypesServiceImpl implements NewsTypesService {

    private final NewsTypeDAO newsTypesDAO = DAOProvider.getInstance().getNewsTypeDAO();

    @Override
    public int getIdByName(String name) throws ServiceException {
        try {
            return newsTypesDAO.getIdByName(name);
        } catch (DaoException e) {
            //logging
            throw new ServiceException("Failed to retrieve news id",e);
        }
    }

    @Override
    public String getNameById(int id) throws ServiceException {
        try {
            return newsTypesDAO.getNameById(id);
        } catch (DaoException e) {
            //logging
            throw new ServiceException("Failed to retrieve news name",e);
        }
    }

}
