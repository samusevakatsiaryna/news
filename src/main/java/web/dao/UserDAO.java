package web.dao;

import web.model.User;

public interface UserDAO {

    User findByEmail(String email) throws DaoException;

}
