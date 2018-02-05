package by.myProject.controller;

import by.myProject.model.domain.*;
import by.myProject.model.domain.dto.CourseTO;
import by.myProject.model.service.CourseService;
import by.myProject.model.service.StatusService;
import by.myProject.model.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

@Controller
public class RegistrationCourseController {

    protected static Logger logger = Logger.getLogger(RegistrationCourseController.class);

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "statusService")
    private StatusService statusService;


    // показывает форму для создания нового курса
    @RequestMapping(value = {"/courseForm"}, method = RequestMethod.GET)
    public String showForm (ModelMap model, Course course) {
        model.addAttribute("courseAdd", course);
        model.addAttribute("courseto", new CourseTO());
        model.addAttribute("users", this.userService.findTeachers());
        model.addAttribute("status", this.statusService.findAll());
        return "registrationCourse";
    }

    // возвращает всех пользователей с ролью преподавателя
    @ModelAttribute("users")
    public List<User> initializeUsers() {
        return this.userService.findTeachers();
    }

    @RequestMapping(value = {"/courseForm"}, method = RequestMethod.POST)
    public String saveCourse(@Valid @ModelAttribute("courseto") CourseTO courseTO,
                             BindingResult result, ModelMap model) {
        if(result.hasErrors()){
            return "registrationCourse";
        }
        Optional<Course> maybeCourse = courseService.saveCourse(courseTO);
        if (maybeCourse.isPresent()) {
            System.out.println("course=" + maybeCourse.get());
            model.addAttribute("courseForm", maybeCourse.get());
            return "redirect:/registrationCourseSuccess";
        } else {
            System.out.println("course does not exist :(");
            return "registrationCourse";
        }
    }

    // выводит список с курсами после успешного создания нового курса
   @RequestMapping(value = "/registrationCourseSuccess", method = RequestMethod.GET)
    public String listCourses(Model model){
        model.addAttribute("message", "Course saved successfully.");
        model.addAttribute("listCourse", courseService.getAllCourses());
        return "registrationCourseSuccess";
    }

    // выводит список всех курсов
    @RequestMapping(value = "/listCourses", method = RequestMethod.GET)
    public String listAdminCourses(Model model){
        model.addAttribute("listCourse", courseService.getAllCourses());
        return "listCourses";
    }

    // форма редактирования курса
    @RequestMapping(value = "/editCourse/{id}", method = RequestMethod.GET)
    public ModelAndView editCourseForm(@PathVariable("id") Long id, ModelMap model){
        CourseTO courseTO = courseService.getCourseEdit(id);
        model.addAttribute("users", this.userService.findTeachers());
        model.addAttribute("status", this.statusService.findAll());
        return new ModelAndView("registrationCourseEdit","command",courseTO);
    }

    // сохраняет изменения при редактировании
    @RequestMapping(value = "/editCourse/{id}", method = RequestMethod.POST)
    public String edit(CourseTO courseTO, Long statusId, ModelMap model, Long userId) {
        Optional<Course> maybeCourse = courseService.updateCourse(courseTO, statusId, userId);
        if (maybeCourse.isPresent()) {
            System.out.println("course=" + maybeCourse.get());
            model.addAttribute("registrationCourseEdit", maybeCourse.get());
            return "redirect:/registrationCourseSuccess";
        } else {
            System.out.println("user does not exist :(");
            return "registrationCourseEdit";
        }
    }

    // удаляет нужный курс по id
    @RequestMapping(value = "/removeCourse/{id}")
    public String removeCourse(@PathVariable("id") Long id){
        logger.debug("Received request to delete existing course");
        this.courseService.deleteById(id);
        return "redirect:/registrationCourseSuccess";
    }
}
