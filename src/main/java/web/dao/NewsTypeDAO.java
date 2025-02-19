package web.dao;

public interface NewsTypeDAO {

    int getIdByName(String name) throws DaoException;
    String getNameById(int id) throws DaoException;

}
