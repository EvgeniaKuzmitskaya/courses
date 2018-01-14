package by.myProject.controller;

import by.myProject.model.domain.Result;
import by.myProject.model.domain.dto.CourseTO;
import by.myProject.model.service.CourseService;
import by.myProject.model.service.ResultService;
import by.myProject.model.service.StatusService;
import by.myProject.model.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class ResultController {

    protected static Logger logger = Logger.getLogger(RegistrationController.class);

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "statusService")
    private StatusService statusService;

    @Resource(name = "resultService")
    private ResultService resultService;

    @RequestMapping(value = {"/resultCourse"}, method = RequestMethod.GET)
    public String showForm (ModelMap model, Result result) {
        model.addAttribute("resultForm", result);
        model.addAttribute("resultto", new CourseTO());
        model.addAttribute("courses", this.courseService.findAll());
        model.addAttribute("users", this.userService.findAllUsers());
        return "resultCourse";
    }


}
