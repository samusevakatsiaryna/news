package web.service.impl;

import web.bean.Session;
import web.dao.DAOProvider;
import web.dao.DaoException;
import web.dao.UserDAO;
import web.model.User;
import web.service.AuthService;
import web.service.ServiceException;

public class AuthServiceImpl implements AuthService {

    private final UserDAO userDAO = DAOProvider.getInstance().getUser();

    @Override
    public Session login() {
        return null;
    }

    @Override
    public User authenticate(String email, String password) throws ServiceException {
        try {
            User user = userDAO.findByEmail(email);

            if (user.getPassword().equals(password)) {
                return user;
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException("Auth Error", e);
        }
    }
}
