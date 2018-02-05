package by.myProject.controller;

import by.myProject.exception.UserNotFoundException;
import by.myProject.model.domain.Role;
import by.myProject.model.domain.User;
import by.myProject.model.domain.dto.UserTO;
import by.myProject.model.service.RoleService;
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
import java.util.List;
import java.util.Optional;

@Controller
public class RegistrationUserController {

    protected static Logger logger = Logger.getLogger(RegistrationUserController.class);


    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "roleService")
    private RoleService roleService;

    //показывает форму регистрации
    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public String newUser() {
        return "registrationUser";
    }

    //сохраняет пользователя
    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("userto")UserTO userTO, String roleId,
                           BindingResult result, ModelMap model, User user) {
        if(result.hasErrors()){
            return "registrationUser";
        }
        Optional<User> existingUser = userService.findByUserName(user.getUserName());
        if (existingUser.isPresent()){
            model.addAttribute("username_error", "Пользователь с таким логином существует");
            return "error";
        } else {
            Optional<User> maybeUser = userService.saveUser(userTO, roleId);
            if (maybeUser.isPresent()) {
                System.out.println("user=" + maybeUser.get());
                model.addAttribute("registrationForm", maybeUser.get());
                return "redirect:/registrationUserSuccess";
            } else {
                System.out.println("user does not exist :(");
                return "registrationUser";
            }
        }
    }

    // возвращает список ролей
    @ModelAttribute("roles")
    public List<Role> initializeRoles() {
        return roleService.findAllRoles();
    }

    // выводит таблицу с пользователями
    @RequestMapping(value = "/registrationUserSuccess", method = RequestMethod.GET)
    public String listUsers(Model model){
        model.addAttribute("listUsers",userService.getAllUsers());
        return "registrationUserSuccess";
    }

   // удаляет пользователя по id
    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") Long id){
        this.userService.deleteById(id);
        return "redirect:/registrationUserSuccess";
    }

    // показывает форму для редактирования данных о пользователе
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditUser(@PathVariable("id") Long id) {
        UserTO userTO = userService.getUserEdit(id);
        if (userTO == null) try {
            throw new UserNotFoundException(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return new ModelAndView("registrationUserEdit","command",userTO);
    }

    //редактирует пользователя
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editUser(UserTO userTO, Long idRole, ModelMap model, Long idUser){
        Optional<User> maybeUser = userService.updateUser(userTO, idRole, idUser);
        if (maybeUser.isPresent()) {
            System.out.println("user=" + maybeUser.get());
            model.addAttribute("registrationUserEdit", maybeUser.get());
            return "redirect:/registrationUserSuccess";
        } else {
            System.out.println("user does not exist :(");
            return "registrationUserEdit";
        }
    }
}
