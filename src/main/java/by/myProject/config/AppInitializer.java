package by.myProject.config;

import by.myProject.security.LoginSecurityConfig;
import org.springframework.security.access.SecurityConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.EnumSet;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        //create the root Spring application context
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(AppConfig.class, LoginSecurityConfig.class);
//
//        servletContext.addListener(new ContextLoaderListener(rootContext));
//
//        //Create the dispatcher servlet's Spring application context
//        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
//        servletAppContext.register(HibernateConfiguration.class);
//
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
//        // throw NoHandlerFoundException to controller ExceptionHandler.class. Used for <error-page> analogue
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//
//        //register and map the dispatcher servlet
//        //note Dispatcher servlet with constructor arguments
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//
//        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
//        encodingFilter.setInitParameter("encoding", "UTF-8");
//        encodingFilter.setInitParameter("forceEncoding", "true");
//        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
//
//    }
//    @Override
//    public void onStartup(ServletContext container) throws ServletException {
//
//    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//    ctx.register(AppConfig.class);
//    ctx.setServletContext(container);
//
//    ServletRegistration.Dynamic servlet = container.addServlet(
//            "dispatcher", new DispatcherServlet(ctx));
//
//    servlet.setLoadOnStartup(1);
//    servlet.addMapping("/");
//
//        FilterRegistration.Dynamic encodingFilter = container.addFilter("encoding-filter", new CharacterEncodingFilter());
//        encodingFilter.setInitParameter("encoding", "UTF-8");
//        encodingFilter.setInitParameter("forceEncoding", "true");
//        encodingFilter.addMappingForUrlPatterns(null, true, "/*");

//}







//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
//        appContext.register(HibernateConfiguration.class);
//
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
//                new DispatcherServlet(appContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//
//
//        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(appContext);
//
//        servletContext.addListener(contextLoaderListener);
//
//
//        // Filter.
//        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
//
//        fr.setInitParameter("encoding", "UTF-8");
//        fr.setInitParameter("forceEncoding", "true");
//        fr.addMappingForUrlPatterns(null, true, "/*");
//    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                HibernateConfiguration.class};
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

//    @Override
//    protected Filter[] getServletFilters() {
//
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//
//        DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
//
//        return new Filter[] {characterEncodingFilter, securityFilterChain};
//    }



    @Override
    protected Filter[] getServletFilters() {

        CharacterEncodingFilter charFilter = new CharacterEncodingFilter();
        charFilter.setEncoding("UTF-8");
        charFilter.setForceEncoding(true);
        return new Filter[] { new HiddenHttpMethodFilter(), charFilter,
                };
    }


// new HttpPutFormContentFilter()



//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        //create the root Spring application context
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(HibernateConfiguration.class, LoginSecurityConfig.class);
//
//        servletContext.addListener(new ContextLoaderListener(rootContext));
//
//        //Create the dispatcher servlet's Spring application context
//        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
//        servletAppContext.register(AppConfig.class);
//
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
//        // throw NoHandlerFoundException to controller ExceptionHandler.class. Used for <error-page> analogue
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//
//        //register and map the dispatcher servlet
//        //note Dispatcher servlet with constructor arguments
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//
//        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
//        encodingFilter.setInitParameter("encoding", "UTF-8");
//        encodingFilter.setInitParameter("forceEncoding", "true");
//        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
//
//    }
//
//    /**
//     * added to load spring security filter in root context (created in onStartup())
//     */
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] {LoginSecurityConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[0];
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class<?>[0];
//    }
    }




