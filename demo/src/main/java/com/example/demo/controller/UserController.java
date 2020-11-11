package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController() {

    }

    @GetMapping("/users")
    public String getAllUsers(Model theModel)   {
        theModel.addAttribute("usersList", userRepository.findAll());
        return "users";
    }
    @RequestMapping(value = "/users/showFormForAdd", method = RequestMethod.GET)
    public String showFormForAddPassword(Model theModel)   {
        theModel.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user,
                           Model theModel) throws Exception {
        userRepository.save(user);
        return getAllUsers(theModel);
    }

    @GetMapping("/users/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int id, Model theModel) {
        theModel.addAttribute("user", userRepository.findById(id));
        return "addUser";
    }

    @GetMapping("/users/deleteUser")
    public String deleteUser(@RequestParam("id") Integer id, Model theModel) throws Exception {
        userRepository.deleteById(id);
        theModel.addAttribute("usersList", userRepository.findAll());
        return "users";
    }
}
