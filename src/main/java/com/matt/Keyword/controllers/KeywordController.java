package com.matt.Keyword.controllers;


import com.matt.Keyword.models.User;
import com.matt.Keyword.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//test commit note
//second try
//third try
/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("keyword")

public class KeywordController {

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "")
    public String index(Model model, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            return "redirect:keyword/login";
        }

        model.addAttribute("title", "Keyword");

        return "user/index";

    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String displayUserRegistrationForm(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute(new User());
        return "user/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processAddUserForm(@ModelAttribute @Valid User newUser,
                                     Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "user/register";
        }

        userDao.save(newUser);

        return "redirect:/keyword";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayUserLoginForm(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute(new User());
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processUserLoginForm(@ModelAttribute @Valid User newUser,
                                       Errors errors, Model model, HttpSession session) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Login");
            return "user/login";
        }

        for (User userCheck : userDao.findAll()) {

            if (userCheck.toString().contentEquals(newUser.toString())) {

                session.setAttribute("currentUser", newUser);
                model.addAttribute("currentUser", newUser);

                return "redirect:";
            }
        }
        model.addAttribute("title", "Login");
        return "user/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String userLogout(Model model, HttpSession session) {


        session.removeAttribute("currentUser");
        model.addAttribute(new User());

        return "user/login";

    }

}
