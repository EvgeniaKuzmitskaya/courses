package by.myProject.controller;

import by.myProject.model.domain.Course;
import by.myProject.model.domain.dto.CourseTO;
import by.myProject.model.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class StudentCourseController {

    protected static Logger logger = Logger.getLogger(RegistrationCourseController.class);

    @Resource(name = "courseService")
    private CourseService courseService;

    //  форма для записи студента на курс
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

    //сохраняет студента на курс
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

    // список курсов студента
    @RequestMapping(value = "/courseStudent", method = RequestMethod.GET)
    public String listStudents(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            String userName = userDetail.getUsername();
            model.addAttribute("listSudentCourse", courseService.getAllCoursesStudent(userName));
        }
        return "courseStudent";
    }



}
