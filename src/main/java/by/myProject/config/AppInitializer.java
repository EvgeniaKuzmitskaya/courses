package by.myProject.config;


import by.myProject.security.LoginSecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.*;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        // Создание корневого контекста Spring
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(HibernateConfiguration.class, LoginSecurityConfig.class);

        // Листенер для управления жизненным циклом корневого контекста Spring
        container.addListener(new ContextLoaderListener(rootContext));

        // Создание контекста Spring для сервлета-диспетчера Spring MVC
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(AppConfig.class);

        // Регистрация сервлета-диспетчера Spring MVC
        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        // Отдельный маппинг для главной страницы приложения
        dispatcher.addMapping("/index");

        // Установка параметров контейнера
        container.setInitParameter("defaultHtmlEscape", "true");

        // Регистрация других сервлетов и фильтров

        // Например, фильтра для установки кодировки символов приложения
        FilterRegistration charEncodingFilterReg =
                container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
        charEncodingFilterReg.setInitParameter("forceEncoding", "true");
        charEncodingFilterReg.addMappingForUrlPatterns(null, false, "/*");
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{LoginSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }


//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] {
//                HibernateConfiguration.class};
//    }

//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[] {
//                AppConfig.class};
//    }

//    @Override
//    protected String[] getServletMappings() {
//        return new String[] {"/"};
//    }

//    @Override
//    protected Filter[] getServletFilters() {
//
//        CharacterEncodingFilter charFilter = new CharacterEncodingFilter();
//        charFilter.setEncoding("UTF-8");
//        charFilter.setForceEncoding(true);
//        charFilter.setForceRequestEncoding(true);
//        charFilter.setForceResponseEncoding(true);
//        return new Filter[] {
//                new HiddenHttpMethodFilter(), charFilter, new HttpPutFormContentFilter()
//        };
//    }
}




