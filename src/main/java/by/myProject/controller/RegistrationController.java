package by.myProject.controller;

import by.myProject.model.domain.Role;
import by.myProject.model.domain.User;
import by.myProject.model.service.RoleService;
import by.myProject.model.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    protected static Logger logger = Logger.getLogger(RegistrationController.class);

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public String newUser(ModelMap model, User user) {
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result) {
        if(result.hasErrors()){
            return "registration";
        }
        userService.save(user);
        return "redirect:/usersList";
    }

    @ModelAttribute("roles")
    public List<Role> initializeRoles() {
        return roleService.findAllRoles();
    }

    @RequestMapping(value = "/usersList", method = RequestMethod.GET)
    public String addAll(Model model){
        List<User> list = new ArrayList(userService.findAllUsers());
        model.addAttribute("listUsers",list);
        return "usersList";
    }


    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){

        this.userService.deleteById(id);
        return "redirect:/registration";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.findById(id));
        model.addAttribute("listUsers", this.userService.findAllUsers());
        return "registration";
    }











}
