package web.service;

import web.service.impl.AuthServiceImpl;
import web.service.impl.NewsServiceImpl;
import web.service.impl.NewsTypesServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final AuthService authService = new AuthServiceImpl();

    private final NewsService newsService = new NewsServiceImpl();

    private final NewsTypesService newsTypesService = new NewsTypesServiceImpl();

    public ServiceProvider() {
        // TODO document why this constructor is empty
    }
    public AuthService getAuthService() {
        return authService;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public NewsTypesService getNewsTypeService() {
        return newsTypesService;
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

}
