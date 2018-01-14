package by.myProject.model.service;

import by.myProject.model.dao.CourseDao;
import by.myProject.model.domain.Course;
import by.myProject.model.domain.Role;
import by.myProject.model.domain.Status;
import by.myProject.model.domain.User;
import by.myProject.model.domain.dto.CourseTO;
import by.myProject.model.domain.enums.TypeRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService{

    protected static Logger logger = Logger.getLogger(CourseServiceImpl.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    CourseDao courseDao;

    @Autowired
    UserService userService;

    @Autowired
    StatusService statusService;

    @Autowired
    ResultService resultService;

    @Override
    public SimpleDateFormat getDateFormat(){
        return SDF;
    }

    @Override
    @Transactional
    public void save(Course course) {
        this.courseDao.save(course);
    }

    @Override
    @Transactional
    public Optional<Course> save(CourseTO courseTO) {
        Course course = new Course();
        String dateBeginCourse = courseTO.getDateBeginCourse();
        String dateEndCourse = courseTO.getDateEndCourse();
        course.setDescriptionCourse(courseTO.getDescriptionCourse());
        course.setNameCourse(courseTO.getNameCourse());

        try {
            //convert strings to dates
            Date parseDateBegin = SDF.parse(dateBeginCourse);
            Date parseDateEnd = SDF.parse(dateEndCourse);

            course.setDateBeginCourse(parseDateBegin);
            course.setDateEndCourse(parseDateEnd);

        } catch (ParseException e){
            logger.error("Some date has invalid format", e);
            return Optional.empty();
        }

        User u = userService.findById(Long.parseLong(courseTO.getUserId()));
        u.getCourses().add(course);
        Status s = statusService.findById(Long.parseLong(courseTO.getStatusId()));
        course.setStatus(s);
        this.save(course);

        return this.findCourse(course.getNameCourse());
    }

    @Override
    public void deleteById(Long id) {
        this.courseDao.deleteById(id);
    }

    @Override
    public List<Course> findAll() {
        return this.courseDao.findAll();
    }

    @Override
    public void update(Course course) {
        this.courseDao.update(course);
    }

    @Override
    public Course findById(Long id) {
        return this.courseDao.findById(id);
    }

    @Override
    public Optional<Course> findCourse(String nameCourse) {
        return this.courseDao.findCourse(nameCourse);
    }

    @Override
    public List<Course> findAllByUser() {
        return this.courseDao.findAllByUser();
    }

    @Override
    @Transactional
    public List<CourseTO> getAllCourses() {
        return findAll().stream().map(course -> {
            CourseTO dto = new CourseTO();
            dto.setIdCourse(course.getIdCourse());
            if (course.getDateBeginCourse() != null){
                dto.setDateBeginCourse(SDF.format(course.getDateBeginCourse()));
            }
            if (course.getDateEndCourse() != null){
                dto.setDateEndCourse(SDF.format(course.getDateEndCourse()));
            }
            dto.setDescriptionCourse(course.getDescriptionCourse());
            dto.setNameCourse(course.getNameCourse());
            if (course.getStatus() != null){
                dto.setTypeStatus(course.getStatus().getTypeStatus());
            }
            this.getFirstTeacher(course).ifPresent(user -> dto.setUserLastName(user.getLastName()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CourseTO getAllCoursesStudent(Long courseId) {
            CourseTO dto = new CourseTO();
            Course course = findById(courseId);
            this.getFirstTeacher(course).ifPresent(user -> dto.setUserLastName(user.getLastName()));
            dto.setIdCourse(courseId);
            if (course.getDateBeginCourse() != null){
                dto.setDateBeginCourse(SDF.format(course.getDateBeginCourse()));
            }
            if (course.getDateEndCourse() != null){
                dto.setDateEndCourse(SDF.format(course.getDateEndCourse()));
            }
            dto.setDescriptionCourse(course.getDescriptionCourse());
            dto.setNameCourse(course.getNameCourse());
            if (course.getStatus() != null){
                dto.setTypeStatus(course.getStatus().getTypeStatus());
            }
            return dto;
    }

    private Optional<User> getFirstTeacher(Course course) {
        return course.getUsers().stream().filter(user -> {
            for (Role role: user.getRoles()){
                if (TypeRole.ROLE_TEACHER.getRoleType().equals(role.getTypeRole())){
                    return true;
                }
            }
            return false;
        }).findFirst();
    }

    @Override
    @Transactional
    public Optional<Course> saveStudent(CourseTO courseTO, String userName) {
        Course course = this.findById(courseTO.getIdCourse());
        User u = userService.findByUserName(userName);
        u.getCourses().add(course);
        userService.update(u);
        course = this.findById(courseTO.getIdCourse());
        return Optional.of(course);
    }


    @Override
    @Transactional
    public List<CourseTO> getAllCoursesStudent(String userName) {
        User user = userService.findByUserName(userName);
        return user.getCourses().stream().map(course -> {
            CourseTO dto = new CourseTO();
            dto.setNameCourse(course.getNameCourse());
            if (course.getDateBeginCourse() != null) {
                dto.setDateBeginCourse(SDF.format(course.getDateBeginCourse()));
            }
            if (course.getDateEndCourse() != null) {
                dto.setDateEndCourse(SDF.format(course.getDateEndCourse()));
            }
            if (course.getStatus() != null) {
                dto.setTypeStatus(course.getStatus().getTypeStatus());
            }
            dto.setDescriptionCourse(course.getDescriptionCourse());
            dto.setMarkResult(resultService.findByStudent(user));
            this.getFirstTeacher(course).ifPresent(user1 -> dto.setUserLastName(user1.getLastName()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Course> findAllStudentCourse(String userName) {
        return this.courseDao.findAllStudentCourse(userName);
    }


    // Регистрация на курс студента
//    @Override
//    @Transactional
//    public Optional<Course> FindFormStudent(Course course) {
//        CourseTO courseTo = new CourseTO();
//        courseTo.setUserId(course.getIdCourse().toString());
//        courseTo.setNameCourse(course.getNameCourse());
//        Date dateBeginCourse = course.getDateBeginCourse();
//        Date dateEndCourse = course.getDateEndCourse();
//        try {
//            //convert dates to Strings
//            String reportDateBegin = SDF.format(dateBeginCourse);
//            String reportDateEnd = SDF.format(dateEndCourse);
//
//            courseTo.setDateBeginCourse(reportDateBegin);
//            courseTo.setDateEndCourse(reportDateEnd);
//
//        } catch (Exception e){
//            logger.error("Some date has invalid format", e);
//            return Optional.empty();
//        }
//
//        User u = userService.findById(Long.parseLong(courseTo.getUserId()));
//
//
//
//        return courseTo.getNameCourse();
//    }
//
//    @Override
//    @Transactional
//    public List<CourseTO> getCoursesStudent() {
//        return findAll().stream().map(course -> {
//            CourseTO dto = new CourseTO();
//            dto.setIdCourse(course.getIdCourse());
//            if (course.getDateBeginCourse() != null){
//                dto.setDateBeginCourse(SDF.format(course.getDateBeginCourse()));
//            }
//            if (course.getDateEndCourse() != null){
//                dto.setDateEndCourse(SDF.format(course.getDateEndCourse()));
//            }
//            dto.setDescriptionCourse(course.getDescriptionCourse());
//            dto.setNameCourse(course.getNameCourse());
//            if (course.getStatus() != null){
//                dto.setTypeStatus(course.getStatus().getTypeStatus());
//            }
//            course.getUsers().stream().filter(user -> {
//                for (Role role: user.getRoles()){
//                    if (TypeRole.ROLE_TEACHER.getRoleType().equals(role.getTypeRole())){
//                        return true;
//                    }
//                }
//                return false;
//            }).findFirst().ifPresent(user -> dto.setUserLastName(user.getLastName()));
//            return dto;
//        }).collect(Collectors.toList());
//    }






}
