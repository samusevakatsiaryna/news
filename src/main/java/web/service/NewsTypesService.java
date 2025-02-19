package web.service;

public interface NewsTypesService {
    int getIdByName(String name) throws ServiceException;

    String getNameById(int id) throws ServiceException;
}
