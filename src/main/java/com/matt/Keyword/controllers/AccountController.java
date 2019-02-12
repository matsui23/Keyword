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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

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
    public String index(Model model, HttpSession session){

        if (session.getAttribute("currentUser") == null) {

            return "redirect:/keyword/login";
        }

        session.getAttribute("currentUser");
        model.addAttribute("title", "Accounts");
        model.addAttribute("accounts", accountDao.findAll());

        return "accounts/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddAccountForm(Model model, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            return "redirect:/keyword/login";
        }

        model.addAttribute("title", "Add and link an account");
        model.addAttribute(new Account());
        return "accounts/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddAccountForm(@ModelAttribute  @Valid Account newAccount,
                                        Errors errors, Model model, HttpSession session) {

        List<String> passVals = Arrays.asList("Enter an account name and password", "Enter an account name", "Enter a password");
        if (newAccount.getName() == "" && newAccount.getPassword() == ""){
            String passVal = "";
            passVal = passVals.get(0);
            model.addAttribute("passVal", passVal);
            model.addAttribute("title", "Add and link an account");
            return "accounts/add";
        }
        if (newAccount.getName() == ""){
            String passVal = "";
            passVal = passVals.get(1);
            model.addAttribute("passVal", passVal);
            model.addAttribute("title", "Add and link an account");
            return "accounts/add";
        }
        if (newAccount.getPassword() == ""){
            String passVal = "";
            passVal = passVals.get(2);
            model.addAttribute("passVal", passVal);
            model.addAttribute("title", "Add and link an account");
            return "accounts/add";
        }

        User currentUser = (User) session.getAttribute("currentUser");

        session.getId();

        currentUser.setId(currentUser.getId());

        newAccount.setUserid(currentUser.getId());

        accountDao.save(newAccount);

        return "redirect:";

    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String deleteExistingAccount(Model model, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {

            return "redirect:/keyword/login";
        }

        session.getAttribute("currentUser");
        model.addAttribute("title", "Delete an account(s)");
        Iterable<Account> accountList = accountDao.findAll();
        model.addAttribute("accounts", accountList);
        return "accounts/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String deleteExistingAccountProcess(@RequestParam(value = "accountIds", required = false) int[] accountIds, Model model, HttpSession session) {

        if(accountIds == null){

            String accVal = "Please select some accounts to remove before submitting";

            session.getAttribute("currentUser");
            model.addAttribute("accVal", accVal);
            model.addAttribute("title", "Delete an account(s)");
            Iterable<Account> accountList = accountDao.findAll();
            model.addAttribute("accounts", accountList);

            return "accounts/remove";
        }

        Iterable<Account> accounts = accountDao.findAll();

        for(Account account : accounts){

            for(int id : accountIds)
                if (account.getId() == id) {
                    accountDao.deleteById(account.getId());
                }

        }
        return "redirect:";
    }

}
