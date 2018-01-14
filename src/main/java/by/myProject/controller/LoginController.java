package by.myProject.controller;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    protected static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = { "/", "/loginForm" }, method = RequestMethod.GET)
    public String loginPage(Model model, String logout) {
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "loginForm";
    }

    @RequestMapping(value = { "/loginForm" }, method = RequestMethod.POST)
    public String welcomePage(ModelAndView model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails user = (UserDetails) auth.getPrincipal();
            model.addObject("username", user.getUsername());

        String page = "403";
        if (user != null) {
        switch (user.getAuthorities().toArray()[0].toString()) {
                    case "ROLE_ADMIN":
                        page = "adminPage";
                        break;
                    case "ROLE_TEACHER":
                        page = "teacherPage";
                        break;

                    case "ROLE_STUDENT":
                        page = "studentPage";
                        break;

                    default:
                        break;
                }

            }

            return page;
        }



    @RequestMapping(value = {"/adminPage"}, method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Welcome to the admin page");
        model.setViewName("adminPage");
        return model;
    }

    @RequestMapping(value = {"/studentPage"}, method = RequestMethod.GET)
    public ModelAndView studentPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Welcome to the student page");
        model.setViewName("studentPage");
        return model;
    }

    @RequestMapping(value = {"/teacherPage"}, method = RequestMethod.GET)
    public ModelAndView teacherPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Welcome to the teacher page");
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
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }

    /**
     * This update page is for user login with password only.
     * If user is login via remember me cookie, send login to ask for password again.
     * To avoid stolen remember me cookie to update info
     */
    @RequestMapping(value = "/adminPage/update**", method = RequestMethod.GET)
    public ModelAndView updatePage(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (isRememberMeAuthenticated()) {
            //send login for update
            setRememberMeTargetUrlToSession(request);
            model.addObject("loginUpdate", true);
            model.setViewName("/loginForm");
        } else {
            model.setViewName("update");
        }
        return model;
    }

    private boolean isRememberMeAuthenticated() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }

    /**
     * save targetURL in session
     */
    private void setRememberMeTargetUrlToSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.setAttribute("targetUrl", "/admin/update");
        }
    }

    /**
     * get targetURL from session
     */
    private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
        String targetUrl = "";
        HttpSession session = request.getSession(false);
        if(session!=null){
            targetUrl = session.getAttribute("targetUrl")==null?""
                    :session.getAttribute("targetUrl").toString();
        }
        return targetUrl;
    }





}
