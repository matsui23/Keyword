package com.matt.Keyword.controllers;


import com.matt.Keyword.models.Account;
import com.matt.Keyword.models.User;
import com.matt.Keyword.models.data.AccountDao;
import com.matt.Keyword.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

//test commit note
//second try
//third try
/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("account")

public class AccountController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AccountDao accountDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title", "Accounts");
        model.addAttribute("accounts", accountDao.findAll());

        return "accounts/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddAccountForm(Model model) {
        model.addAttribute("title", "Add and link an account");
        model.addAttribute(new Account());
        return "accounts/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddAccountForm(@ModelAttribute  @Valid Account newAccount,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "List of accounts");
            return "accounts/add";
        }

        accountDao.save(newAccount);

        return "redirect:";
    }

}