package by.myProject.model.service.impl.util;

import by.myProject.model.domain.*;
import by.myProject.model.domain.enums.TypeRole;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityCreator {

    private static DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static User userCreate(){
        User user = new User(1L, "marina", "1111",
                "Marina", "Ivanova", "marina@mail.ru", true );
        return user;
    }

    public static User userCreate(long id){
        User user = new User(id, "username" + id, "password" + id,
                "firstname" + id, "lastname" + id,
                id + "test@mail.ru", true );
        return user;
    }

    public static Role roleCreate(TypeRole typeRole){
        Role role = new Role();
        role.setTypeRole(typeRole.getRoleType());
        return role;
    }

    private static Date createDate(String string){
        try {
            return formatter.parse(string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Course courseCreate(long id){

        Course course = new Course(id, "nameCourse" + id,
                createDate("12-08-2017"), createDate("23-09-2017"),
                "descriptionCourse" + id);
        return course;
    }

    public static Status statusCreate(Course course, long id){
        Status status = new Status(id, "nameStatus" + id);
        status.getCourses().add(course);
        return status;
    }

    public static UserCourse userCourseStudentCreate(User user, Course course, long id){
        UserCourse userCourse = new UserCourse(id, 6);
        userCourse.setCourse(course);
        userCourse.setUser(user);
        return userCourse;
    }

    public static UserCourse userCourseTeacherCreate(User user, Course course){
        UserCourse userCourse = new UserCourse();
        userCourse.setCourse(course);
        userCourse.setUser(user);
        return userCourse;
    }
}
