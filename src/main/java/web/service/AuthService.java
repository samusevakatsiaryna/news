package web.service;

import web.bean.Session;
import web.model.User;

public interface AuthService {
    Session login();
    boolean register = false;

    User authenticate(String email, String password) throws ServiceException;

}
