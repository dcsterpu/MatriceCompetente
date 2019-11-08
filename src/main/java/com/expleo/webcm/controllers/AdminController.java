package com.expleo.webcm.controllers;

import com.expleo.webcm.entity.expleodb.UserExpleo;
import com.expleo.webcm.service.SearchService;
import com.expleo.webcm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    SearchService searchService;

    //add req mapping for /admin
    @GetMapping()
    public String showAdmin(){
        return "admin";
    }

    @RequestMapping("/tomcatManager")
    public String tomcat(){
        return "redirect:/manager/st.jsp";
    }

    //add a new user
    @GetMapping("/addUser")
    public String addUser(Model theModel){

        UserExpleo employee = new UserExpleo();
        theModel.addAttribute("newEmployee", employee);

        searchService.searchUser("Ovi");

        return "add-user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("newEmployee") UserExpleo employee, BindingResult result){

        if (result.hasErrors()){
            return "add-user";
        }

        userService.saveNewUser(employee);
        System.out.println("================================================" + employee.getNume());
        userService.saveNewUserSecurityDb(employee);

        return "redirect:/admin";
    }

    @GetMapping("/updateUser")
    public String updateUser(){
        return "search-update-user";
    }

    @GetMapping("/updateUser/search")
    public String searchUsers(@RequestParam(value = "searchTerm") String text, Model theModel){
        List<UserExpleo> result = searchService.searchUser(text);

//        for(Object o : result){
//            System.out.println( "-----------------" + o );
//        }
        theModel.addAttribute("result", result);

        return "search-update-user";
    }

    @PostMapping("/updateUser/modify")
    public String modifyUser(@RequestParam("userId") int theId, Model theModel){

        return "update-user";
    }
}
