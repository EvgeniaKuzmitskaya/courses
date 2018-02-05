package by.myProject.controller;


import by.myProject.model.domain.UserCourse;
import by.myProject.model.domain.dto.CourseTO;
import by.myProject.model.domain.dto.StudentCourseTO;
import by.myProject.model.service.CourseService;
import by.myProject.model.service.UserCourseService;
import by.myProject.model.service.UserService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TeacherCourseController {

    protected static Logger logger = Logger.getLogger(TeacherCourseController.class);

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "userCourseService")
    private UserCourseService userCourseService;

    @Resource(name = "userService")
    private UserService userService;

    //  форма преподавателя для выставления оценки студенту за курс
    @RequestMapping(value = {"/putResult"}, method = RequestMethod.GET)
    public String resultCourseShowForm (Model model, Long courseId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            String userName = userDetail.getUsername();
            model.addAttribute("coursesTeacher", this.courseService.getAllCoursesTeacher(userName));
        }
        model.addAttribute("userCourse", new CourseTO());
        if(courseId!=null) {
            model.addAttribute("students", this.userService.getAllStudents(courseId));
        } else model.addAttribute("students", new ArrayList<>());
        model.addAttribute("mark", new UserCourse());

        return "resultCourse";
    }

    //ajax для формы по выставлению оценки преподавателем студенту
    @RequestMapping(value="/getTeacherCourses", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getTeacherCoursesAjax(@RequestParam("courseId") Long courseId) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String response = "";
        try {
            List<StudentCourseTO> list = this.courseService.getAllCourseStudents(courseId);
            response = ow.writeValueAsString(list);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return response;
    }

    //сохраняет оценку
    @RequestMapping(value = {"/putResult"}, method = RequestMethod.POST)
    public String saveMarkResult(ModelMap model, @ModelAttribute("userCourse")
            StudentCourseTO studentCourseTO, String markResult){
        Optional<UserCourse> userCourse = userCourseService.saveResult(studentCourseTO);
        if (userCourse.isPresent()) {
            System.out.println("course=" + userCourse.get());
            model.addAttribute("putResult", userCourse.get());
        } else {
            System.out.println("course does not exist :(");
        }
        return "redirect:/courseTeacher";
    }

    // выводит список информации по курсу для преподавателя
    @RequestMapping(value = {"/courseTeacher", "/chooseCourse"}, method = RequestMethod.GET)
    public String listTeacher(Model model, Long courseId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            String userName = userDetail.getUsername();
            model.addAttribute("coursesTeacher", this.courseService.getAllCoursesTeacher(userName));
        }
        return "courseTeacher";
    }

    @RequestMapping(value="/getListCourses", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getStudentsOnCourseAjax(@RequestParam("courseId") Long courseId) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String response = "";
        try {
            List<StudentCourseTO> list = this.courseService.getAllCourseStudents(courseId);
            response = ow.writeValueAsString(list);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return response;
    }
}
