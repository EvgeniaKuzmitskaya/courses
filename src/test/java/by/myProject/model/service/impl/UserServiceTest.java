package by.myProject.model.service.impl;

import by.myProject.model.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import by.myProject.config.TestDataBaseConfig;
import by.myProject.model.domain.User;
import by.myProject.model.service.impl.util.EntityCreator;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.*;
import java.util.Optional;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class UserServiceTest {

//    private User user;
//    private final static Long DEFAULT_USER_ID = Long.valueOf(1);
//
//    @Autowired
//    private UserService userService;
//
//    @Before
//    public void setup(){
//        user = EntityCreator.userCreate();
//        userService.save(user);
//    }
//
//    @Test
//    public void testGetUserByIdNotFound(){
//        User foundUser = userService.findById(Long.MAX_VALUE);
//
////        assertFalse(foundUser.isPresent());
//    }
//
//    @After
//    public void clearDB(){
//        userService.deleteById(DEFAULT_USER_ID);
//    }


}
