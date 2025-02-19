package web.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DBResourceManager {

    private static final DBResourceManager instance = new DBResourceManager();
    //ResourceBundle jdbcProperties = ResourceBundle.getBundle("resource.db");
    private ResourceBundle resourceBundle;

    private DBResourceManager() {
        try {
            resourceBundle = ResourceBundle.getBundle("db");
        } catch (MissingResourceException e) {
            throw new RuntimeException("File db.properties is not found!", e);
        }
    }

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String propertyKey) {
        return resourceBundle.getString(propertyKey);
    }

}