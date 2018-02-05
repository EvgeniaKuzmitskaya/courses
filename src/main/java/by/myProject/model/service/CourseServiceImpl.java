package by.myProject.model.service;

import by.myProject.model.dao.CourseDao;
import by.myProject.model.domain.*;
import by.myProject.model.domain.dto.CourseTO;
import by.myProject.model.domain.dto.StudentCourseTO;
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
    UserCourseService userCourseService;

    @Override
    public SimpleDateFormat getDateFormat(){
        return SDF;
    }

    @Override
    @Transactional
    public void save(Course course) {
        this.courseDao.save(course);
    }


    // оохраняет курс при его создании админом
    @Override
    @Transactional
    public Optional<Course> saveCourse(CourseTO courseTO) {
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
        Status s = statusService.findById(Long.parseLong(courseTO.getStatusId()));
        course.setStatus(s);
        this.save(course);
        Optional<Course> maybeCourse = this.findCourse(course.getNameCourse());
        if (maybeCourse.isPresent()){
            course = maybeCourse.get();
            UserCourse userCourse = new UserCourse();
            userCourse.setCourse(course);
            userCourse.setUser(u);
            userCourseService.save(userCourse);
        }
        return maybeCourse;
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

    // возвращает курс по названию
    @Override
    public Optional<Course> findCourse(String nameCourse) {
        return this.courseDao.findCourse(nameCourse);
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

    //
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
        return course.getUserCourses().stream().filter(userCourse -> {
            User user = userCourse.getUser();
            for (Role role: user.getRoles()){
                if (TypeRole.ROLE_TEACHER.getRoleType().equals(role.getTypeRole())){
                    return true;
                }
            }
            return false;
        }).findFirst().map(UserCourse::getUser);
    }


    // сохраняет студента на определенный курс
    @Override
    @Transactional
    public Optional<Course> saveStudent(CourseTO courseTO, String userName) {
        Course course = this.findById(courseTO.getIdCourse());
        User u = userService.findByUserName(userName).get();
        UserCourse userCourse = new UserCourse();
        userCourse.setCourse(course);
        userCourse.setUser(u);
        userCourseService.save(userCourse);
        course = this.findById(courseTO.getIdCourse());
        return Optional.of(course);
    }

    @Override
    @Transactional
    public List<CourseTO> getAllCoursesStudent(String userName) {
        User user = userService.findByUserName(userName).get();
        return user.getUserCourses().stream().map(userCourse -> {
            Course course = userCourse.getCourse();
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

            if (userCourse.getMarkResult()!=null){
            dto.setMarkResult(String.valueOf(userCourse.getMarkResult()));
            } else dto.setMarkResult(" ");

            this.getFirstTeacher(course).ifPresent(user1 -> dto.setUserLastName(user1.getLastName()));
            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public List<CourseTO> getAllCoursesTeacher(String userName) {
        User user = userService.findByUserName(userName).get();
        return user.getUserCourses().stream().map(userCourse -> {
            Course course = userCourse.getCourse();
            CourseTO dto = new CourseTO();
            dto.setIdCourse(course.getIdCourse());
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
            this.getStudents(course).forEach(user1 -> dto.setUserLastName(user1.getLastName()));
            return dto;
        }).collect(Collectors.toList());
    }

    private boolean isStudent(User user) {
        for (Role role: user.getRoles()){
            if (TypeRole.ROLE_STUDENT.getRoleType().equals(role.getTypeRole())){
                return true;
            }
        }
        return false;
    }

    private List<User> getStudents(Course course) {
        return course.getUserCourses().stream()
                .filter(userCourse -> isStudent(userCourse.getUser()))
                .map(UserCourse::getUser)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<StudentCourseTO> getAllCourseStudents(Long courseId) {
        Course course = findById(courseId);
        return course.getUserCourses().stream()
                .filter(userCourse -> isStudent(userCourse.getUser()))
                .map(userCourse -> {
            StudentCourseTO dto = new StudentCourseTO();
            User user = userCourse.getUser();
            dto.setIdCourse(course.getIdCourse());
                    if (course.getDateBeginCourse() != null) {
                        dto.setDateBeginCourse(SDF.format(course.getDateBeginCourse()));
                    }
                    if (course.getDateEndCourse() != null) {
                        dto.setDateEndCourse(SDF.format(course.getDateEndCourse()));
                    }
                    dto.setNameCourse(course.getNameCourse());
            dto.setIdUser(user.getIdUser());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setMarkResult(userCourse.getMarkResult());

            return dto;
        }).collect(Collectors.toList());
    }



    @Override
    @Transactional
    public CourseTO getCourseEdit(Long courseId) {
        Course course = this.findById(courseId);
        CourseTO dto = new CourseTO();
        dto.setIdCourse(course.getIdCourse());
        dto.setNameCourse(course.getNameCourse());
        if (course.getDateBeginCourse() != null) {
            dto.setDateBeginCourse(SDF.format(course.getDateBeginCourse()));
        }
        if (course.getDateEndCourse() != null) {
            dto.setDateEndCourse(SDF.format(course.getDateEndCourse()));
        }
        dto.setDescriptionCourse(course.getDescriptionCourse());
        dto.setTypeStatus(course.getStatus().getTypeStatus());
        dto.setStatusId(String.valueOf(course.getStatus().getIdStatus()));
        this.getFirstTeacher(course).ifPresent(user ->
                dto.setUserLastName(user.getLastName()));
        return dto;
    }

    @Override
    @Transactional
    public Optional<Course> updateCourse(CourseTO courseTO, Long statusId, Long idCourse) {
        Course course = this.findById(courseTO.getIdCourse());
        course.setNameCourse(courseTO.getNameCourse());
        String dateBeginCourse = courseTO.getDateBeginCourse();
        String dateEndCourse = courseTO.getDateEndCourse();
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
        course.setDescriptionCourse(courseTO.getDescriptionCourse());
        Status status = statusService.findById(Long.parseLong(courseTO.getTypeStatus()));
        course.setStatus(status);
        User user = userService.findById(Long.valueOf(courseTO.getUserLastName()));
        UserCourse userCourse = findTeacherUserCourse(course);
        userCourse.setUser(user);
        userCourse.setCourse(course);
        if (userCourse.getIdResult() == null){
            userCourseService.save(userCourse);
        } else {
            userCourseService.update(userCourse);
        }
        this.update(course);
        return this.findCourse(course.getNameCourse());
    }

    private UserCourse findTeacherUserCourse(Course course){
        UserCourse userCourse = null;
        for (UserCourse userCourse1: course.getUserCourses()){
            User user = userCourse1.getUser();
            if (isTeacher(user)){
                userCourse = userCourse1;
                break;
            }
        }
        if (userCourse == null){
            userCourse = new UserCourse();
        }
        return userCourse;
    }

    // является ли user преподавателем
    private boolean isTeacher(User user) {
        for (Role role: user.getRoles()){
            if (TypeRole.ROLE_TEACHER.getRoleType().equals(role.getTypeRole())){
                return true;
            }
        }
        return false;
    }
}
