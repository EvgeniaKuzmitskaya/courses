package by.myProject.controller;

import by.myProject.model.domain.Course;
import by.myProject.model.domain.Role;
import by.myProject.model.domain.Status;
import by.myProject.model.domain.User;
import by.myProject.model.domain.dto.CourseTO;
import by.myProject.model.domain.enums.TypeRole;
import by.myProject.model.service.CourseService;
import by.myProject.model.service.StatusService;
import by.myProject.model.service.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.http.MediaType;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CourseController {

    protected static Logger logger = Logger.getLogger(CourseController.class);

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "statusService")
    private StatusService statusService;

    @RequestMapping(value = {"/courseForm"}, method = RequestMethod.GET)
    public String showForm (ModelMap model, Course course) {
        model.addAttribute("courseAdd", course);
        model.addAttribute("courseto", new CourseTO());
        model.addAttribute("users", this.userService.findByRole());
        model.addAttribute("status", this.statusService.findAll());
        return "courseAdd";
    }

    @ModelAttribute("users")
    public List<User> initializeUsers() {
        return this.userService.findByRole();
    }

    @RequestMapping(value = {"/courseForm"}, method = RequestMethod.POST)
    public String saveCourse(@Valid @ModelAttribute("courseto") CourseTO courseTO,
                             BindingResult result, ModelMap model) {

        if(result.hasErrors()){
            return "courseAdd";
        }


        Optional<Course> maybeCourse = courseService.save(courseTO);
        if (maybeCourse.isPresent()) {
            System.out.println("course=" + maybeCourse.get());
            model.addAttribute("courseForm", maybeCourse.get());
            return "redirect:/courseAdded";
        } else {
            System.out.println("course does not exist :(");
            return "courseAdd";
        }
    }

   @RequestMapping(value = "/courseAdded", method = RequestMethod.GET)
    public String listCourses(Model model){
        model.addAttribute("message", "Course saved successfully.");
        model.addAttribute("listCourse", courseService.getAllCourses());
        return "courseAdded";
    }

    @RequestMapping(value = "/listCourses", method = RequestMethod.GET)
    public String listAdminCourses(Model model){
        model.addAttribute("listCourse", courseService.getAllCourses());
        return "listCourses";
    }

    @RequestMapping(value = "editCourse/{id}", method = RequestMethod.GET)
    public String editCourse(@PathVariable("id") Long id, Model model){
        model.addAttribute("course", this.courseService.findById(id));
        model.addAttribute("listCourse", this.courseService.findAll());
//        model.addAttribute("listUsers", userService.findByCourse());
        return "courseAdd";
    }


    @RequestMapping(value = "/removeCourse/{id}")
    public String removeCourse(@PathVariable("id") Long id){
        logger.debug("Received request to delete existing course");
        this.courseService.deleteById(id);
        return "redirect:/courseAdded";
    }

//  Контроллер для формы Регистрация студента на курс
    @RequestMapping(value = {"/registerCourse"}, method = RequestMethod.GET)
    public String registerCourseShowForm (ModelMap model) {
        model.addAttribute("studentCourses", this.courseService.findAll());
        model.addAttribute("register", new CourseTO());
        return "addOnCourse";
    }

    @RequestMapping(value="/getCourses", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getCoursesAjax(@RequestParam("courseId") Long courseId) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String response = "";
        try {
            response = ow.writeValueAsString(this.courseService.getAllCoursesStudent(courseId));
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return response;
    }

    //сохраняет студента
    @RequestMapping(value = {"/registerCourse"}, method = RequestMethod.POST)
    public String registerOnCourse(ModelMap model, @ModelAttribute("register") CourseTO courseTo){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            String userName = userDetail.getUsername();
            Optional<Course> course = courseService.saveStudent(courseTo, userName);
            if (course.isPresent()) {
                System.out.println("course=" + course.get());
                model.addAttribute("registerCourse", course.get());
            } else {
                System.out.println("course does not exist :(");
            }
        }
        return "redirect:/courseStudent";
    }


    @RequestMapping(value = "/courseStudent", method = RequestMethod.GET)
    public String listStudents(Model model, Course course){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            String userName = userDetail.getUsername();
            model.addAttribute("listSudentCourse", courseService.getAllCoursesStudent(userName));
    }

    return "courseStudent";
}
}
