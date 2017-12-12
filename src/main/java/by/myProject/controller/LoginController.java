package by.myProject.controller;

import by.myProject.model.domain.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.MessageSource;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class LoginController {

    protected static Logger logger = Logger.getLogger(LoginController.class);



    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("welcome");
        return model;
    }

    @RequestMapping(value = { "/homePage"}, method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("homePage");
        return model;
    }

//    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
//    public ModelAndView loginPage(@RequestParam(name = "error", required = false) String error,
//                                  @RequestParam(name = "logout", required = false) String logout,
//                            ModelAndView model) {
//
//        if (error != null) {
//            model.addObject("error", "Invalid Credentials provided.");
//        }
//
//        if (logout != null) {
//            model.addObject("message", "Logged out from JournalDEV successfully.");
//        }
//
//        model.setViewName("loginForm");
//        return model;
//    }

    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public String loginPage(Model model, String error, String logout) {

        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

       return "loginForm";

    }








}
