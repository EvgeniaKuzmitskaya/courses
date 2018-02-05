package by.myProject.controller;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    protected static Logger logger = Logger.getLogger(LoginController.class);

    // запускает страницу авторизации
    @RequestMapping(value = { "/", "/loginPage" }, method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("loginPage");
        logger.info("view loginPage");
        return model;
    }

    // показывает страницу админа при авторизации админом
    @RequestMapping(value = {"/adminPage"}, method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Welcome to the admin page");
        model.setViewName("adminPage");
        model.addObject("userName", getPrincipal());
        return model;
    }

    // показывает страницу админа при авторизации админом
    @RequestMapping(value = {"/studentPage"}, method = RequestMethod.GET)
    public ModelAndView studentPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Welcome to the student page");
        model.setViewName("studentPage");
        model.addObject("userName", getPrincipal());
        return model;
    }

    // показывает страницу преподавателя при авторизации пользователя с ролью преподаватель
    @RequestMapping(value = {"/teacherPage"}, method = RequestMethod.GET)
    public ModelAndView teacherPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Welcome to the teacher page");
        model.addObject("userName", getPrincipal());
        model.setViewName("teacherPage");
        return model;
    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("userName", userDetail.getUsername());
        }
        model.setViewName("403");
        return model;
    }

    private String getPrincipal(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    // выход из аккаунта
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/loginPage?logout";
    }

}
