package by.myProject.controller;

import by.myProject.model.domain.Role;
import by.myProject.model.domain.User;
import by.myProject.model.service.RoleService;
import by.myProject.model.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.Map;

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

    @RequestMapping(value = {"/registration"})
    @Transactional
    public String saveUser(@Valid User user, String roleId, BindingResult result) {
        if(result.hasErrors()){
            return "registration";
        }
        Role r = roleService.findById(Long.parseLong(roleId));
        user.getRoles().add(r);
        userService.save(user);
        return "redirect:/usersList";
    }

    @ModelAttribute("roles")
    public List<Role> initializeRoles() {
        return roleService.findAllRoles();
    }

    @RequestMapping(value = "/usersList", method = RequestMethod.GET)
    public String addAll(Model model){
        model.addAttribute("listUsers",userService.getAllUsers());
        return "usersList";
    }


    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") Long id){
        this.userService.deleteById(id);
        return "redirect:/usersList";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", this.userService.findById(id));
        model.addAttribute("listUsers", this.userService.findAllUsers());
        return "usersList";
    }

}
