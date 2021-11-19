package com.example.apiSpring.controllers;

import java.util.ArrayList;

import com.example.apiSpring.models.UserModel;
import com.example.apiSpring.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.Mapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public ArrayList<UserModel> getAllUsers(){
        return userService.getUsers();
    }
    @PostMapping("/Create")
    public UserModel createUser(@RequestBody UserModel user){
        return this.userService.createUser(user);
    }
    @DeleteMapping(path = "/Delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        boolean delete = userService.deleteUser(id);
        if(delete){
            return "User was delete";
        }else{
            return "User wasn't found";
        }
    }

}
