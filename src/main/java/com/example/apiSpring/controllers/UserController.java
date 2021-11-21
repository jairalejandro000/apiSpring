package com.example.apiSpring.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.apiSpring.models.MailModel;
import com.example.apiSpring.models.UserModel;
import com.example.apiSpring.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ArrayList<UserModel> getAllUsers(){
        return userService.getUsers();
    }
    @GetMapping(path = "/get/{id}")
    public Optional<UserModel> getUser(@PathVariable long id){
        return userService.getUser(id);
    }
    @PostMapping("/create")
    public UserModel createUser(@RequestBody UserModel user){
        return this.userService.createUser(user);
    }
    @DeleteMapping(path = "/Delete/{id}")
    public Optional<UserModel> deleteUser(@PathVariable("id") long id){
        return userService.deleteUser(id);
    }
    //Nomas no jalo por sus huevotes
    @PostMapping("/Prueba")
    public MailModel setEmail(){
        MailModel mail = new MailModel();
        mail.setFrom("jair.alejandro.mtz32@gmail.com");
        mail.setTo("jairalejandro32@outlook.com");
        mail.setSubject("Simple mail");
        mail.setText("Hey I'm a simple mail");
        //userService.sendEmail(mail);
        return mail;
    }
}
