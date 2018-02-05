package by.myProject.model.service;

import by.myProject.model.dao.UserDao;
import by.myProject.model.domain.*;
import by.myProject.model.domain.dto.UserTO;
import by.myProject.model.domain.enums.TypeRole;
import org.junit.Test;
import by.myProject.config.TestDataBaseConfig;
import by.myProject.model.service.impl.util.EntityCreator;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;


@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class UserServiceTest {

    private final static Long ID_USER = Long.valueOf(1);

    private UserDao userDao = mock(UserDao.class);
    private CourseService courseService = mock(CourseService.class);

    @Test
    public void testSaveUser(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.userDao = userDao;
        User user = EntityCreator.userCreate();
        userService.save(user);
        verify(userDao).save(user);
    }

    @Test
    public void testUpdateUser(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.userDao = userDao;
        User user = EntityCreator.userCreate(233);
        userService.update(user);
        verify(userDao).update(user);
    }

    @Test
    public void testFindById(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.userDao = userDao;
        Long userId = 1L;
        User user = EntityCreator.userCreate();
        when(userDao.findById(userId)).thenReturn(user);
        User resultUser = userService.findById(1l);
        verify(userDao).findById(userId);
        assertEquals(user, resultUser);
    }

    @Test
    public void testFindAllUsers(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.userDao = userDao;
        User user1 = EntityCreator.userCreate();
        User user2 = EntityCreator.userCreate(245);
        List<User> userList = Arrays.asList(user1, user2);
        when(userDao.findAllUsers()).thenReturn(userList);
        List<User> resultList = userService.findAllUsers();
        verify(userDao).findAllUsers();
        assertEquals(userList, resultList);
    }


    @Test
    public void testGetAllUsers(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.userDao = userDao;
        User user1 = EntityCreator.userCreate(123);
        Role role1 = EntityCreator.roleCreate(TypeRole.ROLE_TEACHER);
        user1.getRoles().add(role1);
        User user2 = EntityCreator.userCreate(100500);
        List<User> userList = Arrays.asList(user1, user2);
        when(userDao.findAllUsers()).thenReturn(userList);
        List<UserTO> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
        UserTO dto1 = users.get(0);
        assertEquals(user1.getIdUser(), dto1.getIdUser());
        assertEquals(user1.getUserName(), dto1.getUserName());
        assertEquals(user1.getLastName(), dto1.getLastName());
        assertEquals(user1.getFirstName(), dto1.getFirstName());
        assertEquals(user1.getEmail(), dto1.getEmail());
        assertEquals(user1.getPassword(), dto1.getPassword());
        assertEquals(role1.getTypeRole(), dto1.getTypeRole());
    }

    @Test
    public void testGetAllStudents(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.userDao = userDao;
        userService.courseService = courseService;
        User user1 = EntityCreator.userCreate(123);
        Role role1 = EntityCreator.roleCreate(TypeRole.ROLE_STUDENT);
        user1.getRoles().add(role1);
        Long courseId = 234L;
        Course course = EntityCreator.courseCreate(courseId);
        Status status = EntityCreator.statusCreate(course, 4);
        UserCourse userCourse1 = EntityCreator.userCourseStudentCreate(user1, course,345);
        course.setStatus(status);
        course.getUserCourses().add(userCourse1);
        User user2 = EntityCreator.userCreate(100500);
        Role role2 = EntityCreator.roleCreate(TypeRole.ROLE_TEACHER);
        user2.getRoles().add(role2);
        UserCourse userCourse2 = EntityCreator.userCourseTeacherCreate(user2, course);
        course.getUserCourses().add(userCourse2);
        when(courseService.findById(courseId)).thenReturn(course);
        List<UserTO> users = userService.getAllStudents(courseId);
        assertNotNull(users);
        assertEquals(1, users.size());
        UserTO dto1 = users.get(0);
        assertEquals(user1.getIdUser(), dto1.getIdUser());
        assertEquals(user1.getUserName(), dto1.getUserName());
        assertEquals(user1.getLastName(), dto1.getLastName());
        assertEquals(user1.getFirstName(), dto1.getFirstName());
        assertEquals(user1.getEmail(), dto1.getEmail());
        assertEquals(user1.getPassword(), dto1.getPassword());
    }


    @Test
    public void testDeleteUser(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.userDao = userDao;
        userService.deleteById(ID_USER);
        verify(userDao).deleteById(ID_USER);
    }
}
