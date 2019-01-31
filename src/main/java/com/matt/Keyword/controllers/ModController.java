package com.matt.Keyword.controllers;


import com.matt.Keyword.models.Mod;
import com.matt.Keyword.models.User;
import com.matt.Keyword.models.data.ModDao;
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
@RequestMapping("mod")

public class ModController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModDao modDao;

    @RequestMapping(value = "")
    public String index(Model model, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {

            return "redirect:/keyword/login";
        }

        session.getAttribute("currentUser");
        model.addAttribute("title", "Mods");
        Iterable modList = modDao.findAll();

        model.addAttribute("mods", modDao.findAll());


        return "mods/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayModForm(Model model, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            return "redirect:/keyword/login";
        }

        model.addAttribute("title", "Add a new mod");
        model.addAttribute(new Mod());
        return "mods/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddModForm(@ModelAttribute @Valid Mod newMod,
                                        Errors errors, Model model, HttpSession session) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "List of Mods");
            return "mods/add";
        }
        User currentUser = (User) session.getAttribute("currentUser");

        session.getId();

        currentUser.setId(currentUser.getId());

        newMod.setUserid(currentUser.getId());

        modDao.save(newMod);

        return "redirect:";

    }

}

