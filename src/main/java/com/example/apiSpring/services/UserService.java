package com.example.apiSpring.services;

//import java.security.KeyStore.PasswordProtection;
import java.util.ArrayList;
import java.util.Optional;

import com.example.apiSpring.models.MailModel;
import com.example.apiSpring.models.UserModel;
import com.example.apiSpring.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //PasswordEncoder passwordEncoder;

    public ArrayList<UserModel> getUsers(){
        try{
            return (ArrayList<UserModel>) userRepository.findAll();
        }catch (Exception errorMessage){
            System.out.println(errorMessage);
            return null;
        }
    }
    public Optional<UserModel> getUser(long id){
        try{
            return userRepository.findById(id);
        }catch (Exception errorMessage){
            System.out.println(errorMessage);
            return null;
        }
    }
    public UserModel createUser(UserModel user){
        try{
            //passwordEncoder = new BCryptPasswordEncoder();
            //String encodedPassword = passwordEncoder.encode(user.getPassword());
            //user.setPassword(encodedPassword);
            return userRepository.save(user);
        }catch (Exception errorMessage){
            System.out.println(errorMessage);
            return null;
        }
    }
    public Optional<UserModel> deleteUser(long id){
        try{
            userRepository.deleteById(id);
            System.out.println("User deleted");
            return (userRepository.findById(id));
        }catch (Exception errorMessage){
            System.out.println(errorMessage);
            return null;
        }
    }
    public UserModel updateUser(UserModel userUpdate){
        //Optional<UserModel> user = userRepository.findById(id);
        //user.get().setUsername(userUpdate.getUsername());
        //user.get().setEmail(userUpdate.getEmail());
        userRepository.save(userUpdate);
        return userUpdate;

    }
    private JavaMailSender javaMailSender;
    public MailModel sendEmail(MailModel mail){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mail.getFrom());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getText());
        simpleMailMessage.setTo(mail.getTo());
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
        return mail;
    }
}
