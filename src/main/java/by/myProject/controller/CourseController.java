package by.myProject.controller;

import by.myProject.model.domain.Course;
import by.myProject.model.domain.User;
import by.myProject.model.domain.dto.CourseTO;
import by.myProject.model.service.CourseService;
import by.myProject.model.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    protected static Logger logger = Logger.getLogger(CourseController.class);

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = {"/courseForm"}, method = RequestMethod.GET)
    public String showForm (ModelMap model, Course course) {
        model.addAttribute("courseAdd", course);
        model.addAttribute("courseto", new CourseTO());
        model.addAttribute("users", this.userService.findByRole());
        return "courseAdd";
    }

//    @ModelAttribute("users")
//    public List<User> initializeUsers() {
//        return
//                this.userService.findByRole();
//    }

    @RequestMapping(value = {"/courseForm"}, method = RequestMethod.POST)
    @Transactional
    public String saveCourse(@Valid @ModelAttribute("courseto") CourseTO courseTO, String userId,
                             BindingResult result, ModelMap model) {



        Course course = new Course();
        String dateBeginCourse = courseTO.getDateBeginCourse();
        String dateEndCourse = courseTO.getDateEndCourse();
        course.setDescriptionCourse(courseTO.getDescriptionCourse());
        course.setNameCourse(courseTO.getNameCourse());

        try {
            //convert strings to dates
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parseDateBegin = sdf.parse(dateBeginCourse);
            Date parseDateEnd = sdf.parse(dateEndCourse);

            course.setDateBeginCourse(parseDateBegin);
            course.setDateEndCourse(parseDateEnd);


        } catch (ParseException e){
            logger.error("Some date has invalid format", e);
            return "courseAdd";
        }

        User u = userService.findById(Long.parseLong(userId));
        //        User u = userService.findByLastName(lastName);
        u.getCourses().add(course);
        courseService.save(course);

        if(result.hasErrors()){
            return "courseAdd";
        }


        Optional<Course> maybeCourse = courseService.findCourse(course.getNameCourse());
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
        List<Course> listCourse = new ArrayList(courseService.findAll());
        model.addAttribute("listCourse", listCourse);
        model.addAttribute("message", "Course saved successfully.");
        return "courseAdded";
    }
}
