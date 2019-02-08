package com.matt.Keyword.controllers;

import com.matt.Keyword.models.Mod;
import com.matt.Keyword.models.User;
import com.matt.Keyword.models.data.AccountDao;
import com.matt.Keyword.models.data.ModDao;
import com.matt.Keyword.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

//test commit note
//second try
//third try
/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("generator")

public class GeneratorController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModDao modDao;

    @Autowired
    private AccountDao accountDao;

    @RequestMapping(value = "")
    public String index(Model model, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {

            return "redirect:/keyword/login";
        }

        List<Integer> typeIds = Arrays.asList(1, 2, 3);
        List<String> typeLabels = Arrays.asList("Special", "Alphabetic", "Numeric");
        session.getAttribute("currentUser");
        model.addAttribute("title", "Password Generator");
        model.addAttribute("typeIds", typeIds);
        model.addAttribute("typeLabels", typeLabels);
        return "generator/index";

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String process(Model model, HttpSession session,@RequestParam(value = "typeIds", required = false) int[] typeIds, @RequestParam(value = "range", required = false) Integer range) {

        List<Integer> setTypeIds = Arrays.asList(1, 2, 3);
        if (session.getAttribute("currentUser") == null) {

            return "redirect:/keyword/login";
        }

        User currentUser = (User) session.getAttribute("currentUser");
        Random randomNum = new Random();
        ArrayList<String> specials = new ArrayList<>();
        ArrayList<String> alphabetics = new ArrayList<>();
        ArrayList<String> numerics = new ArrayList<>();

        Iterable<Mod> userMods = modDao.findAll();
        String result = "";

        int j = 0;
        int i = 0;

        while(i < range){

            for(Mod mod : userMods){

                if(mod.getUserid() == currentUser.getId()){

                    for(Integer type : typeIds){

                        if(type == mod.getRole()){

                            if(mod.getRole() == 1){
                                specials.add(mod.getEntry());
                            }
                            if(mod.getRole() == 2){
                                alphabetics.add(mod.getEntry());
                            }
                            if(mod.getRole() == 3){
                                numerics.add(mod.getEntry());
                            }

                            i = i + 1;

                        }
                    }
                }
            }
        }

        Collections.shuffle(specials);
        Collections.shuffle(alphabetics);
        Collections.shuffle(numerics);

        while (j < range){
            for (Integer type : typeIds){
                if(specials.size() <= j && alphabetics.size() <= j && numerics.size() <= j){
                    j = j + 1;
                }
                if (type == 1){
                    if (specials.size() <= j){
                        continue;
                    }
                    if(specials.size() == 0){
                        continue;
                    }
                    result = result + specials.get(j);
                }
                if (type == 2){
                    if (alphabetics.size() <= j){
                        continue;
                    }
                    if(alphabetics.size() == 0){
                        continue;
                    }
                    result = result + alphabetics.get(j);
                }
                if (type == 3){
                    if (numerics.size() <= j){
                        continue;
                    }
                    if(numerics.size() == 0){
                        continue;
                    }
                    result = result + numerics.get(j);
                }
                j = j + 1;
            }
        }
        session.getAttribute("currentUser");
        model.addAttribute("title", "Password Generator");
        model.addAttribute("result", result);
        model.addAttribute("typeIds", typeIds);
        model.addAttribute("range", range);


        return "generator/result";

    }

}